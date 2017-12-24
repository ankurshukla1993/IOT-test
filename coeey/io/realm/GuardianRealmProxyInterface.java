package io.realm;

import com.cooey.common.vo.Allergy;
import com.cooey.common.vo.Height;
import com.cooey.common.vo.HipSize;
import com.cooey.common.vo.UserSettings;
import com.cooey.common.vo.WaistSize;

public interface GuardianRealmProxyInterface {
    String realmGet$alarmId();

    RealmList<Allergy> realmGet$allergires();

    String realmGet$caretakerId();

    String realmGet$country();

    String realmGet$dateOfBirth();

    String realmGet$email();

    String realmGet$firstName();

    String realmGet$gender();

    String realmGet$governmentId();

    String realmGet$groupId();

    Height realmGet$height();

    HipSize realmGet$hipSize();

    String realmGet$id();

    String realmGet$lastName();

    String realmGet$mobile();

    String realmGet$oauthId();

    String realmGet$oauthToken();

    String realmGet$password();

    Boolean realmGet$passwordResetEnabled();

    String realmGet$patientId();

    Long realmGet$publicId();

    String realmGet$room();

    String realmGet$tenantId();

    String realmGet$type();

    UserSettings realmGet$userSettings();

    WaistSize realmGet$waistSize();

    void realmSet$alarmId(String str);

    void realmSet$allergires(RealmList<Allergy> realmList);

    void realmSet$caretakerId(String str);

    void realmSet$country(String str);

    void realmSet$dateOfBirth(String str);

    void realmSet$email(String str);

    void realmSet$firstName(String str);

    void realmSet$gender(String str);

    void realmSet$governmentId(String str);

    void realmSet$groupId(String str);

    void realmSet$height(Height height);

    void realmSet$hipSize(HipSize hipSize);

    void realmSet$id(String str);

    void realmSet$lastName(String str);

    void realmSet$mobile(String str);

    void realmSet$oauthId(String str);

    void realmSet$oauthToken(String str);

    void realmSet$password(String str);

    void realmSet$passwordResetEnabled(Boolean bool);

    void realmSet$patientId(String str);

    void realmSet$publicId(Long l);

    void realmSet$room(String str);

    void realmSet$tenantId(String str);

    void realmSet$type(String str);

    void realmSet$userSettings(UserSettings userSettings);

    void realmSet$waistSize(WaistSize waistSize);
}
