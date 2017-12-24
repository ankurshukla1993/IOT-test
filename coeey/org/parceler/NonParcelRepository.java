package org.parceler;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.parceler.Parcels.ParcelableFactory;
import org.parceler.converter.ArrayListParcelConverter;
import org.parceler.converter.BooleanArrayParcelConverter;
import org.parceler.converter.CharArrayParcelConverter;
import org.parceler.converter.CollectionParcelConverter;
import org.parceler.converter.HashMapParcelConverter;
import org.parceler.converter.HashSetParcelConverter;
import org.parceler.converter.LinkedHashMapParcelConverter;
import org.parceler.converter.LinkedHashSetParcelConverter;
import org.parceler.converter.LinkedListParcelConverter;
import org.parceler.converter.NullableParcelConverter;
import org.parceler.converter.SparseArrayParcelConverter;
import org.parceler.converter.TreeMapParcelConverter;
import org.parceler.converter.TreeSetParcelConverter;

final class NonParcelRepository implements Repository<ParcelableFactory> {
    private static final NonParcelRepository INSTANCE = new NonParcelRepository();
    private final Map<Class, ParcelableFactory> parcelableCollectionFactories = new HashMap();

    private static class ConverterParcelable<T> implements Parcelable, ParcelWrapper<T> {
        private final TypeRangeParcelConverter<T, T> converter;
        private final T value;

        private ConverterParcelable(Parcel parcel, TypeRangeParcelConverter<T, T> converter) {
            this(converter.fromParcel(parcel), (TypeRangeParcelConverter) converter);
        }

        private ConverterParcelable(T value, TypeRangeParcelConverter<T, T> converter) {
            this.converter = converter;
            this.value = value;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            this.converter.toParcel(this.value, parcel);
        }

        public int describeContents() {
            return 0;
        }

        public T getParcel() {
            return this.value;
        }
    }

    public static final class BooleanArrayParcelable extends ConverterParcelable<boolean[]> {
        private static final BooleanArrayParcelConverter CONVERTER = new BooleanArrayParcelConverter();
        public static final BooleanArrayParcelableCreator CREATOR = new BooleanArrayParcelableCreator();

        private static final class BooleanArrayParcelableCreator implements Creator<BooleanArrayParcelable> {
            private BooleanArrayParcelableCreator() {
            }

            public BooleanArrayParcelable createFromParcel(Parcel parcel) {
                return new BooleanArrayParcelable(parcel);
            }

