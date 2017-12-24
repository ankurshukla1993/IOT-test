package com.ihealth.communication.cloud.data;

public class Data_HS_Result {
    private String f763A;
    private String f764B;
    private String f765C;
    private int f766D;
    private int f767a;
    private long f768b;
    private String f769c;
    private long f770d;
    private double f771e;
    private double f772f;
    private float f773g;
    private float f774h;
    private float f775i;
    private float f776j;
    private float f777k;
    private float f778l;
    private int f779m;
    private float f780n;
    private float f781o;
    private long f782p;
    private String f783q;
    private int f784r;
    private String f785s;
    private String f786t;
    private String f787u;
    private int f788v;
    private long f789w;
    private int f790x;
    private int f791y;
    private String f792z;

    public Data_HS_Result(int ChangeType, long LastChangeTime, String PhoneDataID, long PhoneCreateTime, double Lat, double Lon, float TimeZone, float BMI, float BoneValue, float DCI, float FatValue, float MuscaleValue, int MeasureType, float WaterValue, float WeightValue, long MeasureTime, String Note, int VisceraFatLevel, String MechineType, String MechineDeviceID, String iHealthID, int Emotions, long NoteTS, int Mood, int Activity, String weather, String temp, String humidity, String visibility, int usedUserid) {
        this.f767a = ChangeType;
        this.f768b = LastChangeTime;
        this.f769c = PhoneDataID;
        this.f770d = PhoneCreateTime;
        this.f771e = Lat;
        this.f772f = Lon;
        this.f773g = TimeZone;
        this.f774h = BMI;
        this.f775i = BoneValue;
        this.f776j = DCI;
        this.f777k = FatValue;
        this.f778l = MuscaleValue;
        this.f779m = MeasureType;
        this.f780n = WaterValue;
        this.f781o = WeightValue;
        this.f782p = MeasureTime;
        this.f783q = Note;
        this.f784r = VisceraFatLevel;
        this.f785s = MechineType;
        this.f786t = MechineDeviceID;
        this.f787u = iHealthID;
        this.f788v = Emotions;
        this.f789w = NoteTS;
        this.f790x = Mood;
        this.f791y = Activity;
        this.f792z = temp;
        this.f764B = humidity;
        this.f765C = visibility;
        this.f763A = weather;
        this.f766D = usedUserid;
    }

    public int getActivity() {
        return this.f791y;
    }

    public float getBMI() {
        return this.f774h;
    }

    public float getBoneValue() {
        return this.f775i;
    }

    public int getChangeType() {
        return this.f767a;
    }

    public float getDCI() {
        return this.f776j;
    }

    public int getEmotions() {
        return this.f788v;
    }

    public float getFatValue() {
        return this.f777k;
    }

    public String getHumidity() {
        return this.f764B;
    }

    public long getLastChangeTime() {
        return this.f768b;
    }

    public double getLat() {
        return this.f771e;
    }

    public double getLon() {
        return this.f772f;
    }

    public long getMeasureTime() {
        return this.f782p;
    }

    public int getMeasureType() {
        return this.f779m;
    }

    public String getMechineDeviceID() {
        return this.f786t;
    }

    public String getMechineType() {
        return this.f785s;
    }

    public int getMood() {
        return this.f790x;
    }

    public float getMuscaleValue() {
        return this.f778l;
    }

    public String getNote() {
        return this.f783q;
    }

    public long getNoteTS() {
        return this.f789w;
    }

    public long getPhoneCreateTime() {
        return this.f770d;
    }

    public String getPhoneDataID() {
        return this.f769c;
    }

    public String getTemp() {
        return this.f792z;
    }

    public float getTimeZone() {
        return this.f773g;
    }

    public int getUsedUserid() {
        return this.f766D;
    }

    public int getVisceraFatLevel() {
        return this.f784r;
    }

    public String getVisibility() {
        return this.f765C;
    }

    public float getWaterValue() {
        return this.f780n;
    }

    public String getWeather() {
        return this.f763A;
    }

    public float getWeightValue() {
        return this.f781o;
    }

    public String getiHealthID() {
        return this.f787u;
    }

    public void setActivity(int activity) {
        this.f791y = activity;
    }

    public void setBMI(float bMI) {
        this.f774h = bMI;
    }

    public void setBoneValue(float boneValue) {
        this.f775i = boneValue;
    }

    public void setChangeType(int changeType) {
        this.f767a = changeType;
    }

    public void setDCI(float dCI) {
        this.f776j = dCI;
    }

    public void setEmotions(int emotions) {
        this.f788v = emotions;
    }

    public void setFatValue(float fatValue) {
        this.f777k = fatValue;
    }

    public void setHumidity(String humidity) {
        this.f764B = humidity;
    }

    public void setLastChangeTime(long lastChangeTime) {
        this.f768b = lastChangeTime;
    }

    public void setLat(double lat) {
        this.f771e = lat;
    }

    public void setLon(double lon) {
        this.f772f = lon;
    }

    public void setMeasureTime(long measureTime) {
        this.f782p = measureTime;
    }

    public void setMeasureType(int measureType) {
        this.f779m = measureType;
    }

    public void setMechineDeviceID(String mechineDeviceID) {
        this.f786t = mechineDeviceID;
    }

    public void setMechineType(String mechineType) {
        this.f785s = mechineType;
    }

    public void setMood(int mood) {
        this.f790x = mood;
    }

    public void setMuscaleValue(float muscaleValue) {
        this.f778l = muscaleValue;
    }

    public void setNote(String note) {
        this.f783q = note;
    }

    public void setNoteTS(long noteTS) {
        this.f789w = noteTS;
    }

    public void setPhoneCreateTime(long phoneCreateTime) {
        this.f770d = phoneCreateTime;
    }

    public void setPhoneDataID(String phoneDataID) {
        this.f769c = phoneDataID;
    }

    public void setTemp(String temp) {
        this.f792z = temp;
    }

    public void setTimeZone(float timeZone) {
        this.f773g = timeZone;
    }

    public void setUsedUserid(int usedUserid) {
        this.f766D = usedUserid;
    }

    public void setVisceraFatLevel(int visceraFatLevel) {
        this.f784r = visceraFatLevel;
    }

    public void setVisibility(String visibility) {
        this.f765C = visibility;
    }

    public void setWaterValue(float waterValue) {
        this.f780n = waterValue;
    }

    public void setWeather(String weather) {
        this.f763A = weather;
    }

    public void setWeightValue(float weightValue) {
        this.f781o = weightValue;
    }

    public void setiHealthID(String iHealthID) {
        this.f787u = iHealthID;
    }
}
