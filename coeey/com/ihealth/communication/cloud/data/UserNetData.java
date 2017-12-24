package com.ihealth.communication.cloud.data;

public class UserNetData {
    public int ActivityLevel;
    public int Age;
    public long Birthday;
    public String[] Email = new String[100];
    public int Gender;
    public int Height;
    public int ID;
    public int IsSporter;
    public String Language;
    public String Name;
    public String Nation;
    public long TS;
    public int UserIdx = 0;
    public String UserNation;
    public float Weight;
    public String iHealthID;
    public LOGO logo;
    public int usecloud;

    public class LOGO {
        public long TS;
        final /* synthetic */ UserNetData f982a;
        public String data = new String();

        public LOGO(UserNetData this$0) {
            this.f982a = this$0;
        }
    }

    public UserNetData() {
        int i = 0;
        while (i < 100) {
            this.Email[i] = "";
            i++;
        }
        this.Name = new String();
        this.Nation = new String();
        this.Language = new String();
        this.logo = new LOGO(this);
        this.iHealthID = new String();
    }

    public static int getMaxsizeemail() {
        return 100;
    }

    public int getActivityLevel() {
        return this.ActivityLevel;
    }

    public int getAge() {
        return this.Age;
    }

    public long getBirthday() {
        return this.Birthday;
    }

    public String[] getEmail() {
        return this.Email;
    }

    public int getGender() {
        return this.Gender;
    }

    public int getHeight() {
        return this.Height;
    }

    public int getID() {
        return this.ID;
    }

    public int getIsSporter() {
        return this.IsSporter;
    }

    public String getLanguage() {
        return this.Language;
    }

    public LOGO getLogo() {
        return this.logo;
    }

    public String getName() {
        return this.Name;
    }

    public String getNation() {
        return this.Nation;
    }

    public long getTS() {
        return this.TS;
    }

    public int getUsecloud() {
        return this.usecloud;
    }

    public int getUserIdx() {
        return this.UserIdx;
    }

    public String getUserNation() {
        return this.UserNation;
    }

    public float getWeight() {
        return this.Weight;
    }

    public String getiHealthID() {
        return this.iHealthID;
    }

    public void setActivityLevel(int activityLevel) {
        this.ActivityLevel = activityLevel;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public void setBirthday(long birthday) {
        this.Birthday = birthday;
    }

    public void setEmail(String[] email) {
        this.Email = email;
    }

    public void setGender(int gender) {
        this.Gender = gender;
    }

    public void setHeight(int height) {
        this.Height = height;
    }

    public void setID(int iD) {
        this.ID = iD;
    }

    public void setIsSporter(int isSporter) {
        this.IsSporter = isSporter;
    }

    public void setLanguage(String language) {
        this.Language = language;
    }

    public void setLogo(LOGO logo) {
        this.logo = logo;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setNation(String nation) {
        this.Nation = nation;
    }

    public void setTS(long tS) {
        this.TS = tS;
    }

    public void setUsecloud(int usecloud) {
        this.usecloud = usecloud;
    }

    public void setUserIdx(int userIdx) {
        this.UserIdx = userIdx;
    }

    public void setUserNation(String userNation) {
        this.UserNation = userNation;
    }

    public void setWeight(float weight) {
        this.Weight = weight;
    }

    public void setiHealthID(String iHealthID) {
        this.iHealthID = iHealthID;
    }
}