            public BooleanArrayParcelable[] newArray(int size) {
                return new BooleanArrayParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public BooleanArrayParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public BooleanArrayParcelable(boolean[] value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class BooleanArrayParcelableFactory implements ParcelableFactory<boolean[]> {
        private BooleanArrayParcelableFactory() {
        }

        public Parcelable buildParcelable(boolean[] input) {
            return new BooleanArrayParcelable(input);
        }
    }

    public static final class BooleanParcelable extends ConverterParcelable<Boolean> {
        private static final NullableParcelConverter<Boolean> CONVERTER = new C25631();
        public static final BooleanParcelableCreator CREATOR = new BooleanParcelableCreator();

        static class C25631 extends NullableParcelConverter<Boolean> {
            C25631() {
            }

            public Boolean nullSafeFromParcel(Parcel parcel) {
                return Boolean.valueOf(parcel.createBooleanArray()[0]);
            }

            public void nullSafeToParcel(Boolean input, Parcel parcel) {
                parcel.writeBooleanArray(new boolean[]{input.booleanValue()});
            }
        }

        private static final class BooleanParcelableCreator implements Creator<BooleanParcelable> {
            private BooleanParcelableCreator() {
            }

            public BooleanParcelable createFromParcel(Parcel parcel) {
                return new BooleanParcelable(parcel);
            }

            public BooleanParcelable[] newArray(int size) {
                return new BooleanParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public BooleanParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public BooleanParcelable(boolean value) {
            super(Boolean.valueOf(value), CONVERTER);
        }
    }

    private static class BooleanParcelableFactory implements ParcelableFactory<Boolean> {
        private BooleanParcelableFactory() {
        }

        public Parcelable buildParcelable(Boolean input) {
            return new BooleanParcelable(input.booleanValue());
        }
    }

    private static class BundleParcelableFactory implements ParcelableFactory<Bundle> {
        private BundleParcelableFactory() {
        }

        public Parcelable buildParcelable(Bundle input) {
            return input;
        }
    }

    public static final class ByteArrayParcelable extends ConverterParcelable<byte[]> {
        private static final NullableParcelConverter<byte[]> CONVERTER = new C25641();
        public static final ByteArrayParcelableCreator CREATOR = new ByteArrayParcelableCreator();

        static class C25641 extends NullableParcelConverter<byte[]> {
            C25641() {
            }

            public byte[] nullSafeFromParcel(Parcel parcel) {
                return parcel.createByteArray();
            }

            public void nullSafeToParcel(byte[] input, Parcel parcel) {
                parcel.writeByteArray(input);
            }
        }

        private static final class ByteArrayParcelableCreator implements Creator<ByteArrayParcelable> {
            private ByteArrayParcelableCreator() {
            }

            public ByteArrayParcelable createFromParcel(Parcel parcel) {
                return new ByteArrayParcelable(parcel);
            }

            public ByteArrayParcelable[] newArray(int size) {
                return new ByteArrayParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public ByteArrayParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public ByteArrayParcelable(byte[] value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class ByteArrayParcelableFactory implements ParcelableFactory<byte[]> {
        private ByteArrayParcelableFactory() {
        }

        public Parcelable buildParcelable(byte[] input) {
            return new ByteArrayParcelable(input);
        }
    }

    public static final class ByteParcelable extends ConverterParcelable<Byte> {
        private static final NullableParcelConverter<Byte> CONVERTER = new C25651();
        public static final ByteParcelableCreator CREATOR = new ByteParcelableCreator();

        static class C25651 extends NullableParcelConverter<Byte> {
            C25651() {
            }

            public Byte nullSafeFromParcel(Parcel parcel) {
                return Byte.valueOf(parcel.readByte());
            }

            public void nullSafeToParcel(Byte input, Parcel parcel) {
                parcel.writeByte(input.byteValue());
            }
        }

        private static final class ByteParcelableCreator implements Creator<ByteParcelable> {
            private ByteParcelableCreator() {
            }

            public ByteParcelable createFromParcel(Parcel parcel) {
                return new ByteParcelable(parcel);
            }

            public ByteParcelable[] newArray(int size) {
                return new ByteParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public ByteParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public ByteParcelable(Byte value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class ByteParcelableFactory implements ParcelableFactory<Byte> {
        private ByteParcelableFactory() {
        }

        public Parcelable buildParcelable(Byte input) {
            return new ByteParcelable(input);
        }
    }

    public static final class CharArrayParcelable extends ConverterParcelable<char[]> {
        private static final CharArrayParcelConverter CONVERTER = new CharArrayParcelConverter();
        public static final CharArrayParcelableCreator CREATOR = new CharArrayParcelableCreator();

        private static final class CharArrayParcelableCreator implements Creator<CharArrayParcelable> {
            private CharArrayParcelableCreator() {
            }

            public CharArrayParcelable createFromParcel(Parcel parcel) {
                return new CharArrayParcelable(parcel);
            }

            public CharArrayParcelable[] newArray(int size) {
                return new CharArrayParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public CharArrayParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public CharArrayParcelable(char[] value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class CharArrayParcelableFactory implements ParcelableFactory<char[]> {
        private CharArrayParcelableFactory() {
        }

        public Parcelable buildParcelable(char[] input) {
            return new CharArrayParcelable(input);
        }
    }

    public static final class CharacterParcelable extends ConverterParcelable<Character> {
        private static final NullableParcelConverter<Character> CONVERTER = new C25661();
        public static final CharacterParcelableCreator CREATOR = new CharacterParcelableCreator();

        static class C25661 extends NullableParcelConverter<Character> {
            C25661() {
            }

            public Character nullSafeFromParcel(Parcel parcel) {
                return Character.valueOf(parcel.createCharArray()[0]);
            }

            public void nullSafeToParcel(Character input, Parcel parcel) {
                parcel.writeCharArray(new char[]{input.charValue()});
            }
        }

        private static final class CharacterParcelableCreator implements Creator<CharacterParcelable> {
            private CharacterParcelableCreator() {
            }

            public CharacterParcelable createFromParcel(Parcel parcel) {
                return new CharacterParcelable(parcel);
            }

            public CharacterParcelable[] newArray(int size) {
                return new CharacterParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public CharacterParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public CharacterParcelable(Character value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class CharacterParcelableFactory implements ParcelableFactory<Character> {
        private CharacterParcelableFactory() {
        }

        public Parcelable buildParcelable(Character input) {
            return new CharacterParcelable(input);
        }
    }

    public static final class CollectionParcelable extends ConverterParcelable<Collection> {
        private static final CollectionParcelConverter CONVERTER = new C25671();
        public static final CollectionParcelableCreator CREATOR = new CollectionParcelableCreator();

        static class C25671 extends ArrayListParcelConverter {
            C25671() {
            }

            public Object itemFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(CollectionParcelable.class.getClassLoader()));
            }

            public void itemToParcel(Object input, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(input), 0);
            }
        }

        private static final class CollectionParcelableCreator implements Creator<CollectionParcelable> {
            private CollectionParcelableCreator() {
            }

            public CollectionParcelable createFromParcel(Parcel parcel) {
                return new CollectionParcelable(parcel);
            }

            public CollectionParcelable[] newArray(int size) {
                return new CollectionParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public CollectionParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public CollectionParcelable(Collection value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class CollectionParcelableFactory implements ParcelableFactory<Collection> {
        private CollectionParcelableFactory() {
        }

        public Parcelable buildParcelable(Collection input) {
            return new CollectionParcelable(input);
        }
    }

    public static final class DoubleParcelable extends ConverterParcelable<Double> {
        private static final NullableParcelConverter<Double> CONVERTER = new C25681();
        public static final DoubleParcelableCreator CREATOR = new DoubleParcelableCreator();

        static class C25681 extends NullableParcelConverter<Double> {
            C25681() {
            }

            public Double nullSafeFromParcel(Parcel parcel) {
                return Double.valueOf(parcel.readDouble());
            }

            public void nullSafeToParcel(Double input, Parcel parcel) {
                parcel.writeDouble(input.doubleValue());
            }
        }

        private static final class DoubleParcelableCreator implements Creator<DoubleParcelable> {
            private DoubleParcelableCreator() {
            }

            public DoubleParcelable createFromParcel(Parcel parcel) {
                return new DoubleParcelable(parcel);
            }

            public DoubleParcelable[] newArray(int size) {
                return new DoubleParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public DoubleParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public DoubleParcelable(Double value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class DoubleParcelableFactory implements ParcelableFactory<Double> {
        private DoubleParcelableFactory() {
        }

        public Parcelable buildParcelable(Double input) {
            return new DoubleParcelable(input);
        }
    }

    public static final class FloatParcelable extends ConverterParcelable<Float> {
        private static final NullableParcelConverter<Float> CONVERTER = new C25691();
        public static final FloatParcelableCreator CREATOR = new FloatParcelableCreator();

        static class C25691 extends NullableParcelConverter<Float> {
            C25691() {
            }

            public Float nullSafeFromParcel(Parcel parcel) {
                return Float.valueOf(parcel.readFloat());
            }

            public void nullSafeToParcel(Float input, Parcel parcel) {
                parcel.writeFloat(input.floatValue());
            }
        }

        private static final class FloatParcelableCreator implements Creator<FloatParcelable> {
            private FloatParcelableCreator() {
            }

            public FloatParcelable createFromParcel(Parcel parcel) {
                return new FloatParcelable(parcel);
            }

            public FloatParcelable[] newArray(int size) {
                return new FloatParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public FloatParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public FloatParcelable(Float value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class FloatParcelableFactory implements ParcelableFactory<Float> {
        private FloatParcelableFactory() {
        }

        public Parcelable buildParcelable(Float input) {
            return new FloatParcelable(input);
        }
    }

    public static final class IBinderParcelable extends ConverterParcelable<IBinder> {
        private static final NullableParcelConverter<IBinder> CONVERTER = new C25701();
        public static final IBinderParcelableCreator CREATOR = new IBinderParcelableCreator();

        static class C25701 extends NullableParcelConverter<IBinder> {
            C25701() {
            }

            public IBinder nullSafeFromParcel(Parcel parcel) {
                return parcel.readStrongBinder();
            }

            public void nullSafeToParcel(IBinder input, Parcel parcel) {
                parcel.writeStrongBinder(input);
            }
        }

        private static final class IBinderParcelableCreator implements Creator<IBinderParcelable> {
            private IBinderParcelableCreator() {
            }

            public IBinderParcelable createFromParcel(Parcel parcel) {
                return new IBinderParcelable(parcel);
            }

            public IBinderParcelable[] newArray(int size) {
                return new IBinderParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public IBinderParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public IBinderParcelable(IBinder value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class IBinderParcelableFactory implements ParcelableFactory<IBinder> {
        private IBinderParcelableFactory() {
        }

        public Parcelable buildParcelable(IBinder input) {
            return new IBinderParcelable(input);
        }
    }

    public static final class IntegerParcelable extends ConverterParcelable<Integer> {
        private static final NullableParcelConverter<Integer> CONVERTER = new C25711();
        public static final IntegerParcelableCreator CREATOR = new IntegerParcelableCreator();

        static class C25711 extends NullableParcelConverter<Integer> {
            C25711() {
            }

            public Integer nullSafeFromParcel(Parcel parcel) {
                return Integer.valueOf(parcel.readInt());
            }

            public void nullSafeToParcel(Integer input, Parcel parcel) {
                parcel.writeInt(input.intValue());
            }
        }

        private static final class IntegerParcelableCreator implements Creator<IntegerParcelable> {
            private IntegerParcelableCreator() {
            }

            public IntegerParcelable createFromParcel(Parcel parcel) {
                return new IntegerParcelable(parcel);
            }

            public IntegerParcelable[] newArray(int size) {
                return new IntegerParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public IntegerParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public IntegerParcelable(Integer value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class IntegerParcelableFactory implements ParcelableFactory<Integer> {
        private IntegerParcelableFactory() {
        }

        public Parcelable buildParcelable(Integer input) {
            return new IntegerParcelable(input);
        }
    }

    public static final class LinkedHashMapParcelable extends ConverterParcelable<LinkedHashMap> {
        private static final LinkedHashMapParcelConverter CONVERTER = new C25721();
        public static final LinkedHashMapParcelableCreator CREATOR = new LinkedHashMapParcelableCreator();

        static class C25721 extends LinkedHashMapParcelConverter {
            C25721() {
            }

            public void mapKeyToParcel(Object key, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(key), 0);
            }

            public void mapValueToParcel(Object value, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(value), 0);
            }

            public Object mapKeyFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }

            public Object mapValueFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }
        }

        private static final class LinkedHashMapParcelableCreator implements Creator<LinkedHashMapParcelable> {
            private LinkedHashMapParcelableCreator() {
            }

            public LinkedHashMapParcelable createFromParcel(Parcel parcel$$17) {
                return new LinkedHashMapParcelable(parcel$$17);
            }

            public LinkedHashMapParcelable[] newArray(int size) {
                return new LinkedHashMapParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LinkedHashMapParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public LinkedHashMapParcelable(LinkedHashMap value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class LinkedHashMapParcelableFactory implements ParcelableFactory<LinkedHashMap> {
        private LinkedHashMapParcelableFactory() {
        }

        public Parcelable buildParcelable(LinkedHashMap input) {
            return new LinkedHashMapParcelable(input);
        }
    }

    public static final class LinkedHashSetParcelable extends ConverterParcelable<LinkedHashSet> {
        private static final LinkedHashSetParcelConverter CONVERTER = new C25731();
        public static final LinkedHashSetParcelableCreator CREATOR = new LinkedHashSetParcelableCreator();

        static class C25731 extends LinkedHashSetParcelConverter {
            C25731() {
            }

            public Object itemFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(LinkedHashSetParcelable.class.getClassLoader()));
            }

            public void itemToParcel(Object input, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(input), 0);
            }
        }

        private static final class LinkedHashSetParcelableCreator implements Creator<LinkedHashSetParcelable> {
            private LinkedHashSetParcelableCreator() {
            }

            public LinkedHashSetParcelable createFromParcel(Parcel parcel) {
                return new LinkedHashSetParcelable(parcel);
            }

            public LinkedHashSetParcelable[] newArray(int size) {
                return new LinkedHashSetParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LinkedHashSetParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public LinkedHashSetParcelable(LinkedHashSet value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class LinkedHashSetParcelableFactory implements ParcelableFactory<LinkedHashSet> {
        private LinkedHashSetParcelableFactory() {
        }

        public Parcelable buildParcelable(LinkedHashSet input) {
            return new LinkedHashSetParcelable(input);
        }
    }

    public static final class LinkedListParcelable extends ConverterParcelable<LinkedList> {
        private static final LinkedListParcelConverter CONVERTER = new C25741();
        public static final LinkedListParcelableCreator CREATOR = new LinkedListParcelableCreator();

        static class C25741 extends LinkedListParcelConverter {
            C25741() {
            }

            public Object itemFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(LinkedListParcelable.class.getClassLoader()));
            }

            public void itemToParcel(Object input, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(input), 0);
            }
        }

        private static final class LinkedListParcelableCreator implements Creator<LinkedListParcelable> {
            private LinkedListParcelableCreator() {
            }

            public LinkedListParcelable createFromParcel(Parcel parcel) {
                return new LinkedListParcelable(parcel);
            }

            public LinkedListParcelable[] newArray(int size) {
                return new LinkedListParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LinkedListParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public LinkedListParcelable(LinkedList value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class LinkedListParcelableFactory implements ParcelableFactory<LinkedList> {
        private LinkedListParcelableFactory() {
        }

        public Parcelable buildParcelable(LinkedList input) {
            return new LinkedListParcelable(input);
        }
    }

    public static final class ListParcelable extends ConverterParcelable<List> {
        private static final ArrayListParcelConverter CONVERTER = new C25751();
        public static final ListParcelableCreator CREATOR = new ListParcelableCreator();

        static class C25751 extends ArrayListParcelConverter {
            C25751() {
            }

            public Object itemFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(ListParcelable.class.getClassLoader()));
            }

            public void itemToParcel(Object input, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(input), 0);
            }
        }

        private static final class ListParcelableCreator implements Creator<ListParcelable> {
            private ListParcelableCreator() {
            }

            public ListParcelable createFromParcel(Parcel parcel) {
                return new ListParcelable(parcel);
            }

            public ListParcelable[] newArray(int size) {
                return new ListParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public ListParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public ListParcelable(List value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class ListParcelableFactory implements ParcelableFactory<List> {
        private ListParcelableFactory() {
        }

        public Parcelable buildParcelable(List input) {
            return new ListParcelable(input);
        }
    }

    public static final class LongParcelable extends ConverterParcelable<Long> {
        private static final NullableParcelConverter<Long> CONVERTER = new C25761();
        public static final LongParcelableCreator CREATOR = new LongParcelableCreator();

        static class C25761 extends NullableParcelConverter<Long> {
            C25761() {
            }

            public Long nullSafeFromParcel(Parcel parcel) {
                return Long.valueOf(parcel.readLong());
            }

            public void nullSafeToParcel(Long input, Parcel parcel) {
                parcel.writeLong(input.longValue());
            }
        }

        private static final class LongParcelableCreator implements Creator<LongParcelable> {
            private LongParcelableCreator() {
            }

            public LongParcelable createFromParcel(Parcel parcel) {
                return new LongParcelable(parcel);
            }

            public LongParcelable[] newArray(int size) {
                return new LongParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public LongParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public LongParcelable(Long value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class LongParcelableFactory implements ParcelableFactory<Long> {
        private LongParcelableFactory() {
        }

        public Parcelable buildParcelable(Long input) {
            return new LongParcelable(input);
        }
    }

    public static final class MapParcelable extends ConverterParcelable<Map> {
        private static final HashMapParcelConverter CONVERTER = new C25771();
        public static final MapParcelableCreator CREATOR = new MapParcelableCreator();

        static class C25771 extends HashMapParcelConverter {
            C25771() {
            }

            public void mapKeyToParcel(Object key, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(key), 0);
            }

            public void mapValueToParcel(Object value, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(value), 0);
            }

            public Object mapKeyFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }

            public Object mapValueFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }
        }

        private static final class MapParcelableCreator implements Creator<MapParcelable> {
            private MapParcelableCreator() {
            }

            public MapParcelable createFromParcel(Parcel parcel$$17) {
                return new MapParcelable(parcel$$17);
            }

            public MapParcelable[] newArray(int size) {
                return new MapParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public MapParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public MapParcelable(Map value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class MapParcelableFactory implements ParcelableFactory<Map> {
        private MapParcelableFactory() {
        }

        public Parcelable buildParcelable(Map input) {
            return new MapParcelable(input);
        }
    }

    public static final class ParcelableParcelable implements Parcelable, ParcelWrapper<Parcelable> {
        public static final ParcelableParcelableCreator CREATOR = new ParcelableParcelableCreator();
        private Parcelable parcelable;

        private static final class ParcelableParcelableCreator implements Creator<ParcelableParcelable> {
            private ParcelableParcelableCreator() {
            }

            public ParcelableParcelable createFromParcel(Parcel parcel) {
                return new ParcelableParcelable(parcel);
            }

            public ParcelableParcelable[] newArray(int size) {
                return new ParcelableParcelable[size];
            }
        }

        private ParcelableParcelable(Parcel parcel) {
            this.parcelable = parcel.readParcelable(ParcelableParcelable.class.getClassLoader());
        }

        private ParcelableParcelable(Parcelable parcelable) {
            this.parcelable = parcelable;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeParcelable(this.parcelable, flags);
        }

        public int describeContents() {
            return 0;
        }

        public Parcelable getParcel() {
            return this.parcelable;
        }
    }

    static class ParcelableParcelableFactory implements ParcelableFactory<Parcelable> {
        ParcelableParcelableFactory() {
        }

        public Parcelable buildParcelable(Parcelable input) {
            return new ParcelableParcelable(input);
        }
    }

    public static final class SetParcelable extends ConverterParcelable<Set> {
        private static final HashSetParcelConverter CONVERTER = new C25781();
        public static final SetParcelableCreator CREATOR = new SetParcelableCreator();

        static class C25781 extends HashSetParcelConverter {
            C25781() {
            }

            public Object itemFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(SetParcelable.class.getClassLoader()));
            }

            public void itemToParcel(Object input, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(input), 0);
            }
        }

        private static final class SetParcelableCreator implements Creator<SetParcelable> {
            private SetParcelableCreator() {
            }

            public SetParcelable createFromParcel(Parcel parcel) {
                return new SetParcelable(parcel);
            }

            public SetParcelable[] newArray(int size) {
                return new SetParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public SetParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public SetParcelable(Set value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class SetParcelableFactory implements ParcelableFactory<Set> {
        private SetParcelableFactory() {
        }

        public Parcelable buildParcelable(Set input) {
            return new SetParcelable(input);
        }
    }

    public static final class SparseArrayParcelable extends ConverterParcelable<SparseArray> {
        private static final SparseArrayParcelConverter CONVERTER = new C25791();
        public static final SparseArrayCreator CREATOR = new SparseArrayCreator();

        static class C25791 extends SparseArrayParcelConverter {
            C25791() {
            }

            public Object itemFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(SparseArrayParcelable.class.getClassLoader()));
            }

            public void itemToParcel(Object input, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(input), 0);
            }
        }

        private static final class SparseArrayCreator implements Creator<SparseArrayParcelable> {
            private SparseArrayCreator() {
            }

            public SparseArrayParcelable createFromParcel(Parcel parcel) {
                return new SparseArrayParcelable(parcel);
            }

            public SparseArrayParcelable[] newArray(int size) {
                return new SparseArrayParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public SparseArrayParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public SparseArrayParcelable(SparseArray value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class SparseArrayParcelableFactory implements ParcelableFactory<SparseArray> {
        private SparseArrayParcelableFactory() {
        }

        public Parcelable buildParcelable(SparseArray input) {
            return new SparseArrayParcelable(input);
        }
    }

    public static final class SparseBooleanArrayParcelable extends ConverterParcelable<SparseBooleanArray> {
        private static final NullableParcelConverter<SparseBooleanArray> CONVERTER = new C25801();
        public static final SparseBooleanArrayCreator CREATOR = new SparseBooleanArrayCreator();

        static class C25801 extends NullableParcelConverter<SparseBooleanArray> {
            C25801() {
            }

            public SparseBooleanArray nullSafeFromParcel(Parcel parcel) {
                return parcel.readSparseBooleanArray();
            }

            public void nullSafeToParcel(SparseBooleanArray input, Parcel parcel) {
                parcel.writeSparseBooleanArray(input);
            }
        }

        private static final class SparseBooleanArrayCreator implements Creator<SparseBooleanArrayParcelable> {
            private SparseBooleanArrayCreator() {
            }

            public SparseBooleanArrayParcelable createFromParcel(Parcel parcel) {
                return new SparseBooleanArrayParcelable(parcel);
            }

            public SparseBooleanArrayParcelable[] newArray(int size) {
                return new SparseBooleanArrayParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public SparseBooleanArrayParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public SparseBooleanArrayParcelable(SparseBooleanArray value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class SparseBooleanArrayParcelableFactory implements ParcelableFactory<SparseBooleanArray> {
        private SparseBooleanArrayParcelableFactory() {
        }

        public Parcelable buildParcelable(SparseBooleanArray input) {
            return new SparseBooleanArrayParcelable(input);
        }
    }

    public static final class StringParcelable implements Parcelable, ParcelWrapper<String> {
        public static final StringParcelableCreator CREATOR = new StringParcelableCreator();
        private String contents;

        private static final class StringParcelableCreator implements Creator<StringParcelable> {
            private StringParcelableCreator() {
            }

            public StringParcelable createFromParcel(Parcel parcel) {
                return new StringParcelable(parcel);
            }

            public StringParcelable[] newArray(int size) {
                return new StringParcelable[size];
            }
        }

        private StringParcelable(Parcel parcel) {
            this.contents = parcel.readString();
        }

        private StringParcelable(String contents) {
            this.contents = contents;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(this.contents);
        }

        public int describeContents() {
            return 0;
        }

        public String getParcel() {
            return this.contents;
        }
    }

    private static class StringParcelableFactory implements ParcelableFactory<String> {
        private StringParcelableFactory() {
        }

        public Parcelable buildParcelable(String input) {
            return new StringParcelable(input);
        }
    }

    public static final class TreeMapParcelable extends ConverterParcelable<Map> {
        private static final TreeMapParcelConverter CONVERTER = new C25811();
        public static final TreeMapParcelableCreator CREATOR = new TreeMapParcelableCreator();

        static class C25811 extends TreeMapParcelConverter {
            C25811() {
            }

            public void mapKeyToParcel(Object key, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(key), 0);
            }

            public void mapValueToParcel(Object value, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(value), 0);
            }

            public Object mapKeyFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }

            public Object mapValueFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(MapParcelable.class.getClassLoader()));
            }
        }

        private static final class TreeMapParcelableCreator implements Creator<TreeMapParcelable> {
            private TreeMapParcelableCreator() {
            }

            public TreeMapParcelable createFromParcel(Parcel parcel$$17) {
                return new TreeMapParcelable(parcel$$17);
            }

            public TreeMapParcelable[] newArray(int size) {
                return new TreeMapParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public TreeMapParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public TreeMapParcelable(Map value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class TreeMapParcelableFactory implements ParcelableFactory<Map> {
        private TreeMapParcelableFactory() {
        }

        public Parcelable buildParcelable(Map input) {
            return new TreeMapParcelable(input);
        }
    }

    public static final class TreeSetParcelable extends ConverterParcelable<Set> {
        private static final TreeSetParcelConverter CONVERTER = new C25821();
        public static final TreeSetParcelableCreator CREATOR = new TreeSetParcelableCreator();

        static class C25821 extends TreeSetParcelConverter {
            C25821() {
            }

            public Object itemFromParcel(Parcel parcel) {
                return Parcels.unwrap(parcel.readParcelable(TreeSetParcelable.class.getClassLoader()));
            }

            public void itemToParcel(Object input, Parcel parcel) {
                parcel.writeParcelable(Parcels.wrap(input), 0);
            }
        }

        private static final class TreeSetParcelableCreator implements Creator<TreeSetParcelable> {
            private TreeSetParcelableCreator() {
            }

            public TreeSetParcelable createFromParcel(Parcel parcel) {
                return new TreeSetParcelable(parcel);
            }

            public TreeSetParcelable[] newArray(int size) {
                return new TreeSetParcelable[size];
            }
        }

        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }

        public TreeSetParcelable(Parcel parcel) {
            super(parcel, CONVERTER);
        }

        public TreeSetParcelable(Set value) {
            super((Object) value, CONVERTER);
        }
    }

    private static class TreeSetParcelableFactory implements ParcelableFactory<Set> {
        private TreeSetParcelableFactory() {
        }

        public Parcelable buildParcelable(Set input) {
            return new TreeSetParcelable(input);
        }
    }

    private NonParcelRepository() {
        this.parcelableCollectionFactories.put(Collection.class, new CollectionParcelableFactory());
        this.parcelableCollectionFactories.put(List.class, new ListParcelableFactory());
        this.parcelableCollectionFactories.put(ArrayList.class, new ListParcelableFactory());
        this.parcelableCollectionFactories.put(Set.class, new SetParcelableFactory());
        this.parcelableCollectionFactories.put(HashSet.class, new SetParcelableFactory());
        this.parcelableCollectionFactories.put(TreeSet.class, new TreeSetParcelableFactory());
        this.parcelableCollectionFactories.put(SparseArray.class, new SparseArrayParcelableFactory());
        this.parcelableCollectionFactories.put(Map.class, new MapParcelableFactory());
        this.parcelableCollectionFactories.put(HashMap.class, new MapParcelableFactory());
        this.parcelableCollectionFactories.put(TreeMap.class, new TreeMapParcelableFactory());
        this.parcelableCollectionFactories.put(Integer.class, new IntegerParcelableFactory());
        this.parcelableCollectionFactories.put(Long.class, new LongParcelableFactory());
        this.parcelableCollectionFactories.put(Double.class, new DoubleParcelableFactory());
        this.parcelableCollectionFactories.put(Float.class, new FloatParcelableFactory());
        this.parcelableCollectionFactories.put(Byte.class, new ByteParcelableFactory());
        this.parcelableCollectionFactories.put(String.class, new StringParcelableFactory());
        this.parcelableCollectionFactories.put(Character.class, new CharacterParcelableFactory());
        this.parcelableCollectionFactories.put(Boolean.class, new BooleanParcelableFactory());
        this.parcelableCollectionFactories.put(byte[].class, new ByteArrayParcelableFactory());
        this.parcelableCollectionFactories.put(char[].class, new CharArrayParcelableFactory());
        this.parcelableCollectionFactories.put(boolean[].class, new BooleanArrayParcelableFactory());
        this.parcelableCollectionFactories.put(IBinder.class, new IBinderParcelableFactory());
        this.parcelableCollectionFactories.put(Bundle.class, new BundleParcelableFactory());
        this.parcelableCollectionFactories.put(SparseBooleanArray.class, new SparseBooleanArrayParcelableFactory());
        this.parcelableCollectionFactories.put(LinkedList.class, new LinkedListParcelableFactory());
        this.parcelableCollectionFactories.put(LinkedHashMap.class, new LinkedHashMapParcelableFactory());
        this.parcelableCollectionFactories.put(SortedMap.class, new TreeMapParcelableFactory());
        this.parcelableCollectionFactories.put(SortedSet.class, new TreeSetParcelableFactory());
        this.parcelableCollectionFactories.put(LinkedHashSet.class, new LinkedHashSetParcelableFactory());
    }

    public static NonParcelRepository getInstance() {
        return INSTANCE;
    }

    public Map<Class, ParcelableFactory> get() {
        return this.parcelableCollectionFactories;
    }
}
