package com.lifesense.ble.bean;

public class VibrationVoice {
    private float continuousSoundTime;
    private float continuousVibrationTime;
    private String deviceId;
    private boolean isEnableSound;
    private boolean isEnableVibration;
    private float pauseSoundTime;
    private float pauseVibrationTime;
    private int productUserNumber;
    private int soundSelect;
    private int soundTimes;
    private int vibrationIntensity;
    private int vibrationTimes;
    private int volumeSetting = 100;

    public byte[] getBytes() {
        byte[] bArr = new byte[12];
        bArr[0] = (byte) 84;
        int i = 3;
        if (this.isEnableVibration) {
            bArr[1] = (byte) (bArr[1] + 1);
            bArr[3] = (byte) this.vibrationIntensity;
            bArr[4] = (byte) this.vibrationTimes;
            bArr[5] = (byte) ((int) this.continuousVibrationTime);
            bArr[6] = (byte) ((int) this.pauseVibrationTime);
            i = 7;
        }
        if (this.isEnableSound) {
            bArr[1] = (byte) (bArr[1] + 2);
            bArr[i] = (byte) this.soundSelect;
            i++;
            bArr[i] = (byte) this.soundTimes;
            i++;
            bArr[i] = (byte) this.volumeSetting;
            i++;
            bArr[i] = (byte) ((int) this.continuousSoundTime);
            bArr[i + 1] = (byte) ((int) this.pauseSoundTime);
        }
        bArr[2] = (byte) this.productUserNumber;
        return bArr;
    }

    public float getContinuousSoundTime() {
        return this.continuousSoundTime;
    }

    public float getContinuousVibrationTime() {
        return this.continuousVibrationTime;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public float getPauseSoundTime() {
        return this.pauseSoundTime;
    }

    public float getPauseVibrationTime() {
        return this.pauseVibrationTime;
    }

    public int getProductUserNumber() {
        return this.productUserNumber;
    }

    public int getSoundSelect() {
        return this.soundSelect;
    }

    public int getSoundTimes() {
        return this.soundTimes;
    }

    public int getVibrationIntensity() {
        return this.vibrationIntensity;
    }

    public int getVibrationTimes() {
        return this.vibrationTimes;
    }

    public int getVolumeSetting() {
        return this.volumeSetting;
    }

    public boolean isEnableSound() {
        return this.isEnableSound;
    }

    public boolean isEnableVibration() {
        return this.isEnableVibration;
    }

    public void setContinuousSoundTime(float f) {
        this.continuousSoundTime = f;
    }

    public void setContinuousVibrationTime(float f) {
        this.continuousVibrationTime = f;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setEnableSound(boolean z) {
        this.isEnableSound = z;
    }

    public void setEnableVibration(boolean z) {
        this.isEnableVibration = z;
    }

    public void setPauseSoundTime(float f) {
        this.pauseSoundTime = f;
    }

    public void setPauseVibrationTime(float f) {
        this.pauseVibrationTime = f;
    }

    public void setProductUserNumber(int i) {
        this.productUserNumber = i;
    }

    public void setSoundSelect(int i) {
        this.soundSelect = i;
    }

    public void setSoundTimes(int i) {
        this.soundTimes = i;
    }

    public void setVibrationIntensity(int i) {
        this.vibrationIntensity = i;
    }

    public void setVibrationTimes(int i) {
        this.vibrationTimes = i;
    }

    public void setVolumeSetting(int i) {
        this.volumeSetting = i;
    }

    public String toString() {
        return "VibrationVoice [deviceId=" + this.deviceId + ", productUserNumber=" + this.productUserNumber + ", vibrationIntensity=" + this.vibrationIntensity + ", vibrationTimes=" + this.vibrationTimes + ", continuousVibrationTime=" + this.continuousVibrationTime + ", pauseVibrationTime=" + this.pauseVibrationTime + ", soundSelect=" + this.soundSelect + ", soundTimes=" + this.soundTimes + ", volumeSetting=" + this.volumeSetting + ", continuousSoundTime=" + this.continuousSoundTime + ", pauseSoundTime=" + this.pauseSoundTime + ", isEnableVibration=" + this.isEnableVibration + ", isEnableSound=" + this.isEnableSound + "]";
    }
}
