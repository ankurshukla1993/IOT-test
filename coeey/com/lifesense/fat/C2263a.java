package com.lifesense.fat;

public final class C2263a extends FatPercentage {
    public final double getBmi(double d, double d2) {
        return (d / d2) / d2;
    }

    public final double getBodyWater(int i, double d, int i2, double d2, boolean z) {
        if (!z) {
            switch (i) {
                case 0:
                    return 87.51d + (((-1.162d * d2) - (0.00813d * d)) + (0.07594d * ((double) i2)));
                case 1:
                    return 77.721d + (((-1.148d * d2) - (0.00573d * d)) + (0.06448d * ((double) i2)));
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return 91.305d + (((-1.191d * d2) - (0.00768d * d)) + (0.08148d * ((double) i2)));
            case 1:
                return 80.286d + (((-1.132d * d2) - (0.0052d * d)) + (0.07152d * ((double) i2)));
        }
        return 0.0d;
    }

    public final double getBone(int i, double d, int i2, double d2, boolean z) {
        if (!z) {
            switch (i) {
                case 0:
                    return 7.829d + (((-0.0855d * d2) - (5.92E-4d * d)) - (((double) i2) * 0.0389d));
                case 1:
                    return 7.98d + (((-0.0973d * d2) - (4.84E-4d * d)) - (0.036d * ((double) i2)));
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return 8.091d + (((-0.0856d * d2) - (5.25E-4d * d)) - (0.0403d * ((double) i2)));
            case 1:
                return 8.309d + (((-0.0965d * d2) - (4.02E-4d * d)) - (((double) i2) * 0.0389d));
        }
        return 0.0d;
    }

    public final double getCalorie(int i, double d, double d2, int i2, boolean z) {
        if (!z) {
            switch (i) {
                case 0:
                    return 101.46d + (((21.23d * d) + (775.105d * d2)) - (10.553d * ((double) i2)));
                case 1:
                    return 1014.35d + (((14.877d * d) + (279.277d * d2)) - (7.285d * ((double) i2)));
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return 101.828d + (((25.893d * d) + (929.104d * d2)) - (8.972d * ((double) i2)));
            case 1:
                return 1015.802d + (((19.532d * d) + (433.227d * d2)) - (5.724d * ((double) i2)));
        }
        return 0.0d;
    }

    public final double getFat(int i, double d, int i2, double d2, boolean z) {
        if (!z) {
            switch (i) {
                case 0:
                    return (((1.479d + (4.4E-4d * d)) * d2) + (((double) i2) * 0.1d)) - 21.764d;
                case 1:
                    return (((1.506d + (3.908E-4d * d)) * d2) + (((double) i2) * 0.1d)) - 12.834d;
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return (((1.504d + (3.835E-4d * d)) * d2) + (0.102d * ((double) i2))) - 26.565d;
            case 1:
                return (((1.511d + (3.296E-4d * d)) * d2) + (0.104d * ((double) i2))) - 17.241d;
        }
        return 0.0d;
    }

    public final double getImp(int i) {
        return i >= 410 ? 0.3d * ((double) (i - 400)) : 3.0d;
    }

    public final double getMuscle(int i, double d, int i2, double d2, boolean z) {
        if (!z) {
            switch (i) {
                case 0:
                    return 74.627d + (((-0.811d * d2) - (0.00565d * d)) - (0.367d * ((double) i2)));
                case 1:
                    return 57.0d + (((-0.694d * d2) - (0.00344d * d)) - (0.255d * ((double) i2)));
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return 77.389d + (((-0.819d * d2) - (0.00486d * d)) - (0.382d * ((double) i2)));
            case 1:
                return 59.225d + (((-0.685d * d2) - (0.00283d * d)) - (0.274d * ((double) i2)));
        }
        return 0.0d;
    }
}
