package com.cooey.devices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.cooey.devices.vo.CooeyStatus;
import com.cooey.devices.vo.Device;
import com.lifesense.ble.p003a.C2234v;
import java.util.ArrayList;
import java.util.List;

public class CooeyDeviceDataSource {
    private String[] allColumns = new String[]{"_id", CooeySQLHelper.USR_ID, CooeySQLHelper.KEY_DEVICE_ID, CooeySQLHelper.KEY_DEVICE_NAME, CooeySQLHelper.KEY_DEVICE_ADDRESS, CooeySQLHelper.KEY_DEVICE_SERVICE_UUID, CooeySQLHelper.KEY_DEVICE_TYPE, CooeySQLHelper.KEY_DEVICE_PAIRFLAGS, CooeySQLHelper.KEY_DEVICE_SN, CooeySQLHelper.KEY_DEVICE_MODELNUMBER, CooeySQLHelper.KEY_DEVICE_PASSWORD, CooeySQLHelper.KEY_DEVICE_BROADCASTID, CooeySQLHelper.KEY_DEVICE_SOFTWARE_VERSION, CooeySQLHelper.KEY_DEVICE_HARDWARE_VERSION, CooeySQLHelper.KEY_DEVICE_FIRMWARE_VERSION, CooeySQLHelper.KEY_DEVICE_MANUFACTURENAME, CooeySQLHelper.KEY_DEVICE_SYSTEMID, CooeySQLHelper.KEY_DEVICE_MODEL, CooeySQLHelper.KEY_DEVICE_USER_NUMBER, CooeySQLHelper.KEY_DEVICE_USER_NAME, CooeySQLHelper.KEY_MAX_USER_QUANTITY, CooeySQLHelper.KEY_DEVICE_STATUS, CooeySQLHelper.KEY_PROTOCOL_TYPE, CooeySQLHelper.KEY_PAIRSTATUS};
    private SQLiteDatabase database;
    private CooeySQLHelper dbHelper;

    public CooeyDeviceDataSource(Context context) {
        this.dbHelper = new CooeySQLHelper(context);
    }

    public void open() throws SQLException {
        this.database = this.dbHelper.getWritableDatabase();
    }

    public void close() {
        this.dbHelper.close();
    }

