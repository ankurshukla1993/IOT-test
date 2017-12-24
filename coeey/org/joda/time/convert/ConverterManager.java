package org.joda.time.convert;

import org.joda.time.JodaTimePermission;

public final class ConverterManager {
    private static ConverterManager INSTANCE;
    private ConverterSet iDurationConverters = new ConverterSet(new Converter[]{ReadableDurationConverter.INSTANCE, ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iInstantConverters = new ConverterSet(new Converter[]{ReadableInstantConverter.INSTANCE, StringConverter.INSTANCE, CalendarConverter.INSTANCE, DateConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iIntervalConverters = new ConverterSet(new Converter[]{ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iPartialConverters = new ConverterSet(new Converter[]{ReadablePartialConverter.INSTANCE, ReadableInstantConverter.INSTANCE, StringConverter.INSTANCE, CalendarConverter.INSTANCE, DateConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iPeriodConverters = new ConverterSet(new Converter[]{ReadableDurationConverter.INSTANCE, ReadablePeriodConverter.INSTANCE, ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, NullConverter.INSTANCE});

    public static ConverterManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConverterManager();
        }
        return INSTANCE;
    }

    protected ConverterManager() {
    }

    public InstantConverter getInstantConverter(Object obj) {
        InstantConverter instantConverter = (InstantConverter) this.iInstantConverters.select(obj == null ? null : obj.getClass());
        if (instantConverter != null) {
            return instantConverter;
        }
        throw new IllegalArgumentException("No instant converter found for type: " + (obj == null ? "null" : obj.getClass().getName()));
    }

    public InstantConverter[] getInstantConverters() {
        ConverterSet converterSet = this.iInstantConverters;
        Object[] objArr = new InstantConverter[converterSet.size()];
        converterSet.copyInto(objArr);
        return objArr;
    }

    public InstantConverter addInstantConverter(InstantConverter instantConverter) throws SecurityException {
        checkAlterInstantConverters();
        if (instantConverter == null) {
            return null;
        }
        Converter[] converterArr = new InstantConverter[1];
        this.iInstantConverters = this.iInstantConverters.add(instantConverter, converterArr);
        return converterArr[0];
    }

    public InstantConverter removeInstantConverter(InstantConverter instantConverter) throws SecurityException {
        checkAlterInstantConverters();
        if (instantConverter == null) {
            return null;
        }
        Converter[] converterArr = new InstantConverter[1];
        this.iInstantConverters = this.iInstantConverters.remove((Converter) instantConverter, converterArr);
        return converterArr[0];
    }

    private void checkAlterInstantConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterInstantConverters"));
        }
    }

    public PartialConverter getPartialConverter(Object obj) {
        PartialConverter partialConverter = (PartialConverter) this.iPartialConverters.select(obj == null ? null : obj.getClass());
        if (partialConverter != null) {
            return partialConverter;
        }
        throw new IllegalArgumentException("No partial converter found for type: " + (obj == null ? "null" : obj.getClass().getName()));
    }

    public PartialConverter[] getPartialConverters() {
        ConverterSet converterSet = this.iPartialConverters;
        Object[] objArr = new PartialConverter[converterSet.size()];
        converterSet.copyInto(objArr);
        return objArr;
    }

    public PartialConverter addPartialConverter(PartialConverter partialConverter) throws SecurityException {
        checkAlterPartialConverters();
        if (partialConverter == null) {
            return null;
        }
        Converter[] converterArr = new PartialConverter[1];
        this.iPartialConverters = this.iPartialConverters.add(partialConverter, converterArr);
        return converterArr[0];
    }

    public PartialConverter removePartialConverter(PartialConverter partialConverter) throws SecurityException {
        checkAlterPartialConverters();
        if (partialConverter == null) {
            return null;
        }
        Converter[] converterArr = new PartialConverter[1];
        this.iPartialConverters = this.iPartialConverters.remove((Converter) partialConverter, converterArr);
        return converterArr[0];
    }

    private void checkAlterPartialConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterPartialConverters"));
        }
    }

    public DurationConverter getDurationConverter(Object obj) {
        DurationConverter durationConverter = (DurationConverter) this.iDurationConverters.select(obj == null ? null : obj.getClass());
        if (durationConverter != null) {
            return durationConverter;
        }
        throw new IllegalArgumentException("No duration converter found for type: " + (obj == null ? "null" : obj.getClass().getName()));
    }

    public DurationConverter[] getDurationConverters() {
        ConverterSet converterSet = this.iDurationConverters;
        Object[] objArr = new DurationConverter[converterSet.size()];
        converterSet.copyInto(objArr);
        return objArr;
    }

    public DurationConverter addDurationConverter(DurationConverter durationConverter) throws SecurityException {
        checkAlterDurationConverters();
        if (durationConverter == null) {
            return null;
        }
        Converter[] converterArr = new DurationConverter[1];
        this.iDurationConverters = this.iDurationConverters.add(durationConverter, converterArr);
        return converterArr[0];
    }

    public DurationConverter removeDurationConverter(DurationConverter durationConverter) throws SecurityException {
        checkAlterDurationConverters();
        if (durationConverter == null) {
            return null;
        }
        Converter[] converterArr = new DurationConverter[1];
        this.iDurationConverters = this.iDurationConverters.remove((Converter) durationConverter, converterArr);
        return converterArr[0];
    }

    private void checkAlterDurationConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterDurationConverters"));
        }
    }

