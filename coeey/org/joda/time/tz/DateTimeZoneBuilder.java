package org.joda.time.tz;

import com.facebook.imageutils.JfifUtil;
import com.google.common.primitives.Ints;
import humanize.util.Constants;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.chrono.ISOChronology;

public class DateTimeZoneBuilder {
    private final ArrayList<RuleSet> iRuleSets = new ArrayList(10);

    private static final class DSTZone extends DateTimeZone {
        private static final long serialVersionUID = 6941492635554961361L;
        final Recurrence iEndRecurrence;
        final int iStandardOffset;
        final Recurrence iStartRecurrence;

        static DSTZone readFrom(DataInput dataInput, String str) throws IOException {
            return new DSTZone(str, (int) DateTimeZoneBuilder.readMillis(dataInput), Recurrence.readFrom(dataInput), Recurrence.readFrom(dataInput));
        }

        DSTZone(String str, int i, Recurrence recurrence, Recurrence recurrence2) {
            super(str);
            this.iStandardOffset = i;
            this.iStartRecurrence = recurrence;
            this.iEndRecurrence = recurrence2;
        }

        public String getNameKey(long j) {
            return findMatchingRecurrence(j).getNameKey();
        }

        public int getOffset(long j) {
            return this.iStandardOffset + findMatchingRecurrence(j).getSaveMillis();
        }

        public int getStandardOffset(long j) {
            return this.iStandardOffset;
        }

        public boolean isFixed() {
            return false;
        }

        public long nextTransition(long j) {
            long next;
            long j2;
            int i = this.iStandardOffset;
            Recurrence recurrence = this.iStartRecurrence;
            Recurrence recurrence2 = this.iEndRecurrence;
            try {
                next = recurrence.next(j, i, recurrence2.getSaveMillis());
                if (j > 0 && next < 0) {
                    next = j;
                }
                j2 = next;
            } catch (IllegalArgumentException e) {
                j2 = j;
            } catch (ArithmeticException e2) {
                j2 = j;
            }
            try {
                next = recurrence2.next(j, i, recurrence.getSaveMillis());
                if (j <= 0 || next >= 0) {
                    j = next;
                }
                next = j;
            } catch (IllegalArgumentException e3) {
                next = j;
            } catch (ArithmeticException e4) {
                next = j;
            }
            if (j2 > next) {
                return next;
            }
            return j2;
        }

