package com.ihealth.communication.ins;

import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.Arrays;

public class UserListInHs5 {
    boolean f1909a = true;
    private int f1910b = -1;
    private int f1911c = -1;
    private int f1912d = 0;
    private String[] f1913e = new String[20];
    private int[] f1914f = new int[20];
    private int[] f1915g = new int[20];

    int m1030a() {
        return this.f1910b;
    }

    protected void m1031a(int i, int i2) {
        Log.p("UserListInHs5", Level.DEBUG, "updateUserInList", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
        if (i < 0 || i > 19) {
            Log.e("UserListInHs5", "updateUserInList() position in invalid, position = " + i);
            return;
        }
        this.f1914f[i] = i2;
        this.f1913e[i] = ByteBufferUtil.decimalismToHexadecimal(i2);
        if (i2 == -1) {
            this.f1915g[i] = 0;
            if (this.f1910b == i) {
                this.f1910b = -1;
            }
        } else {
            int i3 = 0;
            while (i3 < 19) {
                this.f1915g[i3] = this.f1915g[i3] == 2 ? 1 : this.f1915g[i3];
                i3++;
            }
            this.f1915g[i] = 2;
            this.f1910b = i;
        }
        for (int i4 = 0; i4 < 19; i4++) {
            if (this.f1913e[i4].equals("FFFFFFFF")) {
                this.f1911c = i4;
                break;
            }
        }
        this.f1912d = this.f1914f[0];
    }

    public void checkUserInHs5(int userId, String userListData) {
        this.f1910b = -1;
        this.f1911c = -1;
        for (int i = 0; i < 20; i++) {
            String substring = userListData.substring(i * 8, (i * 8) + 8);
            byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(substring);
            this.f1914f[i] = (hexStringToByte[3] & 255) + ((((((hexStringToByte[0] & 255) * 256) * 256) * 256) + (((hexStringToByte[1] & 255) * 256) * 256)) + ((hexStringToByte[2] & 255) * 256));
            if (i == 0) {
                this.f1912d = this.f1914f[i];
            }
            this.f1913e[i] = substring;
            if (substring.equals("FFFFFFFF")) {
                this.f1915g[i] = 0;
            } else {
                this.f1915g[i] = 1;
            }
            if (ByteBufferUtil.decimalismToHexadecimal(userId).equals(substring)) {
                this.f1910b = i;
                Log.d("UserListInHs5", String.format("Find user(id:%d) in list(position = %d)", new Object[]{Integer.valueOf(userId), Integer.valueOf(this.f1910b)}));
                this.f1915g[i] = 2;
            }
            if (substring.equals("FFFFFFFF") && this.f1911c == -1) {
                this.f1911c = i;
            }
        }
        Log.d("UserListInHs5", String.format("userId = %d, userInList = %d, firstFreeInScale = %d, userID_first = ", new Object[]{Integer.valueOf(userId), Integer.valueOf(this.f1910b), Integer.valueOf(this.f1911c), Integer.valueOf(this.f1912d)}));
    }

    public int getFirstFreeInScale() {
        return this.f1911c;
    }

    public int[] getStates() {
        return this.f1915g;
    }

    public int getUserID_first() {
        return this.f1912d;
    }

    public int[] getUserIds() {
        return this.f1914f;
    }

    public String[] getUserList() {
        return this.f1913e;
    }

    public void setFirstFreeInScale(int firstFreeInScale) {
        this.f1911c = firstFreeInScale;
    }

    public void setUserID_first(int userID_first) {
        this.f1912d = userID_first;
    }

    public void setUserInlist(int userInList) {
        this.f1910b = userInList;
    }

    public void setUserList(String[] userList) {
        this.f1913e = userList;
    }

    public String toString() {
        return "UserListInHs5{userInList=" + this.f1910b + ", firstFreeInScale=" + this.f1911c + ", userID_first=" + this.f1912d + ", userList=" + Arrays.toString(this.f1913e) + ", userIds=" + Arrays.toString(this.f1914f) + ", states=" + Arrays.toString(this.f1915g) + '}';
    }
}