    public PeriodConverter getPeriodConverter(Object obj) {
        PeriodConverter periodConverter = (PeriodConverter) this.iPeriodConverters.select(obj == null ? null : obj.getClass());
        if (periodConverter != null) {
            return periodConverter;
        }
        throw new IllegalArgumentException("No period converter found for type: " + (obj == null ? "null" : obj.getClass().getName()));
    }

    public PeriodConverter[] getPeriodConverters() {
        ConverterSet converterSet = this.iPeriodConverters;
        Object[] objArr = new PeriodConverter[converterSet.size()];
        converterSet.copyInto(objArr);
        return objArr;
    }

    public PeriodConverter addPeriodConverter(PeriodConverter periodConverter) throws SecurityException {
        checkAlterPeriodConverters();
        if (periodConverter == null) {
            return null;
        }
        Converter[] converterArr = new PeriodConverter[1];
        this.iPeriodConverters = this.iPeriodConverters.add(periodConverter, converterArr);
        return converterArr[0];
    }

    public PeriodConverter removePeriodConverter(PeriodConverter periodConverter) throws SecurityException {
        checkAlterPeriodConverters();
        if (periodConverter == null) {
            return null;
        }
        Converter[] converterArr = new PeriodConverter[1];
        this.iPeriodConverters = this.iPeriodConverters.remove((Converter) periodConverter, converterArr);
        return converterArr[0];
    }

    private void checkAlterPeriodConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterPeriodConverters"));
        }
    }

    public IntervalConverter getIntervalConverter(Object obj) {
        IntervalConverter intervalConverter = (IntervalConverter) this.iIntervalConverters.select(obj == null ? null : obj.getClass());
        if (intervalConverter != null) {
            return intervalConverter;
        }
        throw new IllegalArgumentException("No interval converter found for type: " + (obj == null ? "null" : obj.getClass().getName()));
    }

    public IntervalConverter[] getIntervalConverters() {
        ConverterSet converterSet = this.iIntervalConverters;
        Object[] objArr = new IntervalConverter[converterSet.size()];
        converterSet.copyInto(objArr);
        return objArr;
    }

    public IntervalConverter addIntervalConverter(IntervalConverter intervalConverter) throws SecurityException {
        checkAlterIntervalConverters();
        if (intervalConverter == null) {
            return null;
        }
        Converter[] converterArr = new IntervalConverter[1];
        this.iIntervalConverters = this.iIntervalConverters.add(intervalConverter, converterArr);
        return converterArr[0];
    }

    public IntervalConverter removeIntervalConverter(IntervalConverter intervalConverter) throws SecurityException {
        checkAlterIntervalConverters();
        if (intervalConverter == null) {
            return null;
        }
        Converter[] converterArr = new IntervalConverter[1];
        this.iIntervalConverters = this.iIntervalConverters.remove((Converter) intervalConverter, converterArr);
        return converterArr[0];
    }

    private void checkAlterIntervalConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterIntervalConverters"));
        }
    }

    public String toString() {
        return "ConverterManager[" + this.iInstantConverters.size() + " instant," + this.iPartialConverters.size() + " partial," + this.iDurationConverters.size() + " duration," + this.iPeriodConverters.size() + " period," + this.iIntervalConverters.size() + " interval]";
    }
}
