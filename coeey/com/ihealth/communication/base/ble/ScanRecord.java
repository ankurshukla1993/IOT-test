package com.ihealth.communication.base.ble;

import android.os.ParcelUuid;
import android.util.SparseArray;
import com.ihealth.communication.utils.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ScanRecord {
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;
    private final List f308a;
    private final SparseArray f309b;
    private final Map f310c;
    private final int f311d;
    private final int f312e;
    private final String f313f;
    private final byte[] f314g;

    private ScanRecord(List serviceUuids, SparseArray manufacturerData, Map serviceData, int advertiseFlags, int txPowerLevel, String localName, byte[] bytes) {
        this.f308a = serviceUuids;
        this.f309b = manufacturerData;
        this.f310c = serviceData;
        this.f313f = localName;
        this.f311d = advertiseFlags;
        this.f312e = txPowerLevel;
        this.f314g = bytes;
    }

    private static int m269a(byte[] bArr, int i, int i2, int i3, List list) {
        while (i2 > 0) {
            list.add(parseUuidFrom(m270a(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    private static byte[] m270a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return obj;
    }

    public static ScanRecord parseFromBytes(byte[] scanRecord) {
        if (scanRecord == null) {
            return null;
        }
        int i = 0;
        List arrayList = new ArrayList();
        SparseArray sparseArray = new SparseArray();
        Map hashMap = new HashMap();
        int i2 = Integer.MIN_VALUE;
        String str = null;
        int i3 = -1;
        while (i < scanRecord.length) {
            int i4 = i + 1;
            i = scanRecord[i] & 255;
            if (i == 0) {
                if (arrayList.isEmpty()) {
                    arrayList = null;
                }
                return new ScanRecord(arrayList, sparseArray, hashMap, i3, i2, str, scanRecord);
            }
            i--;
            int i5 = i4 + 1;
            try {
                switch (scanRecord[i4] & 255) {
                    case 1:
                        i3 = scanRecord[i5] & 255;
                        break;
                    case 2:
                    case 3:
                        m269a(scanRecord, i5, i, 2, arrayList);
                        break;
                    case 4:
                    case 5:
                        m269a(scanRecord, i5, i, 4, arrayList);
                        break;
                    case 6:
                    case 7:
                        m269a(scanRecord, i5, i, 16, arrayList);
                        break;
                    case 8:
                    case 9:
                        str = new String(m270a(scanRecord, i5, i));
                        break;
                    case 10:
                        i2 = scanRecord[i5];
                        break;
                    case 22:
                        hashMap.put(parseUuidFrom(m270a(scanRecord, i5, 2)), m270a(scanRecord, i5 + 2, i - 2));
                        break;
                    case 255:
                        sparseArray.put(((scanRecord[i5 + 1] & 255) << 8) + (scanRecord[i5] & 255), m270a(scanRecord, i5 + 2, i - 2));
                        break;
                    default:
                        break;
                }
                i += i5;
            } catch (Exception e) {
                Log.w("Custom ScanRecord", "unable to parse scan record: " + Arrays.toString(scanRecord));
                return new ScanRecord(null, null, null, -1, Integer.MIN_VALUE, null, scanRecord);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new ScanRecord(arrayList, sparseArray, hashMap, i3, i2, str, scanRecord);
    }

    public static ParcelUuid parseUuidFrom(byte[] uuidBytes) {
        if (uuidBytes == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = uuidBytes.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        } else if (length == 16) {
            ByteBuffer order = ByteBuffer.wrap(uuidBytes).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
        } else {
            return new ParcelUuid(new UUID(BASE_UUID.getUuid().getMostSignificantBits() + ((length == 2 ? ((long) (uuidBytes[0] & 255)) + ((long) ((uuidBytes[1] & 255) << 8)) : ((((long) (uuidBytes[0] & 255)) + ((long) ((uuidBytes[1] & 255) << 8))) + ((long) ((uuidBytes[2] & 255) << 16))) + ((long) ((uuidBytes[3] & 255) << 24))) << 32), BASE_UUID.getUuid().getLeastSignificantBits()));
        }
    }

    public int getAdvertiseFlags() {
        return this.f311d;
    }

    public byte[] getBytes() {
        return this.f314g;
    }

    public String getDeviceName() {
        return this.f313f;
    }

    public SparseArray getManufacturerSpecificData() {
        return this.f309b;
    }

    public byte[] getManufacturerSpecificData(int manufacturerId) {
        return (byte[]) this.f309b.get(manufacturerId);
    }

    public Map getServiceData() {
        return this.f310c;
    }

    public byte[] getServiceData(ParcelUuid serviceDataUuid) {
        return serviceDataUuid == null ? null : (byte[]) this.f310c.get(serviceDataUuid);
    }

    public List getServiceUuids() {
        return this.f308a;
    }

    public int getTxPowerLevel() {
        return this.f312e;
    }
}