    public void createDeviceForUser(Device dev, long uid) {
        String rowId;
        ContentValues values;
        Cursor cursor = null;
        if (dev != null) {
            try {
                if (dev.getProtocolType().contains(C2234v.PROTOCOL_TYPE_A3)) {
                    cursor = this.database.query(CooeySQLHelper.TABLE_DEV, this.allColumns, "uid=" + uid + " and " + CooeySQLHelper.KEY_DEVICE_USER_NUMBER + " = " + dev.getDeviceUserNumber() + " and " + CooeySQLHelper.KEY_DEVICE_ID + " = ? and " + CooeySQLHelper.KEY_DEVICE_SN + " = ?", new String[]{dev.getDeviceId(), dev.getDeviceSn()}, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        rowId = cursor.getString(cursor.getColumnIndex("_id"));
                        this.database.delete(CooeySQLHelper.TABLE_DEV, "_id=?", new String[]{rowId});
                    }
                    values = new ContentValues();
                    values.put(CooeySQLHelper.USR_ID, Long.valueOf(uid));
                    values.put(CooeySQLHelper.KEY_DEVICE_ID, dev.getDeviceId());
                    values.put(CooeySQLHelper.KEY_DEVICE_NAME, dev.getDeviceName());
                    values.put(CooeySQLHelper.KEY_DEVICE_TYPE, dev.getDeviceType());
                    values.put(CooeySQLHelper.KEY_DEVICE_SN, dev.getDeviceSn());
                    values.put(CooeySQLHelper.KEY_DEVICE_MODELNUMBER, dev.getModelNumber());
                    values.put(CooeySQLHelper.KEY_DEVICE_PASSWORD, dev.getPassword());
                    values.put(CooeySQLHelper.KEY_DEVICE_BROADCASTID, dev.getBroadcastID());
                    values.put(CooeySQLHelper.KEY_DEVICE_SOFTWARE_VERSION, dev.getSoftwareVersion());
                    values.put(CooeySQLHelper.KEY_DEVICE_HARDWARE_VERSION, dev.getHardwareVersion());
                    values.put(CooeySQLHelper.KEY_DEVICE_FIRMWARE_VERSION, dev.getFirmwareVersion());
                    values.put(CooeySQLHelper.KEY_DEVICE_MANUFACTURENAME, dev.getManufactureName());
                    values.put(CooeySQLHelper.KEY_DEVICE_SYSTEMID, dev.getSystemId());
                    values.put(CooeySQLHelper.KEY_DEVICE_MODEL, "");
                    values.put(CooeySQLHelper.KEY_DEVICE_USER_NUMBER, Integer.valueOf(dev.getDeviceUserNumber()));
                    values.put(CooeySQLHelper.KEY_DEVICE_USER_NAME, Integer.valueOf(dev.getDeviceUserNumber()));
                    values.put(CooeySQLHelper.KEY_MAX_USER_QUANTITY, Integer.valueOf(dev.getMaxUserQuantity()));
                    values.put(CooeySQLHelper.KEY_DEVICE_STATUS, "");
                    values.put(CooeySQLHelper.KEY_DEVICE_SERVICE_UUID, "");
                    values.put(CooeySQLHelper.KEY_DEVICE_PAIRFLAGS, Integer.valueOf(dev.getPairStatus()));
                    values.put(CooeySQLHelper.KEY_PROTOCOL_TYPE, dev.getProtocolType());
                    values.put(CooeySQLHelper.KEY_PAIRSTATUS, Integer.valueOf(dev.getPairStatus()));
                    cursor = this.database.query(CooeySQLHelper.TABLE_DEV, this.allColumns, "_id = " + this.database.insert(CooeySQLHelper.TABLE_DEV, null, values), null, null, null, null);
                    cursor.moveToFirst();
                    if (cursor != null) {
                        cursor.close();
                    }
                    cursor.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (dev.getProtocolType().contains(C2234v.PROTOCOL_TYPE_A2)) {
            cursor = this.database.query(CooeySQLHelper.TABLE_DEV, this.allColumns, "uid=" + uid + " and " + CooeySQLHelper.KEY_DEVICE_ID + " = ? and " + CooeySQLHelper.KEY_DEVICE_SN + " = ?", new String[]{dev.getDeviceId(), dev.getDeviceSn()}, null, null, null, null);
        }
        rowId = cursor.getString(cursor.getColumnIndex("_id"));
        this.database.delete(CooeySQLHelper.TABLE_DEV, "_id=?", new String[]{rowId});
        values = new ContentValues();
        values.put(CooeySQLHelper.USR_ID, Long.valueOf(uid));
        values.put(CooeySQLHelper.KEY_DEVICE_ID, dev.getDeviceId());
        values.put(CooeySQLHelper.KEY_DEVICE_NAME, dev.getDeviceName());
        values.put(CooeySQLHelper.KEY_DEVICE_TYPE, dev.getDeviceType());
        values.put(CooeySQLHelper.KEY_DEVICE_SN, dev.getDeviceSn());
        values.put(CooeySQLHelper.KEY_DEVICE_MODELNUMBER, dev.getModelNumber());
        values.put(CooeySQLHelper.KEY_DEVICE_PASSWORD, dev.getPassword());
        values.put(CooeySQLHelper.KEY_DEVICE_BROADCASTID, dev.getBroadcastID());
        values.put(CooeySQLHelper.KEY_DEVICE_SOFTWARE_VERSION, dev.getSoftwareVersion());
        values.put(CooeySQLHelper.KEY_DEVICE_HARDWARE_VERSION, dev.getHardwareVersion());
        values.put(CooeySQLHelper.KEY_DEVICE_FIRMWARE_VERSION, dev.getFirmwareVersion());
        values.put(CooeySQLHelper.KEY_DEVICE_MANUFACTURENAME, dev.getManufactureName());
        values.put(CooeySQLHelper.KEY_DEVICE_SYSTEMID, dev.getSystemId());
        values.put(CooeySQLHelper.KEY_DEVICE_MODEL, "");
        values.put(CooeySQLHelper.KEY_DEVICE_USER_NUMBER, Integer.valueOf(dev.getDeviceUserNumber()));
        values.put(CooeySQLHelper.KEY_DEVICE_USER_NAME, Integer.valueOf(dev.getDeviceUserNumber()));
        values.put(CooeySQLHelper.KEY_MAX_USER_QUANTITY, Integer.valueOf(dev.getMaxUserQuantity()));
        values.put(CooeySQLHelper.KEY_DEVICE_STATUS, "");
        values.put(CooeySQLHelper.KEY_DEVICE_SERVICE_UUID, "");
        values.put(CooeySQLHelper.KEY_DEVICE_PAIRFLAGS, Integer.valueOf(dev.getPairStatus()));
        values.put(CooeySQLHelper.KEY_PROTOCOL_TYPE, dev.getProtocolType());
        values.put(CooeySQLHelper.KEY_PAIRSTATUS, Integer.valueOf(dev.getPairStatus()));
        cursor = this.database.query(CooeySQLHelper.TABLE_DEV, this.allColumns, "_id = " + this.database.insert(CooeySQLHelper.TABLE_DEV, null, values), null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }
        cursor.close();
    }

    public CooeyStatus getRemoveDeviceForUser(Device dev, long uid) {
        int deleteId = -1;
        if (dev.getProtocolType().contains(C2234v.PROTOCOL_TYPE_A3)) {
            deleteId = this.database.delete(CooeySQLHelper.TABLE_DEV, "uid=" + uid + " and " + CooeySQLHelper.KEY_DEVICE_USER_NUMBER + " = " + dev.getDeviceUserNumber() + " and " + CooeySQLHelper.KEY_DEVICE_PASSWORD + " = ? and " + CooeySQLHelper.KEY_DEVICE_ID + " = ? and " + CooeySQLHelper.KEY_DEVICE_SN + " = ? and " + CooeySQLHelper.KEY_DEVICE_BROADCASTID + " = ?", new String[]{dev.getPassword(), dev.getDeviceId(), dev.getDeviceSn(), dev.getBroadcastID()});
        } else if (dev.getProtocolType().contains(C2234v.PROTOCOL_TYPE_A2)) {
            deleteId = this.database.delete(CooeySQLHelper.TABLE_DEV, "uid=" + uid + " and " + CooeySQLHelper.KEY_DEVICE_ID + " = ? and " + CooeySQLHelper.KEY_DEVICE_SN + " = ? and " + CooeySQLHelper.KEY_DEVICE_BROADCASTID + " = ?", new String[]{dev.getDeviceId(), dev.getDeviceSn(), dev.getBroadcastID()});
        }
        if (deleteId >= 0) {
            return new CooeyStatus().setStatus(1).setMessage("Content Deleted");
        }
        if (deleteId == 0) {
            return new CooeyStatus().setStatus(-2).setMessage("Nothing to delete");
        }
        return new CooeyStatus().setStatus(0).setMessage("Delete Failure");
    }

    public List<Device> getDevicesForUser(long uid) {
        List<Device> devList = new ArrayList();
        Cursor cursor = this.database.query(CooeySQLHelper.TABLE_DEV, this.allColumns, "uid = " + uid, null, null, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                devList.add(cursorToDevice(cursor));
                cursor.moveToNext();
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return devList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return devList;
    }

    public List<Device> getAllDevices() {
        List<Device> devList = new ArrayList();
        try {
            Cursor cursor = this.database.query(CooeySQLHelper.TABLE_DEV, this.allColumns, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                devList.add(cursorToDevice(cursor));
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } catch (Throwable th) {
        }
        return devList;
    }

    public Device cursorToDevice(Cursor cursor) {
        Device dev = new Device();
        dev.setBroadcastID(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_BROADCASTID)));
        dev.setDeviceId(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_ID)));
        dev.setDeviceName(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_NAME)));
        dev.setDeviceSn(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_SN)));
        dev.setDeviceType(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_TYPE)));
        dev.setDeviceUserNumber(cursor.getInt(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_USER_NUMBER)));
        dev.setFirmwareVersion(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_FIRMWARE_VERSION)));
        dev.setHardwareVersion(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_HARDWARE_VERSION)));
        dev.setManufactureName(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_MANUFACTURENAME)));
        dev.setMaxUserQuantity(cursor.getInt(cursor.getColumnIndex(CooeySQLHelper.KEY_MAX_USER_QUANTITY)));
        dev.setModelNumber(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_MODELNUMBER)));
        dev.setPairStatus(cursor.getInt(cursor.getColumnIndex(CooeySQLHelper.KEY_PAIRSTATUS)));
        dev.setPassword(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_PASSWORD)));
        dev.setProtocolType(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_PROTOCOL_TYPE)));
        dev.setSoftwareVersion(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_SOFTWARE_VERSION)));
        dev.setSystemId(cursor.getString(cursor.getColumnIndex(CooeySQLHelper.KEY_DEVICE_SYSTEMID)));
        dev.setMaxUserQuantity(cursor.getInt(cursor.getColumnIndex(CooeySQLHelper.USR_ID)));
        return dev;
    }
}