        public long previousTransition(long j) {
            long previous;
            long j2;
            long j3 = j + 1;
            int i = this.iStandardOffset;
            Recurrence recurrence = this.iStartRecurrence;
            Recurrence recurrence2 = this.iEndRecurrence;
            try {
                previous = recurrence.previous(j3, i, recurrence2.getSaveMillis());
                if (j3 < 0 && previous > 0) {
                    previous = j3;
                }
                j2 = previous;
            } catch (IllegalArgumentException e) {
                j2 = j3;
            } catch (ArithmeticException e2) {
                j2 = j3;
            }
            try {
                previous = recurrence2.previous(j3, i, recurrence.getSaveMillis());
                if (j3 >= 0 || previous <= 0) {
                    j3 = previous;
                }
                previous = j3;
            } catch (IllegalArgumentException e3) {
                previous = j3;
            } catch (ArithmeticException e4) {
                previous = j3;
            }
            if (j2 > previous) {
                previous = j2;
            }
            return previous - 1;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DSTZone)) {
                return false;
            }
            DSTZone dSTZone = (DSTZone) obj;
            if (getID().equals(dSTZone.getID()) && this.iStandardOffset == dSTZone.iStandardOffset && this.iStartRecurrence.equals(dSTZone.iStartRecurrence) && this.iEndRecurrence.equals(dSTZone.iEndRecurrence)) {
                return true;
            }
            return false;
        }

        public void writeTo(DataOutput dataOutput) throws IOException {
            DateTimeZoneBuilder.writeMillis(dataOutput, (long) this.iStandardOffset);
            this.iStartRecurrence.writeTo(dataOutput);
            this.iEndRecurrence.writeTo(dataOutput);
        }

        private Recurrence findMatchingRecurrence(long j) {
            long next;
            int i = this.iStandardOffset;
            Recurrence recurrence = this.iStartRecurrence;
            Recurrence recurrence2 = this.iEndRecurrence;
            try {
                next = recurrence.next(j, i, recurrence2.getSaveMillis());
            } catch (IllegalArgumentException e) {
                next = j;
            } catch (ArithmeticException e2) {
                next = j;
            }
            try {
                j = recurrence2.next(j, i, recurrence.getSaveMillis());
            } catch (IllegalArgumentException e3) {
            } catch (ArithmeticException e4) {
            }
            if (next > j) {
                return recurrence;
            }
            return recurrence2;
        }
    }

    private static final class OfYear {
        final boolean iAdvance;
        final int iDayOfMonth;
        final int iDayOfWeek;
        final int iMillisOfDay;
        final char iMode;
        final int iMonthOfYear;

        static OfYear readFrom(DataInput dataInput) throws IOException {
            return new OfYear((char) dataInput.readUnsignedByte(), dataInput.readUnsignedByte(), dataInput.readByte(), dataInput.readUnsignedByte(), dataInput.readBoolean(), (int) DateTimeZoneBuilder.readMillis(dataInput));
        }

        OfYear(char c, int i, int i2, int i3, boolean z, int i4) {
            if (c == 'u' || c == 'w' || c == 's') {
                this.iMode = c;
                this.iMonthOfYear = i;
                this.iDayOfMonth = i2;
                this.iDayOfWeek = i3;
                this.iAdvance = z;
                this.iMillisOfDay = i4;
                return;
            }
            throw new IllegalArgumentException("Unknown mode: " + c);
        }

        public long setInstant(int i, int i2, int i3) {
            if (this.iMode == 'w') {
                i2 += i3;
            } else if (this.iMode != 's') {
                i2 = 0;
            }
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            long dayOfMonth = setDayOfMonth(instanceUTC, instanceUTC.millisOfDay().set(instanceUTC.monthOfYear().set(instanceUTC.year().set(0, i), this.iMonthOfYear), this.iMillisOfDay));
            if (this.iDayOfWeek != 0) {
                dayOfMonth = setDayOfWeek(instanceUTC, dayOfMonth);
            }
            return dayOfMonth - ((long) i2);
        }

        public long next(long j, int i, int i2) {
            if (this.iMode == 'w') {
                i += i2;
            } else if (this.iMode != 's') {
                i = 0;
            }
            long j2 = ((long) i) + j;
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            long dayOfMonthNext = setDayOfMonthNext(instanceUTC, instanceUTC.millisOfDay().add(instanceUTC.millisOfDay().set(instanceUTC.monthOfYear().set(j2, this.iMonthOfYear), 0), this.iMillisOfDay));
            if (this.iDayOfWeek != 0) {
                dayOfMonthNext = setDayOfWeek(instanceUTC, dayOfMonthNext);
                if (dayOfMonthNext <= j2) {
                    dayOfMonthNext = setDayOfWeek(instanceUTC, setDayOfMonthNext(instanceUTC, instanceUTC.monthOfYear().set(instanceUTC.year().add(dayOfMonthNext, 1), this.iMonthOfYear)));
                }
            } else if (dayOfMonthNext <= j2) {
                dayOfMonthNext = setDayOfMonthNext(instanceUTC, instanceUTC.year().add(dayOfMonthNext, 1));
            }
            return dayOfMonthNext - ((long) i);
        }

        public long previous(long j, int i, int i2) {
            if (this.iMode == 'w') {
                i += i2;
            } else if (this.iMode != 's') {
                i = 0;
            }
            long j2 = ((long) i) + j;
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            long dayOfMonthPrevious = setDayOfMonthPrevious(instanceUTC, instanceUTC.millisOfDay().add(instanceUTC.millisOfDay().set(instanceUTC.monthOfYear().set(j2, this.iMonthOfYear), 0), this.iMillisOfDay));
            if (this.iDayOfWeek != 0) {
                dayOfMonthPrevious = setDayOfWeek(instanceUTC, dayOfMonthPrevious);
                if (dayOfMonthPrevious >= j2) {
                    dayOfMonthPrevious = setDayOfWeek(instanceUTC, setDayOfMonthPrevious(instanceUTC, instanceUTC.monthOfYear().set(instanceUTC.year().add(dayOfMonthPrevious, -1), this.iMonthOfYear)));
                }
            } else if (dayOfMonthPrevious >= j2) {
                dayOfMonthPrevious = setDayOfMonthPrevious(instanceUTC, instanceUTC.year().add(dayOfMonthPrevious, -1));
            }
            return dayOfMonthPrevious - ((long) i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OfYear)) {
                return false;
            }
            OfYear ofYear = (OfYear) obj;
            if (this.iMode == ofYear.iMode && this.iMonthOfYear == ofYear.iMonthOfYear && this.iDayOfMonth == ofYear.iDayOfMonth && this.iDayOfWeek == ofYear.iDayOfWeek && this.iAdvance == ofYear.iAdvance && this.iMillisOfDay == ofYear.iMillisOfDay) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "[OfYear]\nMode: " + this.iMode + '\n' + "MonthOfYear: " + this.iMonthOfYear + '\n' + "DayOfMonth: " + this.iDayOfMonth + '\n' + "DayOfWeek: " + this.iDayOfWeek + '\n' + "AdvanceDayOfWeek: " + this.iAdvance + '\n' + "MillisOfDay: " + this.iMillisOfDay + '\n';
        }

        public void writeTo(DataOutput dataOutput) throws IOException {
            dataOutput.writeByte(this.iMode);
            dataOutput.writeByte(this.iMonthOfYear);
            dataOutput.writeByte(this.iDayOfMonth);
            dataOutput.writeByte(this.iDayOfWeek);
            dataOutput.writeBoolean(this.iAdvance);
            DateTimeZoneBuilder.writeMillis(dataOutput, (long) this.iMillisOfDay);
        }

        private long setDayOfMonthNext(Chronology chronology, long j) {
            try {
                return setDayOfMonth(chronology, j);
            } catch (IllegalArgumentException e) {
                if (this.iMonthOfYear == 2 && this.iDayOfMonth == 29) {
                    while (!chronology.year().isLeap(j)) {
                        j = chronology.year().add(j, 1);
                    }
                    return setDayOfMonth(chronology, j);
                }
                throw e;
            }
        }

        private long setDayOfMonthPrevious(Chronology chronology, long j) {
            try {
                return setDayOfMonth(chronology, j);
            } catch (IllegalArgumentException e) {
                if (this.iMonthOfYear == 2 && this.iDayOfMonth == 29) {
                    while (!chronology.year().isLeap(j)) {
                        j = chronology.year().add(j, -1);
                    }
                    return setDayOfMonth(chronology, j);
                }
                throw e;
            }
        }

        private long setDayOfMonth(Chronology chronology, long j) {
            if (this.iDayOfMonth >= 0) {
                return chronology.dayOfMonth().set(j, this.iDayOfMonth);
            }
            return chronology.dayOfMonth().add(chronology.monthOfYear().add(chronology.dayOfMonth().set(j, 1), 1), this.iDayOfMonth);
        }

        private long setDayOfWeek(Chronology chronology, long j) {
            int i = this.iDayOfWeek - chronology.dayOfWeek().get(j);
            if (i == 0) {
                return j;
            }
            if (this.iAdvance) {
                if (i < 0) {
                    i += 7;
                }
            } else if (i > 0) {
                i -= 7;
            }
            return chronology.dayOfWeek().add(j, i);
        }
    }

    private static final class PrecalculatedZone extends DateTimeZone {
        private static final long serialVersionUID = 7811976468055766265L;
        private final String[] iNameKeys;
        private final int[] iStandardOffsets;
        private final DSTZone iTailZone;
        private final long[] iTransitions;
        private final int[] iWallOffsets;

        static PrecalculatedZone readFrom(DataInput dataInput, String str) throws IOException {
            int i;
            int readUnsignedShort = dataInput.readUnsignedShort();
            String[] strArr = new String[readUnsignedShort];
            for (i = 0; i < readUnsignedShort; i++) {
                strArr[i] = dataInput.readUTF();
            }
            int readInt = dataInput.readInt();
            long[] jArr = new long[readInt];
            int[] iArr = new int[readInt];
            int[] iArr2 = new int[readInt];
            String[] strArr2 = new String[readInt];
            for (i = 0; i < readInt; i++) {
                int readUnsignedByte;
                jArr[i] = DateTimeZoneBuilder.readMillis(dataInput);
                iArr[i] = (int) DateTimeZoneBuilder.readMillis(dataInput);
                iArr2[i] = (int) DateTimeZoneBuilder.readMillis(dataInput);
                if (readUnsignedShort < 256) {
                    try {
                        readUnsignedByte = dataInput.readUnsignedByte();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new IOException("Invalid encoding");
                    }
                }
                readUnsignedByte = dataInput.readUnsignedShort();
                strArr2[i] = strArr[readUnsignedByte];
            }
            DSTZone dSTZone = null;
            if (dataInput.readBoolean()) {
                dSTZone = DSTZone.readFrom(dataInput, str);
            }
            return new PrecalculatedZone(str, jArr, iArr, iArr2, strArr2, dSTZone);
        }

        static PrecalculatedZone create(String str, boolean z, ArrayList<Transition> arrayList, DSTZone dSTZone) {
            int size = arrayList.size();
            if (size == 0) {
                throw new IllegalArgumentException();
            }
            DSTZone dSTZone2;
            String str2;
            long[] jArr = new long[size];
            int[] iArr = new int[size];
            int[] iArr2 = new int[size];
            String[] strArr = new String[size];
            Transition transition = null;
            int i = 0;
            while (i < size) {
                Transition transition2 = (Transition) arrayList.get(i);
                if (transition2.isTransitionFrom(transition)) {
                    jArr[i] = transition2.getMillis();
                    iArr[i] = transition2.getWallOffset();
                    iArr2[i] = transition2.getStandardOffset();
                    strArr[i] = transition2.getNameKey();
                    i++;
                    transition = transition2;
                } else {
                    throw new IllegalArgumentException(str);
                }
            }
            String[] strArr2 = new String[5];
            String[][] zoneStrings = new DateFormatSymbols(Locale.ENGLISH).getZoneStrings();
            int i2 = 0;
            Object[] objArr = strArr2;
            while (i2 < zoneStrings.length) {
                Object[] objArr2 = zoneStrings[i2];
                if (objArr2 != null && objArr2.length == 5) {
                    if (str.equals(objArr2[0])) {
                        i2++;
                        objArr = objArr2;
                    }
                }
                objArr2 = objArr;
                i2++;
                objArr = objArr2;
            }
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            i2 = 0;
            while (i2 < strArr.length - 1) {
                String str3 = strArr[i2];
                String str4 = strArr[i2 + 1];
                long j = (long) iArr[i2];
                long j2 = (long) iArr[i2 + 1];
                long j3 = (long) iArr2[i2];
                long j4 = (long) iArr2[i2 + 1];
                Period period = new Period(jArr[i2], jArr[i2 + 1], PeriodType.yearMonthDay(), instanceUTC);
                if (j != j2 && j3 == j4 && str3.equals(str4) && period.getYears() == 0 && period.getMonths() > 4 && period.getMonths() < 8 && str3.equals(objArr[2]) && str3.equals(objArr[4])) {
                    if (ZoneInfoLogger.verbose()) {
                        System.out.println("Fixing duplicate name key - " + str4);
                        System.out.println("     - " + new DateTime(jArr[i2], instanceUTC) + " - " + new DateTime(jArr[i2 + 1], instanceUTC));
                    }
                    if (j > j2) {
                        strArr[i2] = (str3 + "-Summer").intern();
                    } else if (j < j2) {
                        strArr[i2 + 1] = (str4 + "-Summer").intern();
                        i2++;
                    }
                }
                i2++;
            }
            if (dSTZone == null || !dSTZone.iStartRecurrence.getNameKey().equals(dSTZone.iEndRecurrence.getNameKey())) {
                dSTZone2 = dSTZone;
            } else {
                if (ZoneInfoLogger.verbose()) {
                    System.out.println("Fixing duplicate recurrent name key - " + dSTZone.iStartRecurrence.getNameKey());
                }
                dSTZone2 = dSTZone.iStartRecurrence.getSaveMillis() > 0 ? new DSTZone(dSTZone.getID(), dSTZone.iStandardOffset, dSTZone.iStartRecurrence.renameAppend("-Summer"), dSTZone.iEndRecurrence) : new DSTZone(dSTZone.getID(), dSTZone.iStandardOffset, dSTZone.iStartRecurrence, dSTZone.iEndRecurrence.renameAppend("-Summer"));
            }
            if (z) {
                str2 = str;
            } else {
                str2 = "";
            }
            return new PrecalculatedZone(str2, jArr, iArr, iArr2, strArr, dSTZone2);
        }

        private PrecalculatedZone(String str, long[] jArr, int[] iArr, int[] iArr2, String[] strArr, DSTZone dSTZone) {
            super(str);
            this.iTransitions = jArr;
            this.iWallOffsets = iArr;
            this.iStandardOffsets = iArr2;
            this.iNameKeys = strArr;
            this.iTailZone = dSTZone;
        }

        public String getNameKey(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            if (binarySearch >= 0) {
                return this.iNameKeys[binarySearch];
            }
            binarySearch ^= -1;
            if (binarySearch < jArr.length) {
                if (binarySearch > 0) {
                    return this.iNameKeys[binarySearch - 1];
                }
                return "UTC";
            } else if (this.iTailZone == null) {
                return this.iNameKeys[binarySearch - 1];
            } else {
                return this.iTailZone.getNameKey(j);
            }
        }

        public int getOffset(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            if (binarySearch >= 0) {
                return this.iWallOffsets[binarySearch];
            }
            binarySearch ^= -1;
            if (binarySearch < jArr.length) {
                if (binarySearch > 0) {
                    return this.iWallOffsets[binarySearch - 1];
                }
                return 0;
            } else if (this.iTailZone == null) {
                return this.iWallOffsets[binarySearch - 1];
            } else {
                return this.iTailZone.getOffset(j);
            }
        }

        public int getStandardOffset(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            if (binarySearch >= 0) {
                return this.iStandardOffsets[binarySearch];
            }
            binarySearch ^= -1;
            if (binarySearch < jArr.length) {
                if (binarySearch > 0) {
                    return this.iStandardOffsets[binarySearch - 1];
                }
                return 0;
            } else if (this.iTailZone == null) {
                return this.iStandardOffsets[binarySearch - 1];
            } else {
                return this.iTailZone.getStandardOffset(j);
            }
        }

        public boolean isFixed() {
            return false;
        }

        public long nextTransition(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            binarySearch = binarySearch >= 0 ? binarySearch + 1 : binarySearch ^ -1;
            if (binarySearch < jArr.length) {
                return jArr[binarySearch];
            }
            if (this.iTailZone == null) {
                return j;
            }
            long j2 = jArr[jArr.length - 1];
            if (j < j2) {
                j = j2;
            }
            return this.iTailZone.nextTransition(j);
        }

        public long previousTransition(long j) {
            long[] jArr = this.iTransitions;
            int binarySearch = Arrays.binarySearch(jArr, j);
            if (binarySearch < 0) {
                int i = binarySearch ^ -1;
                long previousTransition;
                if (i >= jArr.length) {
                    if (this.iTailZone != null) {
                        previousTransition = this.iTailZone.previousTransition(j);
                        if (previousTransition < j) {
                            return previousTransition;
                        }
                    }
                    previousTransition = jArr[i - 1];
                    if (previousTransition > Long.MIN_VALUE) {
                        return previousTransition - 1;
                    }
                    return j;
                } else if (i <= 0) {
                    return j;
                } else {
                    previousTransition = jArr[i - 1];
                    if (previousTransition > Long.MIN_VALUE) {
                        return previousTransition - 1;
                    }
                    return j;
                }
            } else if (j > Long.MIN_VALUE) {
                return j - 1;
            } else {
                return j;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PrecalculatedZone)) {
                return false;
            }
            PrecalculatedZone precalculatedZone = (PrecalculatedZone) obj;
            if (getID().equals(precalculatedZone.getID()) && Arrays.equals(this.iTransitions, precalculatedZone.iTransitions) && Arrays.equals(this.iNameKeys, precalculatedZone.iNameKeys) && Arrays.equals(this.iWallOffsets, precalculatedZone.iWallOffsets) && Arrays.equals(this.iStandardOffsets, precalculatedZone.iStandardOffsets)) {
                if (this.iTailZone == null) {
                    if (precalculatedZone.iTailZone == null) {
                        return true;
                    }
                } else if (this.iTailZone.equals(precalculatedZone.iTailZone)) {
                    return true;
                }
            }
            return false;
        }

        public void writeTo(DataOutput dataOutput) throws IOException {
            int i;
            boolean z = false;
            int length = this.iTransitions.length;
            Set<String> hashSet = new HashSet();
            for (i = 0; i < length; i++) {
                hashSet.add(this.iNameKeys[i]);
            }
            int size = hashSet.size();
            if (size > 65535) {
                throw new UnsupportedOperationException("String pool is too large");
            }
            String[] strArr = new String[size];
            int i2 = 0;
            for (String str : hashSet) {
                strArr[i2] = str;
                i2++;
            }
            dataOutput.writeShort(size);
            for (i = 0; i < size; i++) {
                dataOutput.writeUTF(strArr[i]);
            }
            dataOutput.writeInt(length);
            for (i2 = 0; i2 < length; i2++) {
                DateTimeZoneBuilder.writeMillis(dataOutput, this.iTransitions[i2]);
                DateTimeZoneBuilder.writeMillis(dataOutput, (long) this.iWallOffsets[i2]);
                DateTimeZoneBuilder.writeMillis(dataOutput, (long) this.iStandardOffsets[i2]);
                Object obj = this.iNameKeys[i2];
                i = 0;
                while (i < size) {
                    if (strArr[i].equals(obj)) {
                        if (size < 256) {
                            dataOutput.writeByte(i);
                        } else {
                            dataOutput.writeShort(i);
                        }
                    } else {
                        i++;
                    }
                }
            }
            if (this.iTailZone != null) {
                z = true;
            }
            dataOutput.writeBoolean(z);
            if (this.iTailZone != null) {
                this.iTailZone.writeTo(dataOutput);
            }
        }

        public boolean isCachable() {
            if (this.iTailZone != null) {
                return true;
            }
            long[] jArr = this.iTransitions;
            if (jArr.length <= 1) {
                return false;
            }
            double d = 0.0d;
            int i = 0;
            for (int i2 = 1; i2 < jArr.length; i2++) {
                long j = jArr[i2] - jArr[i2 - 1];
                if (j < 63158400000L) {
                    d += (double) j;
                    i++;
                }
            }
            if (i <= 0 || (d / ((double) i)) / 8.64E7d < 25.0d) {
                return false;
            }
            return true;
        }
    }

    private static final class Recurrence {
        final String iNameKey;
        final OfYear iOfYear;
        final int iSaveMillis;

        static Recurrence readFrom(DataInput dataInput) throws IOException {
            return new Recurrence(OfYear.readFrom(dataInput), dataInput.readUTF(), (int) DateTimeZoneBuilder.readMillis(dataInput));
        }

        Recurrence(OfYear ofYear, String str, int i) {
            this.iOfYear = ofYear;
            this.iNameKey = str;
            this.iSaveMillis = i;
        }

        public OfYear getOfYear() {
            return this.iOfYear;
        }

        public long next(long j, int i, int i2) {
            return this.iOfYear.next(j, i, i2);
        }

        public long previous(long j, int i, int i2) {
            return this.iOfYear.previous(j, i, i2);
        }

        public String getNameKey() {
            return this.iNameKey;
        }

        public int getSaveMillis() {
            return this.iSaveMillis;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Recurrence)) {
                return false;
            }
            Recurrence recurrence = (Recurrence) obj;
            if (this.iSaveMillis == recurrence.iSaveMillis && this.iNameKey.equals(recurrence.iNameKey) && this.iOfYear.equals(recurrence.iOfYear)) {
                return true;
            }
            return false;
        }

        public void writeTo(DataOutput dataOutput) throws IOException {
            this.iOfYear.writeTo(dataOutput);
            dataOutput.writeUTF(this.iNameKey);
            DateTimeZoneBuilder.writeMillis(dataOutput, (long) this.iSaveMillis);
        }

        Recurrence rename(String str) {
            return new Recurrence(this.iOfYear, str, this.iSaveMillis);
        }

        Recurrence renameAppend(String str) {
            return rename((this.iNameKey + str).intern());
        }

        public String toString() {
            return this.iOfYear + " named " + this.iNameKey + " at " + this.iSaveMillis;
        }
    }

    private static final class Rule {
        final int iFromYear;
        final Recurrence iRecurrence;
        final int iToYear;

        Rule(Recurrence recurrence, int i, int i2) {
            this.iRecurrence = recurrence;
            this.iFromYear = i;
            this.iToYear = i2;
        }

        public int getFromYear() {
            return this.iFromYear;
        }

        public int getToYear() {
            return this.iToYear;
        }

        public OfYear getOfYear() {
            return this.iRecurrence.getOfYear();
        }

        public String getNameKey() {
            return this.iRecurrence.getNameKey();
        }

        public int getSaveMillis() {
            return this.iRecurrence.getSaveMillis();
        }

        public long next(long j, int i, int i2) {
            int i3;
            long j2;
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            int i4 = i + i2;
            if (j == Long.MIN_VALUE) {
                i3 = Integer.MIN_VALUE;
            } else {
                i3 = instanceUTC.year().get(((long) i4) + j);
            }
            if (i3 < this.iFromYear) {
                j2 = (instanceUTC.year().set(0, this.iFromYear) - ((long) i4)) - 1;
            } else {
                j2 = j;
            }
            j2 = this.iRecurrence.next(j2, i, i2);
            if (j2 <= j || instanceUTC.year().get(((long) i4) + j2) <= this.iToYear) {
                return j2;
            }
            return j;
        }

        public String toString() {
            return this.iFromYear + " to " + this.iToYear + " using " + this.iRecurrence;
        }
    }

    private static final class RuleSet {
        private static final int YEAR_LIMIT = (ISOChronology.getInstanceUTC().year().get(DateTimeUtils.currentTimeMillis()) + 100);
        private String iInitialNameKey;
        private int iInitialSaveMillis;
        private ArrayList<Rule> iRules;
        private int iStandardOffset;
        private OfYear iUpperOfYear;
        private int iUpperYear;

        RuleSet() {
            this.iRules = new ArrayList(10);
            this.iUpperYear = Integer.MAX_VALUE;
        }

        RuleSet(RuleSet ruleSet) {
            this.iStandardOffset = ruleSet.iStandardOffset;
            this.iRules = new ArrayList(ruleSet.iRules);
            this.iInitialNameKey = ruleSet.iInitialNameKey;
            this.iInitialSaveMillis = ruleSet.iInitialSaveMillis;
            this.iUpperYear = ruleSet.iUpperYear;
            this.iUpperOfYear = ruleSet.iUpperOfYear;
        }

        public int getStandardOffset() {
            return this.iStandardOffset;
        }

        public void setStandardOffset(int i) {
            this.iStandardOffset = i;
        }

        public void setFixedSavings(String str, int i) {
            this.iInitialNameKey = str;
            this.iInitialSaveMillis = i;
        }

        public void addRule(Rule rule) {
            if (!this.iRules.contains(rule)) {
                this.iRules.add(rule);
            }
        }

        public void setUpperLimit(int i, OfYear ofYear) {
            this.iUpperYear = i;
            this.iUpperOfYear = ofYear;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.joda.time.tz.DateTimeZoneBuilder.Transition firstTransition(long r10) {
            /*
            r9 = this;
            r0 = r9.iInitialNameKey;
            if (r0 == 0) goto L_0x0015;
        L_0x0004:
            r1 = new org.joda.time.tz.DateTimeZoneBuilder$Transition;
            r4 = r9.iInitialNameKey;
            r0 = r9.iStandardOffset;
            r2 = r9.iInitialSaveMillis;
            r5 = r0 + r2;
            r6 = r9.iStandardOffset;
            r2 = r10;
            r1.<init>(r2, r4, r5, r6);
        L_0x0014:
            return r1;
        L_0x0015:
            r7 = new java.util.ArrayList;
            r0 = r9.iRules;
            r7.<init>(r0);
            r2 = -9223372036854775808;
            r1 = 0;
            r0 = 0;
            r8 = r0;
            r0 = r1;
            r1 = r8;
        L_0x0023:
            r4 = r9.nextTransition(r2, r0);
            if (r4 == 0) goto L_0x0036;
        L_0x0029:
            r2 = r4.getMillis();
            r0 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
            if (r0 != 0) goto L_0x0039;
        L_0x0031:
            r1 = new org.joda.time.tz.DateTimeZoneBuilder$Transition;
            r1.<init>(r10, r4);
        L_0x0036:
            r9.iRules = r7;
            goto L_0x0014;
        L_0x0039:
            r0 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
            if (r0 <= 0) goto L_0x006d;
        L_0x003d:
            if (r1 != 0) goto L_0x005c;
        L_0x003f:
            r2 = r7.iterator();
        L_0x0043:
            r0 = r2.hasNext();
            if (r0 == 0) goto L_0x005c;
        L_0x0049:
            r0 = r2.next();
            r0 = (org.joda.time.tz.DateTimeZoneBuilder.Rule) r0;
            r3 = r0.getSaveMillis();
            if (r3 != 0) goto L_0x0043;
        L_0x0055:
            r1 = new org.joda.time.tz.DateTimeZoneBuilder$Transition;
            r2 = r9.iStandardOffset;
            r1.<init>(r10, r0, r2);
        L_0x005c:
            if (r1 != 0) goto L_0x0036;
        L_0x005e:
            r1 = new org.joda.time.tz.DateTimeZoneBuilder$Transition;
            r4 = r4.getNameKey();
            r5 = r9.iStandardOffset;
            r6 = r9.iStandardOffset;
            r2 = r10;
            r1.<init>(r2, r4, r5, r6);
            goto L_0x0036;
        L_0x006d:
            r0 = new org.joda.time.tz.DateTimeZoneBuilder$Transition;
            r0.<init>(r10, r4);
            r1 = r4.getSaveMillis();
            r8 = r0;
            r0 = r1;
            r1 = r8;
            goto L_0x0023;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.tz.DateTimeZoneBuilder.RuleSet.firstTransition(long):org.joda.time.tz.DateTimeZoneBuilder$Transition");
        }

        public Transition nextTransition(long j, int i) {
            Chronology instanceUTC = ISOChronology.getInstanceUTC();
            long j2 = Long.MAX_VALUE;
            Iterator it = this.iRules.iterator();
            Rule rule = null;
            while (it.hasNext()) {
                Rule rule2 = (Rule) it.next();
                long next = rule2.next(j, this.iStandardOffset, i);
                if (next <= j) {
                    it.remove();
                } else {
                    Rule rule3;
                    long j3;
                    if (next <= j2) {
                        long j4 = next;
                        rule3 = rule2;
                        j3 = j4;
                    } else {
                        rule3 = rule;
                        j3 = j2;
                    }
                    j2 = j3;
                    rule = rule3;
                }
            }
            if (rule == null || instanceUTC.year().get(j2) >= YEAR_LIMIT) {
                return null;
            }
            if (this.iUpperYear >= Integer.MAX_VALUE || j2 < this.iUpperOfYear.setInstant(this.iUpperYear, this.iStandardOffset, i)) {
                return new Transition(j2, rule, this.iStandardOffset);
            }
            return null;
        }

        public long getUpperLimit(int i) {
            if (this.iUpperYear == Integer.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            return this.iUpperOfYear.setInstant(this.iUpperYear, this.iStandardOffset, i);
        }

        public DSTZone buildTailZone(String str) {
            if (this.iRules.size() == 2) {
                Rule rule = (Rule) this.iRules.get(0);
                Rule rule2 = (Rule) this.iRules.get(1);
                if (rule.getToYear() == Integer.MAX_VALUE && rule2.getToYear() == Integer.MAX_VALUE) {
                    return new DSTZone(str, this.iStandardOffset, rule.iRecurrence, rule2.iRecurrence);
                }
            }
            return null;
        }

        public String toString() {
            return this.iInitialNameKey + " initial: " + this.iInitialSaveMillis + " std: " + this.iStandardOffset + " upper: " + this.iUpperYear + Constants.SPACE + this.iUpperOfYear + Constants.SPACE + this.iRules;
        }
    }

    private static final class Transition {
        private final long iMillis;
        private final String iNameKey;
        private final int iStandardOffset;
        private final int iWallOffset;

        Transition(long j, Transition transition) {
            this.iMillis = j;
            this.iNameKey = transition.iNameKey;
            this.iWallOffset = transition.iWallOffset;
            this.iStandardOffset = transition.iStandardOffset;
        }

        Transition(long j, Rule rule, int i) {
            this.iMillis = j;
            this.iNameKey = rule.getNameKey();
            this.iWallOffset = rule.getSaveMillis() + i;
            this.iStandardOffset = i;
        }

        Transition(long j, String str, int i, int i2) {
            this.iMillis = j;
            this.iNameKey = str;
            this.iWallOffset = i;
            this.iStandardOffset = i2;
        }

        public long getMillis() {
            return this.iMillis;
        }

        public String getNameKey() {
            return this.iNameKey;
        }

        public int getWallOffset() {
            return this.iWallOffset;
        }

        public int getStandardOffset() {
            return this.iStandardOffset;
        }

        public int getSaveMillis() {
            return this.iWallOffset - this.iStandardOffset;
        }

        public Transition withMillis(long j) {
            return new Transition(j, this.iNameKey, this.iWallOffset, this.iStandardOffset);
        }

        public boolean isTransitionFrom(Transition transition) {
            if (transition == null) {
                return true;
            }
            if (this.iMillis <= transition.iMillis || (this.iWallOffset == transition.iWallOffset && this.iStandardOffset == transition.iStandardOffset && this.iNameKey.equals(transition.iNameKey))) {
                return false;
            }
            return true;
        }

        public String toString() {
            return new DateTime(this.iMillis, DateTimeZone.UTC) + Constants.SPACE + this.iStandardOffset + Constants.SPACE + this.iWallOffset;
        }
    }

    public static DateTimeZone readFrom(InputStream inputStream, String str) throws IOException {
        if (inputStream instanceof DataInput) {
            return readFrom((DataInput) inputStream, str);
        }
        return readFrom(new DataInputStream(inputStream), str);
    }

    public static DateTimeZone readFrom(DataInput dataInput, String str) throws IOException {
        switch (dataInput.readUnsignedByte()) {
            case 67:
                return CachedDateTimeZone.forZone(PrecalculatedZone.readFrom(dataInput, str));
            case 70:
                DateTimeZone fixedDateTimeZone = new FixedDateTimeZone(str, dataInput.readUTF(), (int) readMillis(dataInput), (int) readMillis(dataInput));
                if (fixedDateTimeZone.equals(DateTimeZone.UTC)) {
                    return DateTimeZone.UTC;
                }
                return fixedDateTimeZone;
            case 80:
                return PrecalculatedZone.readFrom(dataInput, str);
            default:
                throw new IOException("Invalid encoding");
        }
    }

    static void writeMillis(DataOutput dataOutput, long j) throws IOException {
        long j2;
        if (j % 1800000 == 0) {
            j2 = j / 1800000;
            if (((j2 << 58) >> 58) == j2) {
                dataOutput.writeByte((int) (j2 & 63));
                return;
            }
        }
        if (j % 60000 == 0) {
            j2 = j / 60000;
            if (((j2 << 34) >> 34) == j2) {
                dataOutput.writeInt(((int) (j2 & 1073741823)) | Ints.MAX_POWER_OF_TWO);
                return;
            }
        }
        if (j % 1000 == 0) {
            j2 = j / 1000;
            if (((j2 << 26) >> 26) == j2) {
                dataOutput.writeByte(((int) ((j2 >> 32) & 63)) | 128);
                dataOutput.writeInt((int) (j2 & -1));
                return;
            }
        }
        dataOutput.writeByte(j < 0 ? 255 : JfifUtil.MARKER_SOFn);
        dataOutput.writeLong(j);
    }

    static long readMillis(DataInput dataInput) throws IOException {
        int readUnsignedByte = dataInput.readUnsignedByte();
        switch (readUnsignedByte >> 6) {
            case 1:
                return ((long) (((((readUnsignedByte << 26) >> 2) | (dataInput.readUnsignedByte() << 16)) | (dataInput.readUnsignedByte() << 8)) | dataInput.readUnsignedByte())) * 60000;
            case 2:
                return ((((((((long) readUnsignedByte) << 58) >> 26) | ((long) (dataInput.readUnsignedByte() << 24))) | ((long) (dataInput.readUnsignedByte() << 16))) | ((long) (dataInput.readUnsignedByte() << 8))) | ((long) dataInput.readUnsignedByte())) * 1000;
            case 3:
                return dataInput.readLong();
            default:
                return ((long) ((readUnsignedByte << 26) >> 26)) * 1800000;
        }
    }

    private static DateTimeZone buildFixedZone(String str, String str2, int i, int i2) {
        if ("UTC".equals(str) && str.equals(str2) && i == 0 && i2 == 0) {
            return DateTimeZone.UTC;
        }
        return new FixedDateTimeZone(str, str2, i, i2);
    }

    public DateTimeZoneBuilder addCutover(int i, char c, int i2, int i3, int i4, boolean z, int i5) {
        if (this.iRuleSets.size() > 0) {
            ((RuleSet) this.iRuleSets.get(this.iRuleSets.size() - 1)).setUpperLimit(i, new OfYear(c, i2, i3, i4, z, i5));
        }
        this.iRuleSets.add(new RuleSet());
        return this;
    }

    public DateTimeZoneBuilder setStandardOffset(int i) {
        getLastRuleSet().setStandardOffset(i);
        return this;
    }

    public DateTimeZoneBuilder setFixedSavings(String str, int i) {
        getLastRuleSet().setFixedSavings(str, i);
        return this;
    }

    public DateTimeZoneBuilder addRecurringSavings(String str, int i, int i2, int i3, char c, int i4, int i5, int i6, boolean z, int i7) {
        if (i2 <= i3) {
            getLastRuleSet().addRule(new Rule(new Recurrence(new OfYear(c, i4, i5, i6, z, i7), str, i), i2, i3));
        }
        return this;
    }

    private RuleSet getLastRuleSet() {
        if (this.iRuleSets.size() == 0) {
            addCutover(Integer.MIN_VALUE, 'w', 1, 1, 0, false, 0);
        }
        return (RuleSet) this.iRuleSets.get(this.iRuleSets.size() - 1);
    }

    public DateTimeZone toDateTimeZone(String str, boolean z) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList();
        DateTimeZone dateTimeZone = null;
        long j = Long.MIN_VALUE;
        int size = this.iRuleSets.size();
        int i = 0;
        while (i < size) {
            long j2;
            RuleSet ruleSet = (RuleSet) this.iRuleSets.get(i);
            Transition firstTransition = ruleSet.firstTransition(j);
            if (firstTransition == null) {
                j2 = j;
            } else {
                addTransition(arrayList, firstTransition);
                j = firstTransition.getMillis();
                int saveMillis = firstTransition.getSaveMillis();
                RuleSet ruleSet2 = new RuleSet(ruleSet);
                int i2 = saveMillis;
                DSTZone dSTZone = dateTimeZone;
                while (true) {
                    Transition nextTransition = ruleSet2.nextTransition(j, i2);
                    if (nextTransition == null || (addTransition(arrayList, nextTransition) && dSTZone != null)) {
                        dateTimeZone = dSTZone;
                        j2 = ruleSet2.getUpperLimit(i2);
                    } else {
                        j = nextTransition.getMillis();
                        i2 = nextTransition.getSaveMillis();
                        if (dSTZone == null && i == size - 1) {
                            dSTZone = ruleSet2.buildTailZone(str);
                        }
                    }
                }
                dateTimeZone = dSTZone;
                j2 = ruleSet2.getUpperLimit(i2);
            }
            i++;
            j = j2;
        }
        if (arrayList.size() == 0) {
            if (dateTimeZone != null) {
                return dateTimeZone;
            }
            return buildFixedZone(str, "UTC", 0, 0);
        } else if (arrayList.size() == 1 && dateTimeZone == null) {
            Transition transition = (Transition) arrayList.get(0);
            return buildFixedZone(str, transition.getNameKey(), transition.getWallOffset(), transition.getStandardOffset());
        } else {
            dateTimeZone = PrecalculatedZone.create(str, z, arrayList, dateTimeZone);
            if (dateTimeZone.isCachable()) {
                return CachedDateTimeZone.forZone(dateTimeZone);
            }
            return dateTimeZone;
        }
    }

    private boolean addTransition(ArrayList<Transition> arrayList, Transition transition) {
        int i = 0;
        int size = arrayList.size();
        if (size == 0) {
            arrayList.add(transition);
            return true;
        }
        Transition transition2 = (Transition) arrayList.get(size - 1);
        if (!transition.isTransitionFrom(transition2)) {
            return false;
        }
        if (size >= 2) {
            i = ((Transition) arrayList.get(size - 2)).getWallOffset();
        }
        int wallOffset = transition2.getWallOffset();
        if (((long) wallOffset) + transition.getMillis() == ((long) i) + transition2.getMillis()) {
            return addTransition(arrayList, transition.withMillis(((Transition) arrayList.remove(size - 1)).getMillis()));
        }
        arrayList.add(transition);
        return true;
    }

    public void writeTo(String str, OutputStream outputStream) throws IOException {
        if (outputStream instanceof DataOutput) {
            writeTo(str, (DataOutput) outputStream);
            return;
        }
        DataOutput dataOutputStream = new DataOutputStream(outputStream);
        writeTo(str, dataOutputStream);
        dataOutputStream.flush();
    }

    public void writeTo(String str, DataOutput dataOutput) throws IOException {
        DateTimeZone toDateTimeZone = toDateTimeZone(str, false);
        if (toDateTimeZone instanceof FixedDateTimeZone) {
            dataOutput.writeByte(70);
            dataOutput.writeUTF(toDateTimeZone.getNameKey(0));
            writeMillis(dataOutput, (long) toDateTimeZone.getOffset(0));
            writeMillis(dataOutput, (long) toDateTimeZone.getStandardOffset(0));
            return;
        }
        if (toDateTimeZone instanceof CachedDateTimeZone) {
            dataOutput.writeByte(67);
            toDateTimeZone = ((CachedDateTimeZone) toDateTimeZone).getUncachedZone();
        } else {
            dataOutput.writeByte(80);
        }
        ((PrecalculatedZone) toDateTimeZone).writeTo(dataOutput);
    }
}
