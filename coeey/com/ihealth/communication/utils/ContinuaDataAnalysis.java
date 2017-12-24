package com.ihealth.communication.utils;

public class ContinuaDataAnalysis {
    private final String f2067a = getClass().getName();
    private byte[] f2068b;
    private int f2069c = 0;
    private int f2070d = 0;

    public ContinuaDataAnalysis(byte[] tempData, int length) {
        this.f2068b = tempData;
        this.f2069c = length;
    }

    public short fabsByte(byte octet) {
        return octet < (byte) 0 ? (short) (octet & 255) : (short) octet;
    }

    public long fabsInt(byte octet) {
        return octet < (byte) 0 ? (long) (octet & 255) : (long) octet;
    }

    public int fabsShort(byte octet) {
        return octet < (byte) 0 ? octet & 255 : octet;
    }

    public short read16ByteValue() {
        try {
            if ((this.f2070d + 2) - 1 >= this.f2069c) {
                return (short) 0;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 2);
            short fabsShort = (short) ((bufferCut[1] * 256) + fabsShort(bufferCut[0]));
            this.f2070d = 2 + this.f2070d;
            return fabsShort;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return (short) 0;
        }
    }

    public int read24ByteValue() {
        try {
            if ((this.f2070d + 3) - 1 >= this.f2069c) {
                return 0;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 3);
            int fabsShort = ((bufferCut[2] * 256) * 256) + (fabsShort(bufferCut[0]) + (fabsShort(bufferCut[1]) * 256));
            this.f2070d = 3 + this.f2070d;
            return fabsShort;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return 0;
        }
    }

    public int read32ByteValue() {
        try {
            if ((this.f2070d + 4) - 1 >= this.f2069c) {
                return 0;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 4);
            int fabsShort = (((bufferCut[3] * 256) * 256) * 256) + ((fabsShort(bufferCut[0]) + (fabsShort(bufferCut[1]) * 256)) + ((fabsShort(bufferCut[2]) * 256) * 256));
            this.f2070d = 4 + this.f2070d;
            return fabsShort;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return 0;
        }
    }

    public byte read8ByteValue() {
        try {
            if ((this.f2070d + 1) - 1 >= this.f2069c) {
                return (byte) 0;
            }
            byte b = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 1)[0];
            this.f2070d = 1 + this.f2070d;
            return b;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return (byte) 0;
        }
    }

    public String readDateValue() {
        String str = "";
        try {
            if ((this.f2070d + 7) - 1 >= this.f2069c) {
                return str;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 7);
            int fabsShort = fabsShort(bufferCut[0]) + (fabsShort(bufferCut[1]) * 256);
            short fabsByte = fabsByte(bufferCut[2]);
            short fabsByte2 = fabsByte(bufferCut[3]);
            short fabsByte3 = fabsByte(bufferCut[4]);
            short fabsByte4 = fabsByte(bufferCut[5]);
            short fabsByte5 = fabsByte(bufferCut[6]);
            str = String.format("%d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(fabsShort), Integer.valueOf(fabsByte), Integer.valueOf(fabsByte2), Integer.valueOf(fabsByte3), Integer.valueOf(fabsByte4), Integer.valueOf(fabsByte5)});
            this.f2070d = 7 + this.f2070d;
            return str;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return str;
        }
    }

    public float readFloatValue() {
        try {
            if ((this.f2070d + 4) - 1 >= this.f2069c) {
                return 0.0f;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 4);
            double fabsShort = (double) (((bufferCut[2] * 256) * 256) + (fabsShort(bufferCut[0]) + (fabsShort(bufferCut[1]) * 256)));
            float pow = (float) (Math.pow(10.0d, (double) bufferCut[3]) * fabsShort);
            this.f2070d = 4 + this.f2070d;
            return pow;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return 0.0f;
        }
    }

    public byte readNibbleValue() {
        try {
            if ((this.f2070d + 1) - 1 >= this.f2069c) {
                return (byte) 0;
            }
            byte b = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 1)[0];
            this.f2070d = 1 + this.f2070d;
            return b;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return (byte) 0;
        }
    }

    public float readSFloatValue() {
        try {
            if ((this.f2070d + 2) - 1 >= this.f2069c) {
                return 0.0f;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 2);
            int i = bufferCut[1] >> 4;
            float pow = (float) (Math.pow(10.0d, (double) i) * ((double) (((bufferCut[1] & 15) * 256) + fabsByte(bufferCut[0]))));
            this.f2070d = 2 + this.f2070d;
            return pow;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return 0.0f;
        }
    }

    public short readSInt16Value() {
        try {
            if ((this.f2070d + 2) - 1 >= this.f2069c) {
                return (short) 0;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 2);
            short fabsShort = (short) ((bufferCut[1] * 256) + fabsShort(bufferCut[0]));
            this.f2070d = 2 + this.f2070d;
            return fabsShort;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return (short) 0;
        }
    }

    public int readSInt32Value() {
        try {
            if ((this.f2070d + 4) - 1 >= this.f2069c) {
                return 0;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 4);
            int fabsByte = (((bufferCut[3] * 256) * 256) * 256) + ((fabsByte(bufferCut[0]) + (fabsByte(bufferCut[1]) * 256)) + ((fabsByte(bufferCut[2]) * 256) * 256));
            this.f2070d = 4 + this.f2070d;
            return fabsByte;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return 0;
        }
    }

    public byte readSInt8Value() {
        try {
            if ((this.f2070d + 1) - 1 >= this.f2069c) {
                return (byte) 0;
            }
            byte b = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 1)[0];
            this.f2070d = 1 + this.f2070d;
            return b;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return (byte) 0;
        }
    }

    public int readUInt16Value() {
        try {
            if ((this.f2070d + 2) - 1 >= this.f2069c) {
                return 0;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 2);
            int fabsShort = (fabsShort(bufferCut[1]) * 256) + fabsShort(bufferCut[0]);
            this.f2070d = 2 + this.f2070d;
            return fabsShort;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return 0;
        }
    }

    public long readUInt32Value() {
        try {
            if ((this.f2070d + 4) - 1 >= this.f2069c) {
                return 0;
            }
            byte[] bufferCut = ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 4);
            long fabsInt = (((fabsInt(bufferCut[3]) * 256) * 256) * 256) + ((fabsInt(bufferCut[0]) + (fabsInt(bufferCut[1]) * 256)) + ((fabsInt(bufferCut[2]) * 256) * 256));
            this.f2070d = 4 + this.f2070d;
            return fabsInt;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return 0;
        }
    }

    public short readUInt8Value() {
        try {
            if ((this.f2070d + 1) - 1 >= this.f2069c) {
                return (short) 0;
            }
            short fabsByte = fabsByte(ByteBufferUtil.bufferCut(this.f2068b, this.f2070d, 1)[0]);
            this.f2070d = 1 + this.f2070d;
            return fabsByte;
        } catch (Exception e) {
            Log.m1211e(this.f2067a, e.toString());
            return (short) 0;
        }
    }
}
