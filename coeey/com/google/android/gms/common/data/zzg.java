package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzg<T> extends AbstractDataBuffer<T> {
    private boolean zzftu = false;
    private ArrayList<Integer> zzftv;

    protected zzg(DataHolder dataHolder) {
        super(dataHolder);
    }

    private final void zzajp() {
        synchronized (this) {
            if (!this.zzftu) {
                int i = this.zzfnz.zzftm;
                this.zzftv = new ArrayList();
                if (i > 0) {
                    this.zzftv.add(Integer.valueOf(0));
                    String zzajo = zzajo();
                    String zzd = this.zzfnz.zzd(zzajo, 0, this.zzfnz.zzbz(0));
                    int i2 = 1;
                    while (i2 < i) {
                        int zzbz = this.zzfnz.zzbz(i2);
                        String zzd2 = this.zzfnz.zzd(zzajo, i2, zzbz);
                        if (zzd2 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(zzajo).length() + 78).append("Missing value for markerColumn: ").append(zzajo).append(", at row: ").append(i2).append(", for window: ").append(zzbz).toString());
                        }
                        if (zzd2.equals(zzd)) {
                            zzd2 = zzd;
                        } else {
                            this.zzftv.add(Integer.valueOf(i2));
                        }
                        i2++;
                        zzd = zzd2;
                    }
                }
                this.zzftu = true;
            }
        }
    }

    private final int zzcc(int i) {
        if (i >= 0 && i < this.zzftv.size()) {
            return ((Integer) this.zzftv.get(i)).intValue();
        }
        throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
    }

    public final T get(int i) {
        int i2;
        zzajp();
        int zzcc = zzcc(i);
        if (i < 0 || i == this.zzftv.size()) {
            i2 = 0;
        } else {
            i2 = i == this.zzftv.size() + -1 ? this.zzfnz.zzftm - ((Integer) this.zzftv.get(i)).intValue() : ((Integer) this.zzftv.get(i + 1)).intValue() - ((Integer) this.zzftv.get(i)).intValue();
            if (i2 == 1) {
                this.zzfnz.zzbz(zzcc(i));
            }
        }
        return zzl(zzcc, i2);
    }

    public int getCount() {
        zzajp();
        return this.zzftv.size();
    }

    protected abstract String zzajo();

    protected abstract T zzl(int i, int i2);
}
