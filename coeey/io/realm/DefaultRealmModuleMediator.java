package io.realm;

import android.util.JsonReader;
import com.cooey.common.converter.Tag;
import com.cooey.common.vo.Action;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.Address;
import com.cooey.common.vo.Allergy;
import com.cooey.common.vo.CareplanSummary;
import com.cooey.common.vo.DeviceAlert;
import com.cooey.common.vo.DietTemplate;
import com.cooey.common.vo.FeedBackInput;
import com.cooey.common.vo.Guardian;
import com.cooey.common.vo.Height;
import com.cooey.common.vo.HipSize;
import com.cooey.common.vo.Limit;
import com.cooey.common.vo.MealPlan;
import com.cooey.common.vo.MedicalContact;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.Message;
import com.cooey.common.vo.Note;
import com.cooey.common.vo.RealmBoolean;
import com.cooey.common.vo.RealmString;
import com.cooey.common.vo.Reminder;
import com.cooey.common.vo.Schedule;
import com.cooey.common.vo.Timings;
import com.cooey.common.vo.User;
import com.cooey.common.vo.UserFeature;
import com.cooey.common.vo.UserSettings;
import com.cooey.common.vo.Vital;
import com.cooey.common.vo.WaistSize;
import com.cooey.common.vo.careplan.ActivityFeature;
import com.cooey.common.vo.careplan.ActivityIntervention;
import com.cooey.common.vo.careplan.ActivityItem;
import com.cooey.common.vo.careplan.Alert;
import com.cooey.common.vo.careplan.Assessment;
import com.cooey.common.vo.careplan.CarePlanReminder;
import com.cooey.common.vo.careplan.Careplan;
import com.cooey.common.vo.careplan.CommonFeature;
import com.cooey.common.vo.careplan.ContentFeature;
import com.cooey.common.vo.careplan.ContentIntervention;
import com.cooey.common.vo.careplan.Course;
import com.cooey.common.vo.careplan.Days;
import com.cooey.common.vo.careplan.Diagnosis;
import com.cooey.common.vo.careplan.DietFeature;
import com.cooey.common.vo.careplan.DietIntervention;
import com.cooey.common.vo.careplan.Feature;
import com.cooey.common.vo.careplan.Goal;
import com.cooey.common.vo.careplan.HealthCondition;
import com.cooey.common.vo.careplan.Instructions;
import com.cooey.common.vo.careplan.Intervention;
import com.cooey.common.vo.careplan.Limits;
import com.cooey.common.vo.careplan.Objective;
import com.cooey.common.vo.careplan.Subjective;
import com.cooey.common.vo.careplan.Symoptoms;
import com.cooey.common.vo.careplan.Template;
import com.cooey.common.vo.careplan.Todo;
import com.cooey.common.vo.careplan.TodoIntervention;
import com.cooey.common.vo.careplan.UrlIdentifier;
import com.cooey.common.vo.careplan.VitalFeature;
import com.cooey.common.vo.careplan.VitalIntervention;
import com.cooey.common.vo.diet.DietMealPlan;
import com.cooey.common.vo.diet.time.BreakFast;
import com.cooey.common.vo.diet.time.Dinner;
import com.cooey.common.vo.diet.time.Lunch;
import com.cooey.common.vo.diet.weekdays.Friday;
import com.cooey.common.vo.diet.weekdays.MealTemplate;
import com.cooey.common.vo.diet.weekdays.Monday;
import com.cooey.common.vo.diet.weekdays.Saturday;
import com.cooey.common.vo.diet.weekdays.Thursday;
import com.cooey.common.vo.diet.weekdays.Tuesday;
import com.cooey.common.vo.diet.weekdays.Wednesday;
import io.realm.BaseRealm.RealmObjectContext;
import io.realm.annotations.RealmModule;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmObjectProxy.CacheData;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {
    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;

    public boolean transformerApplied() {
        return true;
    }

    DefaultRealmModuleMediator() {
    }

    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet();
        modelClasses.add(RealmBoolean.class);
        modelClasses.add(Assessment.class);
        modelClasses.add(Tag.class);
        modelClasses.add(ActivityItem.class);
        modelClasses.add(TodoIntervention.class);
        modelClasses.add(Medicine.class);
        modelClasses.add(Diagnosis.class);
        modelClasses.add(Course.class);
        modelClasses.add(Lunch.class);
        modelClasses.add(Schedule.class);
        modelClasses.add(DietMealPlan.class);
        modelClasses.add(Note.class);
        modelClasses.add(VitalIntervention.class);
        modelClasses.add(Guardian.class);
        modelClasses.add(MedicalContact.class);
        modelClasses.add(CarePlanReminder.class);
        modelClasses.add(Limit.class);
        modelClasses.add(Careplan.class);
        modelClasses.add(Message.class);
        modelClasses.add(Action.class);
        modelClasses.add(Days.class);
        modelClasses.add(Activity.class);
        modelClasses.add(Dinner.class);
        modelClasses.add(WaistSize.class);
        modelClasses.add(RealmString.class);
        modelClasses.add(Reminder.class);
        modelClasses.add(DietTemplate.class);
        modelClasses.add(MealTemplate.class);
        modelClasses.add(Monday.class);
        modelClasses.add(Tuesday.class);
        modelClasses.add(User.class);
        modelClasses.add(Timings.class);
        modelClasses.add(Todo.class);
        modelClasses.add(Height.class);
        modelClasses.add(CareplanSummary.class);
        modelClasses.add(Subjective.class);
        modelClasses.add(Symoptoms.class);
        modelClasses.add(ActivityFeature.class);
        modelClasses.add(DietIntervention.class);
        modelClasses.add(Limits.class);
        modelClasses.add(MealPlan.class);
        modelClasses.add(Objective.class);
        modelClasses.add(Template.class);
        modelClasses.add(UserFeature.class);
        modelClasses.add(ContentFeature.class);
        modelClasses.add(HealthCondition.class);
        modelClasses.add(Allergy.class);
        modelClasses.add(CommonFeature.class);
        modelClasses.add(Address.class);
        modelClasses.add(DeviceAlert.class);
        modelClasses.add(VitalFeature.class);
        modelClasses.add(HipSize.class);
        modelClasses.add(Vital.class);
        modelClasses.add(ActivityIntervention.class);
        modelClasses.add(Alert.class);
        modelClasses.add(UrlIdentifier.class);
        modelClasses.add(Intervention.class);
        modelClasses.add(Saturday.class);
        modelClasses.add(DietFeature.class);
        modelClasses.add(FeedBackInput.class);
        modelClasses.add(Goal.class);
        modelClasses.add(Instructions.class);
        modelClasses.add(Wednesday.class);
        modelClasses.add(Friday.class);
        modelClasses.add(UserSettings.class);
        modelClasses.add(ContentIntervention.class);
        modelClasses.add(BreakFast.class);
        modelClasses.add(Thursday.class);
        modelClasses.add(Feature.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap();
        infoMap.put(RealmBoolean.class, RealmBooleanRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Assessment.class, AssessmentRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Tag.class, TagRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(ActivityItem.class, ActivityItemRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(TodoIntervention.class, TodoInterventionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Medicine.class, MedicineRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Diagnosis.class, DiagnosisRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Course.class, CourseRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Lunch.class, LunchRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Schedule.class, ScheduleRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(DietMealPlan.class, DietMealPlanRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Note.class, NoteRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(VitalIntervention.class, VitalInterventionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Guardian.class, GuardianRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(MedicalContact.class, MedicalContactRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(CarePlanReminder.class, CarePlanReminderRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Limit.class, LimitRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Careplan.class, CareplanRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Message.class, MessageRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Action.class, ActionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Days.class, DaysRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Activity.class, ActivityRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Dinner.class, DinnerRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(WaistSize.class, WaistSizeRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(RealmString.class, RealmStringRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Reminder.class, ReminderRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(DietTemplate.class, DietTemplateRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(MealTemplate.class, MealTemplateRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Monday.class, MondayRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Tuesday.class, TuesdayRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(User.class, UserRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Timings.class, TimingsRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Todo.class, TodoRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Height.class, HeightRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(CareplanSummary.class, CareplanSummaryRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Subjective.class, SubjectiveRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Symoptoms.class, SymoptomsRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(ActivityFeature.class, ActivityFeatureRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(DietIntervention.class, DietInterventionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Limits.class, LimitsRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(MealPlan.class, MealPlanRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Objective.class, ObjectiveRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Template.class, TemplateRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(UserFeature.class, UserFeatureRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(ContentFeature.class, ContentFeatureRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(HealthCondition.class, HealthConditionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Allergy.class, AllergyRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(CommonFeature.class, CommonFeatureRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Address.class, AddressRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(DeviceAlert.class, DeviceAlertRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(VitalFeature.class, VitalFeatureRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(HipSize.class, HipSizeRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Vital.class, VitalRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(ActivityIntervention.class, ActivityInterventionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Alert.class, AlertRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(UrlIdentifier.class, UrlIdentifierRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Intervention.class, InterventionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Saturday.class, SaturdayRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(DietFeature.class, DietFeatureRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(FeedBackInput.class, FeedBackInputRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Goal.class, GoalRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Instructions.class, InstructionsRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Wednesday.class, WednesdayRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Friday.class, FridayRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(UserSettings.class, UserSettingsRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(ContentIntervention.class, ContentInterventionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(BreakFast.class, BreakFastRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Thursday.class, ThursdayRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(Feature.class, FeatureRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);
        if (clazz.equals(RealmBoolean.class)) {
            return RealmBooleanRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Assessment.class)) {
            return AssessmentRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Tag.class)) {
            return TagRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(ActivityItem.class)) {
            return ActivityItemRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(TodoIntervention.class)) {
            return TodoInterventionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Medicine.class)) {
            return MedicineRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Diagnosis.class)) {
            return DiagnosisRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Course.class)) {
            return CourseRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Lunch.class)) {
            return LunchRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Schedule.class)) {
            return ScheduleRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(DietMealPlan.class)) {
            return DietMealPlanRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Note.class)) {
            return NoteRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(VitalIntervention.class)) {
            return VitalInterventionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Guardian.class)) {
            return GuardianRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(MedicalContact.class)) {
            return MedicalContactRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(CarePlanReminder.class)) {
            return CarePlanReminderRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Limit.class)) {
            return LimitRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Careplan.class)) {
            return CareplanRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Message.class)) {
            return MessageRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Action.class)) {
            return ActionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Days.class)) {
            return DaysRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Activity.class)) {
            return ActivityRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Dinner.class)) {
            return DinnerRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(WaistSize.class)) {
            return WaistSizeRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Reminder.class)) {
            return ReminderRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(DietTemplate.class)) {
            return DietTemplateRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(MealTemplate.class)) {
            return MealTemplateRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Monday.class)) {
            return MondayRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Tuesday.class)) {
            return TuesdayRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(User.class)) {
            return UserRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Timings.class)) {
            return TimingsRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Todo.class)) {
            return TodoRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Height.class)) {
            return HeightRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(CareplanSummary.class)) {
            return CareplanSummaryRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Subjective.class)) {
            return SubjectiveRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Symoptoms.class)) {
            return SymoptomsRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(ActivityFeature.class)) {
            return ActivityFeatureRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(DietIntervention.class)) {
            return DietInterventionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Limits.class)) {
            return LimitsRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(MealPlan.class)) {
            return MealPlanRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Objective.class)) {
            return ObjectiveRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Template.class)) {
            return TemplateRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(UserFeature.class)) {
            return UserFeatureRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(ContentFeature.class)) {
            return ContentFeatureRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(HealthCondition.class)) {
            return HealthConditionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Allergy.class)) {
            return AllergyRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(CommonFeature.class)) {
            return CommonFeatureRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Address.class)) {
            return AddressRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(DeviceAlert.class)) {
            return DeviceAlertRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(VitalFeature.class)) {
            return VitalFeatureRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(HipSize.class)) {
            return HipSizeRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Vital.class)) {
            return VitalRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(ActivityIntervention.class)) {
            return ActivityInterventionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Alert.class)) {
            return AlertRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(UrlIdentifier.class)) {
            return UrlIdentifierRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Intervention.class)) {
            return InterventionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Saturday.class)) {
            return SaturdayRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(DietFeature.class)) {
            return DietFeatureRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(FeedBackInput.class)) {
            return FeedBackInputRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Goal.class)) {
            return GoalRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Instructions.class)) {
            return InstructionsRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Wednesday.class)) {
            return WednesdayRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Friday.class)) {
            return FridayRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(UserSettings.class)) {
            return UserSettingsRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(ContentIntervention.class)) {
            return ContentInterventionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(BreakFast.class)) {
            return BreakFastRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Thursday.class)) {
            return ThursdayRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(Feature.class)) {
            return FeatureRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);
        if (clazz.equals(RealmBoolean.class)) {
            return RealmBooleanRealmProxy.getFieldNames();
        }
        if (clazz.equals(Assessment.class)) {
            return AssessmentRealmProxy.getFieldNames();
        }
        if (clazz.equals(Tag.class)) {
            return TagRealmProxy.getFieldNames();
        }
        if (clazz.equals(ActivityItem.class)) {
            return ActivityItemRealmProxy.getFieldNames();
        }
        if (clazz.equals(TodoIntervention.class)) {
            return TodoInterventionRealmProxy.getFieldNames();
        }
        if (clazz.equals(Medicine.class)) {
            return MedicineRealmProxy.getFieldNames();
        }
        if (clazz.equals(Diagnosis.class)) {
            return DiagnosisRealmProxy.getFieldNames();
        }
        if (clazz.equals(Course.class)) {
            return CourseRealmProxy.getFieldNames();
        }
        if (clazz.equals(Lunch.class)) {
            return LunchRealmProxy.getFieldNames();
        }
        if (clazz.equals(Schedule.class)) {
            return ScheduleRealmProxy.getFieldNames();
        }
        if (clazz.equals(DietMealPlan.class)) {
            return DietMealPlanRealmProxy.getFieldNames();
        }
        if (clazz.equals(Note.class)) {
            return NoteRealmProxy.getFieldNames();
        }
        if (clazz.equals(VitalIntervention.class)) {
            return VitalInterventionRealmProxy.getFieldNames();
        }
        if (clazz.equals(Guardian.class)) {
            return GuardianRealmProxy.getFieldNames();
        }
        if (clazz.equals(MedicalContact.class)) {
            return MedicalContactRealmProxy.getFieldNames();
        }
        if (clazz.equals(CarePlanReminder.class)) {
            return CarePlanReminderRealmProxy.getFieldNames();
        }
        if (clazz.equals(Limit.class)) {
            return LimitRealmProxy.getFieldNames();
        }
        if (clazz.equals(Careplan.class)) {
            return CareplanRealmProxy.getFieldNames();
        }
        if (clazz.equals(Message.class)) {
            return MessageRealmProxy.getFieldNames();
        }
        if (clazz.equals(Action.class)) {
            return ActionRealmProxy.getFieldNames();
        }
        if (clazz.equals(Days.class)) {
            return DaysRealmProxy.getFieldNames();
        }
        if (clazz.equals(Activity.class)) {
            return ActivityRealmProxy.getFieldNames();
        }
        if (clazz.equals(Dinner.class)) {
            return DinnerRealmProxy.getFieldNames();
        }
        if (clazz.equals(WaistSize.class)) {
            return WaistSizeRealmProxy.getFieldNames();
        }
        if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.getFieldNames();
        }
        if (clazz.equals(Reminder.class)) {
            return ReminderRealmProxy.getFieldNames();
        }
        if (clazz.equals(DietTemplate.class)) {
            return DietTemplateRealmProxy.getFieldNames();
        }
        if (clazz.equals(MealTemplate.class)) {
            return MealTemplateRealmProxy.getFieldNames();
        }
        if (clazz.equals(Monday.class)) {
            return MondayRealmProxy.getFieldNames();
        }
        if (clazz.equals(Tuesday.class)) {
            return TuesdayRealmProxy.getFieldNames();
        }
        if (clazz.equals(User.class)) {
            return UserRealmProxy.getFieldNames();
        }
        if (clazz.equals(Timings.class)) {
            return TimingsRealmProxy.getFieldNames();
        }
        if (clazz.equals(Todo.class)) {
            return TodoRealmProxy.getFieldNames();
        }
        if (clazz.equals(Height.class)) {
            return HeightRealmProxy.getFieldNames();
        }
        if (clazz.equals(CareplanSummary.class)) {
            return CareplanSummaryRealmProxy.getFieldNames();
        }
        if (clazz.equals(Subjective.class)) {
            return SubjectiveRealmProxy.getFieldNames();
        }
        if (clazz.equals(Symoptoms.class)) {
            return SymoptomsRealmProxy.getFieldNames();
        }
        if (clazz.equals(ActivityFeature.class)) {
            return ActivityFeatureRealmProxy.getFieldNames();
        }
        if (clazz.equals(DietIntervention.class)) {
            return DietInterventionRealmProxy.getFieldNames();
        }
        if (clazz.equals(Limits.class)) {
            return LimitsRealmProxy.getFieldNames();
        }
        if (clazz.equals(MealPlan.class)) {
            return MealPlanRealmProxy.getFieldNames();
        }
        if (clazz.equals(Objective.class)) {
            return ObjectiveRealmProxy.getFieldNames();
        }
        if (clazz.equals(Template.class)) {
            return TemplateRealmProxy.getFieldNames();
        }
        if (clazz.equals(UserFeature.class)) {
            return UserFeatureRealmProxy.getFieldNames();
        }
        if (clazz.equals(ContentFeature.class)) {
            return ContentFeatureRealmProxy.getFieldNames();
        }
        if (clazz.equals(HealthCondition.class)) {
            return HealthConditionRealmProxy.getFieldNames();
        }
        if (clazz.equals(Allergy.class)) {
            return AllergyRealmProxy.getFieldNames();
        }
        if (clazz.equals(CommonFeature.class)) {
            return CommonFeatureRealmProxy.getFieldNames();
        }
        if (clazz.equals(Address.class)) {
            return AddressRealmProxy.getFieldNames();
        }
        if (clazz.equals(DeviceAlert.class)) {
            return DeviceAlertRealmProxy.getFieldNames();
        }
        if (clazz.equals(VitalFeature.class)) {
            return VitalFeatureRealmProxy.getFieldNames();
        }
        if (clazz.equals(HipSize.class)) {
            return HipSizeRealmProxy.getFieldNames();
        }
        if (clazz.equals(Vital.class)) {
            return VitalRealmProxy.getFieldNames();
        }
        if (clazz.equals(ActivityIntervention.class)) {
            return ActivityInterventionRealmProxy.getFieldNames();
        }
        if (clazz.equals(Alert.class)) {
            return AlertRealmProxy.getFieldNames();
        }
        if (clazz.equals(UrlIdentifier.class)) {
            return UrlIdentifierRealmProxy.getFieldNames();
        }
        if (clazz.equals(Intervention.class)) {
            return InterventionRealmProxy.getFieldNames();
        }
        if (clazz.equals(Saturday.class)) {
            return SaturdayRealmProxy.getFieldNames();
        }
        if (clazz.equals(DietFeature.class)) {
            return DietFeatureRealmProxy.getFieldNames();
        }
        if (clazz.equals(FeedBackInput.class)) {
            return FeedBackInputRealmProxy.getFieldNames();
        }
        if (clazz.equals(Goal.class)) {
            return GoalRealmProxy.getFieldNames();
        }
        if (clazz.equals(Instructions.class)) {
            return InstructionsRealmProxy.getFieldNames();
        }
        if (clazz.equals(Wednesday.class)) {
            return WednesdayRealmProxy.getFieldNames();
        }
        if (clazz.equals(Friday.class)) {
            return FridayRealmProxy.getFieldNames();
        }
        if (clazz.equals(UserSettings.class)) {
            return UserSettingsRealmProxy.getFieldNames();
        }
        if (clazz.equals(ContentIntervention.class)) {
            return ContentInterventionRealmProxy.getFieldNames();
        }
        if (clazz.equals(BreakFast.class)) {
            return BreakFastRealmProxy.getFieldNames();
        }
        if (clazz.equals(Thursday.class)) {
            return ThursdayRealmProxy.getFieldNames();
        }
        if (clazz.equals(Feature.class)) {
            return FeatureRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);
        if (clazz.equals(RealmBoolean.class)) {
            return RealmBooleanRealmProxy.getTableName();
        }
        if (clazz.equals(Assessment.class)) {
            return AssessmentRealmProxy.getTableName();
        }
        if (clazz.equals(Tag.class)) {
            return TagRealmProxy.getTableName();
        }
        if (clazz.equals(ActivityItem.class)) {
            return ActivityItemRealmProxy.getTableName();
        }
        if (clazz.equals(TodoIntervention.class)) {
            return TodoInterventionRealmProxy.getTableName();
        }
        if (clazz.equals(Medicine.class)) {
            return MedicineRealmProxy.getTableName();
        }
        if (clazz.equals(Diagnosis.class)) {
            return DiagnosisRealmProxy.getTableName();
        }
        if (clazz.equals(Course.class)) {
            return CourseRealmProxy.getTableName();
        }
        if (clazz.equals(Lunch.class)) {
            return LunchRealmProxy.getTableName();
        }
        if (clazz.equals(Schedule.class)) {
            return ScheduleRealmProxy.getTableName();
        }
        if (clazz.equals(DietMealPlan.class)) {
            return DietMealPlanRealmProxy.getTableName();
        }
        if (clazz.equals(Note.class)) {
            return NoteRealmProxy.getTableName();
        }
        if (clazz.equals(VitalIntervention.class)) {
            return VitalInterventionRealmProxy.getTableName();
        }
        if (clazz.equals(Guardian.class)) {
            return GuardianRealmProxy.getTableName();
        }
        if (clazz.equals(MedicalContact.class)) {
            return MedicalContactRealmProxy.getTableName();
        }
        if (clazz.equals(CarePlanReminder.class)) {
            return CarePlanReminderRealmProxy.getTableName();
        }
        if (clazz.equals(Limit.class)) {
            return LimitRealmProxy.getTableName();
        }
        if (clazz.equals(Careplan.class)) {
            return CareplanRealmProxy.getTableName();
        }
        if (clazz.equals(Message.class)) {
            return MessageRealmProxy.getTableName();
        }
        if (clazz.equals(Action.class)) {
            return ActionRealmProxy.getTableName();
        }
        if (clazz.equals(Days.class)) {
            return DaysRealmProxy.getTableName();
        }
        if (clazz.equals(Activity.class)) {
            return ActivityRealmProxy.getTableName();
        }
        if (clazz.equals(Dinner.class)) {
            return DinnerRealmProxy.getTableName();
        }
        if (clazz.equals(WaistSize.class)) {
            return WaistSizeRealmProxy.getTableName();
        }
        if (clazz.equals(RealmString.class)) {
            return RealmStringRealmProxy.getTableName();
        }
        if (clazz.equals(Reminder.class)) {
            return ReminderRealmProxy.getTableName();
        }
        if (clazz.equals(DietTemplate.class)) {
            return DietTemplateRealmProxy.getTableName();
        }
        if (clazz.equals(MealTemplate.class)) {
            return MealTemplateRealmProxy.getTableName();
        }
        if (clazz.equals(Monday.class)) {
            return MondayRealmProxy.getTableName();
        }
        if (clazz.equals(Tuesday.class)) {
            return TuesdayRealmProxy.getTableName();
        }
        if (clazz.equals(User.class)) {
            return UserRealmProxy.getTableName();
        }
        if (clazz.equals(Timings.class)) {
            return TimingsRealmProxy.getTableName();
        }
        if (clazz.equals(Todo.class)) {
            return TodoRealmProxy.getTableName();
        }
        if (clazz.equals(Height.class)) {
            return HeightRealmProxy.getTableName();
        }
        if (clazz.equals(CareplanSummary.class)) {
            return CareplanSummaryRealmProxy.getTableName();
        }
        if (clazz.equals(Subjective.class)) {
            return SubjectiveRealmProxy.getTableName();
        }
        if (clazz.equals(Symoptoms.class)) {
            return SymoptomsRealmProxy.getTableName();
        }
        if (clazz.equals(ActivityFeature.class)) {
            return ActivityFeatureRealmProxy.getTableName();
        }
        if (clazz.equals(DietIntervention.class)) {
            return DietInterventionRealmProxy.getTableName();
        }
        if (clazz.equals(Limits.class)) {
            return LimitsRealmProxy.getTableName();
        }
        if (clazz.equals(MealPlan.class)) {
            return MealPlanRealmProxy.getTableName();
        }
        if (clazz.equals(Objective.class)) {
            return ObjectiveRealmProxy.getTableName();
        }
        if (clazz.equals(Template.class)) {
            return TemplateRealmProxy.getTableName();
        }
        if (clazz.equals(UserFeature.class)) {
            return UserFeatureRealmProxy.getTableName();
        }
        if (clazz.equals(ContentFeature.class)) {
            return ContentFeatureRealmProxy.getTableName();
        }
        if (clazz.equals(HealthCondition.class)) {
            return HealthConditionRealmProxy.getTableName();
        }
        if (clazz.equals(Allergy.class)) {
            return AllergyRealmProxy.getTableName();
        }
        if (clazz.equals(CommonFeature.class)) {
            return CommonFeatureRealmProxy.getTableName();
        }
        if (clazz.equals(Address.class)) {
            return AddressRealmProxy.getTableName();
        }
        if (clazz.equals(DeviceAlert.class)) {
            return DeviceAlertRealmProxy.getTableName();
        }
        if (clazz.equals(VitalFeature.class)) {
            return VitalFeatureRealmProxy.getTableName();
        }
        if (clazz.equals(HipSize.class)) {
            return HipSizeRealmProxy.getTableName();
        }
        if (clazz.equals(Vital.class)) {
            return VitalRealmProxy.getTableName();
        }
        if (clazz.equals(ActivityIntervention.class)) {
            return ActivityInterventionRealmProxy.getTableName();
        }
        if (clazz.equals(Alert.class)) {
            return AlertRealmProxy.getTableName();
        }
        if (clazz.equals(UrlIdentifier.class)) {
            return UrlIdentifierRealmProxy.getTableName();
        }
        if (clazz.equals(Intervention.class)) {
            return InterventionRealmProxy.getTableName();
        }
        if (clazz.equals(Saturday.class)) {
            return SaturdayRealmProxy.getTableName();
        }
        if (clazz.equals(DietFeature.class)) {
            return DietFeatureRealmProxy.getTableName();
        }
        if (clazz.equals(FeedBackInput.class)) {
            return FeedBackInputRealmProxy.getTableName();
        }
        if (clazz.equals(Goal.class)) {
            return GoalRealmProxy.getTableName();
        }
        if (clazz.equals(Instructions.class)) {
            return InstructionsRealmProxy.getTableName();
        }
        if (clazz.equals(Wednesday.class)) {
            return WednesdayRealmProxy.getTableName();
        }
        if (clazz.equals(Friday.class)) {
            return FridayRealmProxy.getTableName();
        }
        if (clazz.equals(UserSettings.class)) {
            return UserSettingsRealmProxy.getTableName();
        }
        if (clazz.equals(ContentIntervention.class)) {
            return ContentInterventionRealmProxy.getTableName();
        }
        if (clazz.equals(BreakFast.class)) {
            return BreakFastRealmProxy.getTableName();
        }
        if (clazz.equals(Thursday.class)) {
            return ThursdayRealmProxy.getTableName();
        }
        if (clazz.equals(Feature.class)) {
            return FeatureRealmProxy.getTableName();
        }
        throw getMissingProxyClassException(clazz);
    }

    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        RealmObjectContext objectContext = (RealmObjectContext) BaseRealm.objectContext.get();
        try {
            E e;
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);
            if (clazz.equals(RealmBoolean.class)) {
                e = (RealmModel) clazz.cast(new RealmBooleanRealmProxy());
            } else if (clazz.equals(Assessment.class)) {
                r2 = (RealmModel) clazz.cast(new AssessmentRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Tag.class)) {
                r2 = (RealmModel) clazz.cast(new TagRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(ActivityItem.class)) {
                r2 = (RealmModel) clazz.cast(new ActivityItemRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(TodoIntervention.class)) {
                r2 = (RealmModel) clazz.cast(new TodoInterventionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Medicine.class)) {
                r2 = (RealmModel) clazz.cast(new MedicineRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Diagnosis.class)) {
                r2 = (RealmModel) clazz.cast(new DiagnosisRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Course.class)) {
                r2 = (RealmModel) clazz.cast(new CourseRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Lunch.class)) {
                r2 = (RealmModel) clazz.cast(new LunchRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Schedule.class)) {
                r2 = (RealmModel) clazz.cast(new ScheduleRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(DietMealPlan.class)) {
                r2 = (RealmModel) clazz.cast(new DietMealPlanRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Note.class)) {
                r2 = (RealmModel) clazz.cast(new NoteRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(VitalIntervention.class)) {
                r2 = (RealmModel) clazz.cast(new VitalInterventionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Guardian.class)) {
                r2 = (RealmModel) clazz.cast(new GuardianRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(MedicalContact.class)) {
                r2 = (RealmModel) clazz.cast(new MedicalContactRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(CarePlanReminder.class)) {
                r2 = (RealmModel) clazz.cast(new CarePlanReminderRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Limit.class)) {
                r2 = (RealmModel) clazz.cast(new LimitRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Careplan.class)) {
                r2 = (RealmModel) clazz.cast(new CareplanRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Message.class)) {
                r2 = (RealmModel) clazz.cast(new MessageRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Action.class)) {
                r2 = (RealmModel) clazz.cast(new ActionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Days.class)) {
                r2 = (RealmModel) clazz.cast(new DaysRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Activity.class)) {
                r2 = (RealmModel) clazz.cast(new ActivityRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Dinner.class)) {
                r2 = (RealmModel) clazz.cast(new DinnerRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(WaistSize.class)) {
                r2 = (RealmModel) clazz.cast(new WaistSizeRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(RealmString.class)) {
                r2 = (RealmModel) clazz.cast(new RealmStringRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Reminder.class)) {
                r2 = (RealmModel) clazz.cast(new ReminderRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(DietTemplate.class)) {
                r2 = (RealmModel) clazz.cast(new DietTemplateRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(MealTemplate.class)) {
                r2 = (RealmModel) clazz.cast(new MealTemplateRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Monday.class)) {
                r2 = (RealmModel) clazz.cast(new MondayRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Tuesday.class)) {
                r2 = (RealmModel) clazz.cast(new TuesdayRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(User.class)) {
                r2 = (RealmModel) clazz.cast(new UserRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Timings.class)) {
                r2 = (RealmModel) clazz.cast(new TimingsRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Todo.class)) {
                r2 = (RealmModel) clazz.cast(new TodoRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Height.class)) {
                r2 = (RealmModel) clazz.cast(new HeightRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(CareplanSummary.class)) {
                r2 = (RealmModel) clazz.cast(new CareplanSummaryRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Subjective.class)) {
                r2 = (RealmModel) clazz.cast(new SubjectiveRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Symoptoms.class)) {
                r2 = (RealmModel) clazz.cast(new SymoptomsRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(ActivityFeature.class)) {
                r2 = (RealmModel) clazz.cast(new ActivityFeatureRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(DietIntervention.class)) {
                r2 = (RealmModel) clazz.cast(new DietInterventionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Limits.class)) {
                r2 = (RealmModel) clazz.cast(new LimitsRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(MealPlan.class)) {
                r2 = (RealmModel) clazz.cast(new MealPlanRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Objective.class)) {
                r2 = (RealmModel) clazz.cast(new ObjectiveRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Template.class)) {
                r2 = (RealmModel) clazz.cast(new TemplateRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(UserFeature.class)) {
                r2 = (RealmModel) clazz.cast(new UserFeatureRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(ContentFeature.class)) {
                r2 = (RealmModel) clazz.cast(new ContentFeatureRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(HealthCondition.class)) {
                r2 = (RealmModel) clazz.cast(new HealthConditionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Allergy.class)) {
                r2 = (RealmModel) clazz.cast(new AllergyRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(CommonFeature.class)) {
                r2 = (RealmModel) clazz.cast(new CommonFeatureRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Address.class)) {
                r2 = (RealmModel) clazz.cast(new AddressRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(DeviceAlert.class)) {
                r2 = (RealmModel) clazz.cast(new DeviceAlertRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(VitalFeature.class)) {
                r2 = (RealmModel) clazz.cast(new VitalFeatureRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(HipSize.class)) {
                r2 = (RealmModel) clazz.cast(new HipSizeRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Vital.class)) {
                r2 = (RealmModel) clazz.cast(new VitalRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(ActivityIntervention.class)) {
                r2 = (RealmModel) clazz.cast(new ActivityInterventionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Alert.class)) {
                r2 = (RealmModel) clazz.cast(new AlertRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(UrlIdentifier.class)) {
                r2 = (RealmModel) clazz.cast(new UrlIdentifierRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Intervention.class)) {
                r2 = (RealmModel) clazz.cast(new InterventionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Saturday.class)) {
                r2 = (RealmModel) clazz.cast(new SaturdayRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(DietFeature.class)) {
                r2 = (RealmModel) clazz.cast(new DietFeatureRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(FeedBackInput.class)) {
                r2 = (RealmModel) clazz.cast(new FeedBackInputRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Goal.class)) {
                r2 = (RealmModel) clazz.cast(new GoalRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Instructions.class)) {
                r2 = (RealmModel) clazz.cast(new InstructionsRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Wednesday.class)) {
                r2 = (RealmModel) clazz.cast(new WednesdayRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Friday.class)) {
                r2 = (RealmModel) clazz.cast(new FridayRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(UserSettings.class)) {
                r2 = (RealmModel) clazz.cast(new UserSettingsRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(ContentIntervention.class)) {
                r2 = (RealmModel) clazz.cast(new ContentInterventionRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(BreakFast.class)) {
                r2 = (RealmModel) clazz.cast(new BreakFastRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Thursday.class)) {
                r2 = (RealmModel) clazz.cast(new ThursdayRealmProxy());
                objectContext.clear();
            } else if (clazz.equals(Feature.class)) {
                r2 = (RealmModel) clazz.cast(new FeatureRealmProxy());
                objectContext.clear();
            } else {
                throw getMissingProxyClassException(clazz);
            }
            return e;
        } finally {
            objectContext.clear();
        }
    }

    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        Class<E> clazz = obj instanceof RealmObjectProxy ? obj.getClass().getSuperclass() : obj.getClass();
        if (clazz.equals(RealmBoolean.class)) {
            return (RealmModel) clazz.cast(RealmBooleanRealmProxy.copyOrUpdate(realm, (RealmBoolean) obj, update, cache));
        }
        if (clazz.equals(Assessment.class)) {
            return (RealmModel) clazz.cast(AssessmentRealmProxy.copyOrUpdate(realm, (Assessment) obj, update, cache));
        }
        if (clazz.equals(Tag.class)) {
            return (RealmModel) clazz.cast(TagRealmProxy.copyOrUpdate(realm, (Tag) obj, update, cache));
        }
        if (clazz.equals(ActivityItem.class)) {
            return (RealmModel) clazz.cast(ActivityItemRealmProxy.copyOrUpdate(realm, (ActivityItem) obj, update, cache));
        }
        if (clazz.equals(TodoIntervention.class)) {
            return (RealmModel) clazz.cast(TodoInterventionRealmProxy.copyOrUpdate(realm, (TodoIntervention) obj, update, cache));
        }
        if (clazz.equals(Medicine.class)) {
            return (RealmModel) clazz.cast(MedicineRealmProxy.copyOrUpdate(realm, (Medicine) obj, update, cache));
        }
        if (clazz.equals(Diagnosis.class)) {
            return (RealmModel) clazz.cast(DiagnosisRealmProxy.copyOrUpdate(realm, (Diagnosis) obj, update, cache));
        }
        if (clazz.equals(Course.class)) {
            return (RealmModel) clazz.cast(CourseRealmProxy.copyOrUpdate(realm, (Course) obj, update, cache));
        }
        if (clazz.equals(Lunch.class)) {
            return (RealmModel) clazz.cast(LunchRealmProxy.copyOrUpdate(realm, (Lunch) obj, update, cache));
        }
        if (clazz.equals(Schedule.class)) {
            return (RealmModel) clazz.cast(ScheduleRealmProxy.copyOrUpdate(realm, (Schedule) obj, update, cache));
        }
        if (clazz.equals(DietMealPlan.class)) {
            return (RealmModel) clazz.cast(DietMealPlanRealmProxy.copyOrUpdate(realm, (DietMealPlan) obj, update, cache));
        }
        if (clazz.equals(Note.class)) {
            return (RealmModel) clazz.cast(NoteRealmProxy.copyOrUpdate(realm, (Note) obj, update, cache));
        }
        if (clazz.equals(VitalIntervention.class)) {
            return (RealmModel) clazz.cast(VitalInterventionRealmProxy.copyOrUpdate(realm, (VitalIntervention) obj, update, cache));
        }
        if (clazz.equals(Guardian.class)) {
            return (RealmModel) clazz.cast(GuardianRealmProxy.copyOrUpdate(realm, (Guardian) obj, update, cache));
        }
        if (clazz.equals(MedicalContact.class)) {
            return (RealmModel) clazz.cast(MedicalContactRealmProxy.copyOrUpdate(realm, (MedicalContact) obj, update, cache));
        }
        if (clazz.equals(CarePlanReminder.class)) {
            return (RealmModel) clazz.cast(CarePlanReminderRealmProxy.copyOrUpdate(realm, (CarePlanReminder) obj, update, cache));
        }
        if (clazz.equals(Limit.class)) {
            return (RealmModel) clazz.cast(LimitRealmProxy.copyOrUpdate(realm, (Limit) obj, update, cache));
        }
        if (clazz.equals(Careplan.class)) {
            return (RealmModel) clazz.cast(CareplanRealmProxy.copyOrUpdate(realm, (Careplan) obj, update, cache));
        }
        if (clazz.equals(Message.class)) {
            return (RealmModel) clazz.cast(MessageRealmProxy.copyOrUpdate(realm, (Message) obj, update, cache));
        }
        if (clazz.equals(Action.class)) {
            return (RealmModel) clazz.cast(ActionRealmProxy.copyOrUpdate(realm, (Action) obj, update, cache));
        }
        if (clazz.equals(Days.class)) {
            return (RealmModel) clazz.cast(DaysRealmProxy.copyOrUpdate(realm, (Days) obj, update, cache));
        }
        if (clazz.equals(Activity.class)) {
            return (RealmModel) clazz.cast(ActivityRealmProxy.copyOrUpdate(realm, (Activity) obj, update, cache));
        }
        if (clazz.equals(Dinner.class)) {
            return (RealmModel) clazz.cast(DinnerRealmProxy.copyOrUpdate(realm, (Dinner) obj, update, cache));
        }
        if (clazz.equals(WaistSize.class)) {
            return (RealmModel) clazz.cast(WaistSizeRealmProxy.copyOrUpdate(realm, (WaistSize) obj, update, cache));
        }
        if (clazz.equals(RealmString.class)) {
            return (RealmModel) clazz.cast(RealmStringRealmProxy.copyOrUpdate(realm, (RealmString) obj, update, cache));
        }
        if (clazz.equals(Reminder.class)) {
            return (RealmModel) clazz.cast(ReminderRealmProxy.copyOrUpdate(realm, (Reminder) obj, update, cache));
        }
        if (clazz.equals(DietTemplate.class)) {
            return (RealmModel) clazz.cast(DietTemplateRealmProxy.copyOrUpdate(realm, (DietTemplate) obj, update, cache));
        }
        if (clazz.equals(MealTemplate.class)) {
            return (RealmModel) clazz.cast(MealTemplateRealmProxy.copyOrUpdate(realm, (MealTemplate) obj, update, cache));
        }
        if (clazz.equals(Monday.class)) {
            return (RealmModel) clazz.cast(MondayRealmProxy.copyOrUpdate(realm, (Monday) obj, update, cache));
        }
        if (clazz.equals(Tuesday.class)) {
            return (RealmModel) clazz.cast(TuesdayRealmProxy.copyOrUpdate(realm, (Tuesday) obj, update, cache));
        }
        if (clazz.equals(User.class)) {
            return (RealmModel) clazz.cast(UserRealmProxy.copyOrUpdate(realm, (User) obj, update, cache));
        }
        if (clazz.equals(Timings.class)) {
            return (RealmModel) clazz.cast(TimingsRealmProxy.copyOrUpdate(realm, (Timings) obj, update, cache));
        }
        if (clazz.equals(Todo.class)) {
            return (RealmModel) clazz.cast(TodoRealmProxy.copyOrUpdate(realm, (Todo) obj, update, cache));
        }
        if (clazz.equals(Height.class)) {
            return (RealmModel) clazz.cast(HeightRealmProxy.copyOrUpdate(realm, (Height) obj, update, cache));
        }
        if (clazz.equals(CareplanSummary.class)) {
            return (RealmModel) clazz.cast(CareplanSummaryRealmProxy.copyOrUpdate(realm, (CareplanSummary) obj, update, cache));
        }
        if (clazz.equals(Subjective.class)) {
            return (RealmModel) clazz.cast(SubjectiveRealmProxy.copyOrUpdate(realm, (Subjective) obj, update, cache));
        }
        if (clazz.equals(Symoptoms.class)) {
            return (RealmModel) clazz.cast(SymoptomsRealmProxy.copyOrUpdate(realm, (Symoptoms) obj, update, cache));
        }
        if (clazz.equals(ActivityFeature.class)) {
            return (RealmModel) clazz.cast(ActivityFeatureRealmProxy.copyOrUpdate(realm, (ActivityFeature) obj, update, cache));
        }
        if (clazz.equals(DietIntervention.class)) {
            return (RealmModel) clazz.cast(DietInterventionRealmProxy.copyOrUpdate(realm, (DietIntervention) obj, update, cache));
        }
        if (clazz.equals(Limits.class)) {
            return (RealmModel) clazz.cast(LimitsRealmProxy.copyOrUpdate(realm, (Limits) obj, update, cache));
        }
        if (clazz.equals(MealPlan.class)) {
            return (RealmModel) clazz.cast(MealPlanRealmProxy.copyOrUpdate(realm, (MealPlan) obj, update, cache));
        }
        if (clazz.equals(Objective.class)) {
            return (RealmModel) clazz.cast(ObjectiveRealmProxy.copyOrUpdate(realm, (Objective) obj, update, cache));
        }
        if (clazz.equals(Template.class)) {
            return (RealmModel) clazz.cast(TemplateRealmProxy.copyOrUpdate(realm, (Template) obj, update, cache));
        }
        if (clazz.equals(UserFeature.class)) {
            return (RealmModel) clazz.cast(UserFeatureRealmProxy.copyOrUpdate(realm, (UserFeature) obj, update, cache));
        }
        if (clazz.equals(ContentFeature.class)) {
            return (RealmModel) clazz.cast(ContentFeatureRealmProxy.copyOrUpdate(realm, (ContentFeature) obj, update, cache));
        }
        if (clazz.equals(HealthCondition.class)) {
            return (RealmModel) clazz.cast(HealthConditionRealmProxy.copyOrUpdate(realm, (HealthCondition) obj, update, cache));
        }
        if (clazz.equals(Allergy.class)) {
            return (RealmModel) clazz.cast(AllergyRealmProxy.copyOrUpdate(realm, (Allergy) obj, update, cache));
        }
        if (clazz.equals(CommonFeature.class)) {
            return (RealmModel) clazz.cast(CommonFeatureRealmProxy.copyOrUpdate(realm, (CommonFeature) obj, update, cache));
        }
        if (clazz.equals(Address.class)) {
            return (RealmModel) clazz.cast(AddressRealmProxy.copyOrUpdate(realm, (Address) obj, update, cache));
        }
        if (clazz.equals(DeviceAlert.class)) {
            return (RealmModel) clazz.cast(DeviceAlertRealmProxy.copyOrUpdate(realm, (DeviceAlert) obj, update, cache));
        }
        if (clazz.equals(VitalFeature.class)) {
            return (RealmModel) clazz.cast(VitalFeatureRealmProxy.copyOrUpdate(realm, (VitalFeature) obj, update, cache));
        }
        if (clazz.equals(HipSize.class)) {
            return (RealmModel) clazz.cast(HipSizeRealmProxy.copyOrUpdate(realm, (HipSize) obj, update, cache));
        }
        if (clazz.equals(Vital.class)) {
            return (RealmModel) clazz.cast(VitalRealmProxy.copyOrUpdate(realm, (Vital) obj, update, cache));
        }
        if (clazz.equals(ActivityIntervention.class)) {
            return (RealmModel) clazz.cast(ActivityInterventionRealmProxy.copyOrUpdate(realm, (ActivityIntervention) obj, update, cache));
        }
        if (clazz.equals(Alert.class)) {
            return (RealmModel) clazz.cast(AlertRealmProxy.copyOrUpdate(realm, (Alert) obj, update, cache));
        }
        if (clazz.equals(UrlIdentifier.class)) {
            return (RealmModel) clazz.cast(UrlIdentifierRealmProxy.copyOrUpdate(realm, (UrlIdentifier) obj, update, cache));
        }
        if (clazz.equals(Intervention.class)) {
            return (RealmModel) clazz.cast(InterventionRealmProxy.copyOrUpdate(realm, (Intervention) obj, update, cache));
        }
        if (clazz.equals(Saturday.class)) {
            return (RealmModel) clazz.cast(SaturdayRealmProxy.copyOrUpdate(realm, (Saturday) obj, update, cache));
        }
        if (clazz.equals(DietFeature.class)) {
            return (RealmModel) clazz.cast(DietFeatureRealmProxy.copyOrUpdate(realm, (DietFeature) obj, update, cache));
        }
        if (clazz.equals(FeedBackInput.class)) {
            return (RealmModel) clazz.cast(FeedBackInputRealmProxy.copyOrUpdate(realm, (FeedBackInput) obj, update, cache));
        }
        if (clazz.equals(Goal.class)) {
            return (RealmModel) clazz.cast(GoalRealmProxy.copyOrUpdate(realm, (Goal) obj, update, cache));
        }
        if (clazz.equals(Instructions.class)) {
            return (RealmModel) clazz.cast(InstructionsRealmProxy.copyOrUpdate(realm, (Instructions) obj, update, cache));
        }
        if (clazz.equals(Wednesday.class)) {
            return (RealmModel) clazz.cast(WednesdayRealmProxy.copyOrUpdate(realm, (Wednesday) obj, update, cache));
        }
        if (clazz.equals(Friday.class)) {
            return (RealmModel) clazz.cast(FridayRealmProxy.copyOrUpdate(realm, (Friday) obj, update, cache));
        }
        if (clazz.equals(UserSettings.class)) {
            return (RealmModel) clazz.cast(UserSettingsRealmProxy.copyOrUpdate(realm, (UserSettings) obj, update, cache));
        }
        if (clazz.equals(ContentIntervention.class)) {
            return (RealmModel) clazz.cast(ContentInterventionRealmProxy.copyOrUpdate(realm, (ContentIntervention) obj, update, cache));
        }
        if (clazz.equals(BreakFast.class)) {
            return (RealmModel) clazz.cast(BreakFastRealmProxy.copyOrUpdate(realm, (BreakFast) obj, update, cache));
        }
        if (clazz.equals(Thursday.class)) {
            return (RealmModel) clazz.cast(ThursdayRealmProxy.copyOrUpdate(realm, (Thursday) obj, update, cache));
        }
        if (clazz.equals(Feature.class)) {
            return (RealmModel) clazz.cast(FeatureRealmProxy.copyOrUpdate(realm, (Feature) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        Class<RealmModel> clazz = object instanceof RealmObjectProxy ? object.getClass().getSuperclass() : object.getClass();
        if (clazz.equals(RealmBoolean.class)) {
            RealmBooleanRealmProxy.insert(realm, (RealmBoolean) object, cache);
        } else if (clazz.equals(Assessment.class)) {
            AssessmentRealmProxy.insert(realm, (Assessment) object, cache);
        } else if (clazz.equals(Tag.class)) {
            TagRealmProxy.insert(realm, (Tag) object, cache);
        } else if (clazz.equals(ActivityItem.class)) {
            ActivityItemRealmProxy.insert(realm, (ActivityItem) object, cache);
        } else if (clazz.equals(TodoIntervention.class)) {
            TodoInterventionRealmProxy.insert(realm, (TodoIntervention) object, cache);
        } else if (clazz.equals(Medicine.class)) {
            MedicineRealmProxy.insert(realm, (Medicine) object, cache);
        } else if (clazz.equals(Diagnosis.class)) {
            DiagnosisRealmProxy.insert(realm, (Diagnosis) object, cache);
        } else if (clazz.equals(Course.class)) {
            CourseRealmProxy.insert(realm, (Course) object, cache);
        } else if (clazz.equals(Lunch.class)) {
            LunchRealmProxy.insert(realm, (Lunch) object, cache);
        } else if (clazz.equals(Schedule.class)) {
            ScheduleRealmProxy.insert(realm, (Schedule) object, cache);
        } else if (clazz.equals(DietMealPlan.class)) {
            DietMealPlanRealmProxy.insert(realm, (DietMealPlan) object, cache);
        } else if (clazz.equals(Note.class)) {
            NoteRealmProxy.insert(realm, (Note) object, cache);
        } else if (clazz.equals(VitalIntervention.class)) {
            VitalInterventionRealmProxy.insert(realm, (VitalIntervention) object, cache);
        } else if (clazz.equals(Guardian.class)) {
            GuardianRealmProxy.insert(realm, (Guardian) object, cache);
        } else if (clazz.equals(MedicalContact.class)) {
            MedicalContactRealmProxy.insert(realm, (MedicalContact) object, cache);
        } else if (clazz.equals(CarePlanReminder.class)) {
            CarePlanReminderRealmProxy.insert(realm, (CarePlanReminder) object, cache);
        } else if (clazz.equals(Limit.class)) {
            LimitRealmProxy.insert(realm, (Limit) object, cache);
        } else if (clazz.equals(Careplan.class)) {
            CareplanRealmProxy.insert(realm, (Careplan) object, cache);
        } else if (clazz.equals(Message.class)) {
            MessageRealmProxy.insert(realm, (Message) object, cache);
        } else if (clazz.equals(Action.class)) {
            ActionRealmProxy.insert(realm, (Action) object, cache);
        } else if (clazz.equals(Days.class)) {
            DaysRealmProxy.insert(realm, (Days) object, cache);
        } else if (clazz.equals(Activity.class)) {
            ActivityRealmProxy.insert(realm, (Activity) object, cache);
        } else if (clazz.equals(Dinner.class)) {
            DinnerRealmProxy.insert(realm, (Dinner) object, cache);
        } else if (clazz.equals(WaistSize.class)) {
            WaistSizeRealmProxy.insert(realm, (WaistSize) object, cache);
        } else if (clazz.equals(RealmString.class)) {
            RealmStringRealmProxy.insert(realm, (RealmString) object, cache);
        } else if (clazz.equals(Reminder.class)) {
            ReminderRealmProxy.insert(realm, (Reminder) object, cache);
        } else if (clazz.equals(DietTemplate.class)) {
            DietTemplateRealmProxy.insert(realm, (DietTemplate) object, cache);
        } else if (clazz.equals(MealTemplate.class)) {
            MealTemplateRealmProxy.insert(realm, (MealTemplate) object, cache);
        } else if (clazz.equals(Monday.class)) {
            MondayRealmProxy.insert(realm, (Monday) object, cache);
        } else if (clazz.equals(Tuesday.class)) {
            TuesdayRealmProxy.insert(realm, (Tuesday) object, cache);
        } else if (clazz.equals(User.class)) {
            UserRealmProxy.insert(realm, (User) object, cache);
        } else if (clazz.equals(Timings.class)) {
            TimingsRealmProxy.insert(realm, (Timings) object, cache);
        } else if (clazz.equals(Todo.class)) {
            TodoRealmProxy.insert(realm, (Todo) object, cache);
        } else if (clazz.equals(Height.class)) {
            HeightRealmProxy.insert(realm, (Height) object, cache);
        } else if (clazz.equals(CareplanSummary.class)) {
            CareplanSummaryRealmProxy.insert(realm, (CareplanSummary) object, cache);
        } else if (clazz.equals(Subjective.class)) {
            SubjectiveRealmProxy.insert(realm, (Subjective) object, cache);
        } else if (clazz.equals(Symoptoms.class)) {
            SymoptomsRealmProxy.insert(realm, (Symoptoms) object, cache);
        } else if (clazz.equals(ActivityFeature.class)) {
            ActivityFeatureRealmProxy.insert(realm, (ActivityFeature) object, cache);
        } else if (clazz.equals(DietIntervention.class)) {
            DietInterventionRealmProxy.insert(realm, (DietIntervention) object, cache);
        } else if (clazz.equals(Limits.class)) {
            LimitsRealmProxy.insert(realm, (Limits) object, cache);
        } else if (clazz.equals(MealPlan.class)) {
            MealPlanRealmProxy.insert(realm, (MealPlan) object, cache);
        } else if (clazz.equals(Objective.class)) {
            ObjectiveRealmProxy.insert(realm, (Objective) object, cache);
        } else if (clazz.equals(Template.class)) {
            TemplateRealmProxy.insert(realm, (Template) object, cache);
        } else if (clazz.equals(UserFeature.class)) {
            UserFeatureRealmProxy.insert(realm, (UserFeature) object, cache);
        } else if (clazz.equals(ContentFeature.class)) {
            ContentFeatureRealmProxy.insert(realm, (ContentFeature) object, cache);
        } else if (clazz.equals(HealthCondition.class)) {
            HealthConditionRealmProxy.insert(realm, (HealthCondition) object, cache);
        } else if (clazz.equals(Allergy.class)) {
            AllergyRealmProxy.insert(realm, (Allergy) object, cache);
        } else if (clazz.equals(CommonFeature.class)) {
            CommonFeatureRealmProxy.insert(realm, (CommonFeature) object, cache);
        } else if (clazz.equals(Address.class)) {
            AddressRealmProxy.insert(realm, (Address) object, cache);
        } else if (clazz.equals(DeviceAlert.class)) {
            DeviceAlertRealmProxy.insert(realm, (DeviceAlert) object, cache);
        } else if (clazz.equals(VitalFeature.class)) {
            VitalFeatureRealmProxy.insert(realm, (VitalFeature) object, cache);
        } else if (clazz.equals(HipSize.class)) {
            HipSizeRealmProxy.insert(realm, (HipSize) object, cache);
        } else if (clazz.equals(Vital.class)) {
            VitalRealmProxy.insert(realm, (Vital) object, cache);
        } else if (clazz.equals(ActivityIntervention.class)) {
            ActivityInterventionRealmProxy.insert(realm, (ActivityIntervention) object, cache);
        } else if (clazz.equals(Alert.class)) {
            AlertRealmProxy.insert(realm, (Alert) object, cache);
        } else if (clazz.equals(UrlIdentifier.class)) {
            UrlIdentifierRealmProxy.insert(realm, (UrlIdentifier) object, cache);
        } else if (clazz.equals(Intervention.class)) {
            InterventionRealmProxy.insert(realm, (Intervention) object, cache);
        } else if (clazz.equals(Saturday.class)) {
            SaturdayRealmProxy.insert(realm, (Saturday) object, cache);
        } else if (clazz.equals(DietFeature.class)) {
            DietFeatureRealmProxy.insert(realm, (DietFeature) object, cache);
        } else if (clazz.equals(FeedBackInput.class)) {
            FeedBackInputRealmProxy.insert(realm, (FeedBackInput) object, cache);
        } else if (clazz.equals(Goal.class)) {
            GoalRealmProxy.insert(realm, (Goal) object, cache);
        } else if (clazz.equals(Instructions.class)) {
            InstructionsRealmProxy.insert(realm, (Instructions) object, cache);
        } else if (clazz.equals(Wednesday.class)) {
            WednesdayRealmProxy.insert(realm, (Wednesday) object, cache);
        } else if (clazz.equals(Friday.class)) {
            FridayRealmProxy.insert(realm, (Friday) object, cache);
        } else if (clazz.equals(UserSettings.class)) {
            UserSettingsRealmProxy.insert(realm, (UserSettings) object, cache);
        } else if (clazz.equals(ContentIntervention.class)) {
            ContentInterventionRealmProxy.insert(realm, (ContentIntervention) object, cache);
        } else if (clazz.equals(BreakFast.class)) {
            BreakFastRealmProxy.insert(realm, (BreakFast) object, cache);
        } else if (clazz.equals(Thursday.class)) {
            ThursdayRealmProxy.insert(realm, (Thursday) object, cache);
        } else if (clazz.equals(Feature.class)) {
            FeatureRealmProxy.insert(realm, (Feature) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        Map<RealmModel, Long> cache = new HashMap(objects.size());
        if (iterator.hasNext()) {
            RealmModel object = (RealmModel) iterator.next();
            Class<RealmModel> clazz = object instanceof RealmObjectProxy ? object.getClass().getSuperclass() : object.getClass();
            if (clazz.equals(RealmBoolean.class)) {
                RealmBooleanRealmProxy.insert(realm, (RealmBoolean) object, cache);
            } else if (clazz.equals(Assessment.class)) {
                AssessmentRealmProxy.insert(realm, (Assessment) object, cache);
            } else if (clazz.equals(Tag.class)) {
                TagRealmProxy.insert(realm, (Tag) object, cache);
            } else if (clazz.equals(ActivityItem.class)) {
                ActivityItemRealmProxy.insert(realm, (ActivityItem) object, cache);
            } else if (clazz.equals(TodoIntervention.class)) {
                TodoInterventionRealmProxy.insert(realm, (TodoIntervention) object, cache);
            } else if (clazz.equals(Medicine.class)) {
                MedicineRealmProxy.insert(realm, (Medicine) object, cache);
            } else if (clazz.equals(Diagnosis.class)) {
                DiagnosisRealmProxy.insert(realm, (Diagnosis) object, cache);
            } else if (clazz.equals(Course.class)) {
                CourseRealmProxy.insert(realm, (Course) object, cache);
            } else if (clazz.equals(Lunch.class)) {
                LunchRealmProxy.insert(realm, (Lunch) object, cache);
            } else if (clazz.equals(Schedule.class)) {
                ScheduleRealmProxy.insert(realm, (Schedule) object, cache);
            } else if (clazz.equals(DietMealPlan.class)) {
                DietMealPlanRealmProxy.insert(realm, (DietMealPlan) object, cache);
            } else if (clazz.equals(Note.class)) {
                NoteRealmProxy.insert(realm, (Note) object, cache);
            } else if (clazz.equals(VitalIntervention.class)) {
                VitalInterventionRealmProxy.insert(realm, (VitalIntervention) object, cache);
            } else if (clazz.equals(Guardian.class)) {
                GuardianRealmProxy.insert(realm, (Guardian) object, cache);
            } else if (clazz.equals(MedicalContact.class)) {
                MedicalContactRealmProxy.insert(realm, (MedicalContact) object, cache);
            } else if (clazz.equals(CarePlanReminder.class)) {
                CarePlanReminderRealmProxy.insert(realm, (CarePlanReminder) object, cache);
            } else if (clazz.equals(Limit.class)) {
                LimitRealmProxy.insert(realm, (Limit) object, cache);
            } else if (clazz.equals(Careplan.class)) {
                CareplanRealmProxy.insert(realm, (Careplan) object, cache);
            } else if (clazz.equals(Message.class)) {
                MessageRealmProxy.insert(realm, (Message) object, cache);
            } else if (clazz.equals(Action.class)) {
                ActionRealmProxy.insert(realm, (Action) object, cache);
            } else if (clazz.equals(Days.class)) {
                DaysRealmProxy.insert(realm, (Days) object, cache);
            } else if (clazz.equals(Activity.class)) {
                ActivityRealmProxy.insert(realm, (Activity) object, cache);
            } else if (clazz.equals(Dinner.class)) {
                DinnerRealmProxy.insert(realm, (Dinner) object, cache);
            } else if (clazz.equals(WaistSize.class)) {
                WaistSizeRealmProxy.insert(realm, (WaistSize) object, cache);
            } else if (clazz.equals(RealmString.class)) {
                RealmStringRealmProxy.insert(realm, (RealmString) object, cache);
            } else if (clazz.equals(Reminder.class)) {
                ReminderRealmProxy.insert(realm, (Reminder) object, cache);
            } else if (clazz.equals(DietTemplate.class)) {
                DietTemplateRealmProxy.insert(realm, (DietTemplate) object, cache);
            } else if (clazz.equals(MealTemplate.class)) {
                MealTemplateRealmProxy.insert(realm, (MealTemplate) object, cache);
            } else if (clazz.equals(Monday.class)) {
                MondayRealmProxy.insert(realm, (Monday) object, cache);
            } else if (clazz.equals(Tuesday.class)) {
                TuesdayRealmProxy.insert(realm, (Tuesday) object, cache);
            } else if (clazz.equals(User.class)) {
                UserRealmProxy.insert(realm, (User) object, cache);
            } else if (clazz.equals(Timings.class)) {
                TimingsRealmProxy.insert(realm, (Timings) object, cache);
            } else if (clazz.equals(Todo.class)) {
                TodoRealmProxy.insert(realm, (Todo) object, cache);
            } else if (clazz.equals(Height.class)) {
                HeightRealmProxy.insert(realm, (Height) object, cache);
            } else if (clazz.equals(CareplanSummary.class)) {
                CareplanSummaryRealmProxy.insert(realm, (CareplanSummary) object, cache);
            } else if (clazz.equals(Subjective.class)) {
                SubjectiveRealmProxy.insert(realm, (Subjective) object, cache);
            } else if (clazz.equals(Symoptoms.class)) {
                SymoptomsRealmProxy.insert(realm, (Symoptoms) object, cache);
            } else if (clazz.equals(ActivityFeature.class)) {
                ActivityFeatureRealmProxy.insert(realm, (ActivityFeature) object, cache);
            } else if (clazz.equals(DietIntervention.class)) {
                DietInterventionRealmProxy.insert(realm, (DietIntervention) object, cache);
            } else if (clazz.equals(Limits.class)) {
                LimitsRealmProxy.insert(realm, (Limits) object, cache);
            } else if (clazz.equals(MealPlan.class)) {
                MealPlanRealmProxy.insert(realm, (MealPlan) object, cache);
            } else if (clazz.equals(Objective.class)) {
                ObjectiveRealmProxy.insert(realm, (Objective) object, cache);
            } else if (clazz.equals(Template.class)) {
                TemplateRealmProxy.insert(realm, (Template) object, cache);
            } else if (clazz.equals(UserFeature.class)) {
                UserFeatureRealmProxy.insert(realm, (UserFeature) object, cache);
            } else if (clazz.equals(ContentFeature.class)) {
                ContentFeatureRealmProxy.insert(realm, (ContentFeature) object, cache);
            } else if (clazz.equals(HealthCondition.class)) {
                HealthConditionRealmProxy.insert(realm, (HealthCondition) object, cache);
            } else if (clazz.equals(Allergy.class)) {
                AllergyRealmProxy.insert(realm, (Allergy) object, cache);
            } else if (clazz.equals(CommonFeature.class)) {
                CommonFeatureRealmProxy.insert(realm, (CommonFeature) object, cache);
            } else if (clazz.equals(Address.class)) {
                AddressRealmProxy.insert(realm, (Address) object, cache);
            } else if (clazz.equals(DeviceAlert.class)) {
                DeviceAlertRealmProxy.insert(realm, (DeviceAlert) object, cache);
            } else if (clazz.equals(VitalFeature.class)) {
                VitalFeatureRealmProxy.insert(realm, (VitalFeature) object, cache);
            } else if (clazz.equals(HipSize.class)) {
                HipSizeRealmProxy.insert(realm, (HipSize) object, cache);
            } else if (clazz.equals(Vital.class)) {
                VitalRealmProxy.insert(realm, (Vital) object, cache);
            } else if (clazz.equals(ActivityIntervention.class)) {
                ActivityInterventionRealmProxy.insert(realm, (ActivityIntervention) object, cache);
            } else if (clazz.equals(Alert.class)) {
                AlertRealmProxy.insert(realm, (Alert) object, cache);
            } else if (clazz.equals(UrlIdentifier.class)) {
                UrlIdentifierRealmProxy.insert(realm, (UrlIdentifier) object, cache);
            } else if (clazz.equals(Intervention.class)) {
                InterventionRealmProxy.insert(realm, (Intervention) object, cache);
            } else if (clazz.equals(Saturday.class)) {
                SaturdayRealmProxy.insert(realm, (Saturday) object, cache);
            } else if (clazz.equals(DietFeature.class)) {
                DietFeatureRealmProxy.insert(realm, (DietFeature) object, cache);
            } else if (clazz.equals(FeedBackInput.class)) {
                FeedBackInputRealmProxy.insert(realm, (FeedBackInput) object, cache);
            } else if (clazz.equals(Goal.class)) {
                GoalRealmProxy.insert(realm, (Goal) object, cache);
            } else if (clazz.equals(Instructions.class)) {
                InstructionsRealmProxy.insert(realm, (Instructions) object, cache);
            } else if (clazz.equals(Wednesday.class)) {
                WednesdayRealmProxy.insert(realm, (Wednesday) object, cache);
            } else if (clazz.equals(Friday.class)) {
                FridayRealmProxy.insert(realm, (Friday) object, cache);
            } else if (clazz.equals(UserSettings.class)) {
                UserSettingsRealmProxy.insert(realm, (UserSettings) object, cache);
            } else if (clazz.equals(ContentIntervention.class)) {
                ContentInterventionRealmProxy.insert(realm, (ContentIntervention) object, cache);
            } else if (clazz.equals(BreakFast.class)) {
                BreakFastRealmProxy.insert(realm, (BreakFast) object, cache);
            } else if (clazz.equals(Thursday.class)) {
                ThursdayRealmProxy.insert(realm, (Thursday) object, cache);
            } else if (clazz.equals(Feature.class)) {
                FeatureRealmProxy.insert(realm, (Feature) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (!iterator.hasNext()) {
                return;
            }
            if (clazz.equals(RealmBoolean.class)) {
                RealmBooleanRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Assessment.class)) {
                AssessmentRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Tag.class)) {
                TagRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(ActivityItem.class)) {
                ActivityItemRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(TodoIntervention.class)) {
                TodoInterventionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Medicine.class)) {
                MedicineRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Diagnosis.class)) {
                DiagnosisRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Course.class)) {
                CourseRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Lunch.class)) {
                LunchRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Schedule.class)) {
                ScheduleRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(DietMealPlan.class)) {
                DietMealPlanRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Note.class)) {
                NoteRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(VitalIntervention.class)) {
                VitalInterventionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Guardian.class)) {
                GuardianRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(MedicalContact.class)) {
                MedicalContactRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(CarePlanReminder.class)) {
                CarePlanReminderRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Limit.class)) {
                LimitRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Careplan.class)) {
                CareplanRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Message.class)) {
                MessageRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Action.class)) {
                ActionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Days.class)) {
                DaysRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Activity.class)) {
                ActivityRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Dinner.class)) {
                DinnerRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(WaistSize.class)) {
                WaistSizeRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(RealmString.class)) {
                RealmStringRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Reminder.class)) {
                ReminderRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(DietTemplate.class)) {
                DietTemplateRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(MealTemplate.class)) {
                MealTemplateRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Monday.class)) {
                MondayRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Tuesday.class)) {
                TuesdayRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(User.class)) {
                UserRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Timings.class)) {
                TimingsRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Todo.class)) {
                TodoRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Height.class)) {
                HeightRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(CareplanSummary.class)) {
                CareplanSummaryRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Subjective.class)) {
                SubjectiveRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Symoptoms.class)) {
                SymoptomsRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(ActivityFeature.class)) {
                ActivityFeatureRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(DietIntervention.class)) {
                DietInterventionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Limits.class)) {
                LimitsRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(MealPlan.class)) {
                MealPlanRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Objective.class)) {
                ObjectiveRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Template.class)) {
                TemplateRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(UserFeature.class)) {
                UserFeatureRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(ContentFeature.class)) {
                ContentFeatureRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(HealthCondition.class)) {
                HealthConditionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Allergy.class)) {
                AllergyRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(CommonFeature.class)) {
                CommonFeatureRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Address.class)) {
                AddressRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(DeviceAlert.class)) {
                DeviceAlertRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(VitalFeature.class)) {
                VitalFeatureRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(HipSize.class)) {
                HipSizeRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Vital.class)) {
                VitalRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(ActivityIntervention.class)) {
                ActivityInterventionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Alert.class)) {
                AlertRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(UrlIdentifier.class)) {
                UrlIdentifierRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Intervention.class)) {
                InterventionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Saturday.class)) {
                SaturdayRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(DietFeature.class)) {
                DietFeatureRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(FeedBackInput.class)) {
                FeedBackInputRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Goal.class)) {
                GoalRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Instructions.class)) {
                InstructionsRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Wednesday.class)) {
                WednesdayRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Friday.class)) {
                FridayRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(UserSettings.class)) {
                UserSettingsRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(ContentIntervention.class)) {
                ContentInterventionRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(BreakFast.class)) {
                BreakFastRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Thursday.class)) {
                ThursdayRealmProxy.insert(realm, iterator, cache);
            } else if (clazz.equals(Feature.class)) {
                FeatureRealmProxy.insert(realm, iterator, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
        }
    }

    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        Class<RealmModel> clazz = obj instanceof RealmObjectProxy ? obj.getClass().getSuperclass() : obj.getClass();
        if (clazz.equals(RealmBoolean.class)) {
            RealmBooleanRealmProxy.insertOrUpdate(realm, (RealmBoolean) obj, cache);
        } else if (clazz.equals(Assessment.class)) {
            AssessmentRealmProxy.insertOrUpdate(realm, (Assessment) obj, cache);
        } else if (clazz.equals(Tag.class)) {
            TagRealmProxy.insertOrUpdate(realm, (Tag) obj, cache);
        } else if (clazz.equals(ActivityItem.class)) {
            ActivityItemRealmProxy.insertOrUpdate(realm, (ActivityItem) obj, cache);
        } else if (clazz.equals(TodoIntervention.class)) {
            TodoInterventionRealmProxy.insertOrUpdate(realm, (TodoIntervention) obj, cache);
        } else if (clazz.equals(Medicine.class)) {
            MedicineRealmProxy.insertOrUpdate(realm, (Medicine) obj, cache);
        } else if (clazz.equals(Diagnosis.class)) {
            DiagnosisRealmProxy.insertOrUpdate(realm, (Diagnosis) obj, cache);
        } else if (clazz.equals(Course.class)) {
            CourseRealmProxy.insertOrUpdate(realm, (Course) obj, cache);
        } else if (clazz.equals(Lunch.class)) {
            LunchRealmProxy.insertOrUpdate(realm, (Lunch) obj, cache);
        } else if (clazz.equals(Schedule.class)) {
            ScheduleRealmProxy.insertOrUpdate(realm, (Schedule) obj, cache);
        } else if (clazz.equals(DietMealPlan.class)) {
            DietMealPlanRealmProxy.insertOrUpdate(realm, (DietMealPlan) obj, cache);
        } else if (clazz.equals(Note.class)) {
            NoteRealmProxy.insertOrUpdate(realm, (Note) obj, cache);
        } else if (clazz.equals(VitalIntervention.class)) {
            VitalInterventionRealmProxy.insertOrUpdate(realm, (VitalIntervention) obj, cache);
        } else if (clazz.equals(Guardian.class)) {
            GuardianRealmProxy.insertOrUpdate(realm, (Guardian) obj, cache);
        } else if (clazz.equals(MedicalContact.class)) {
            MedicalContactRealmProxy.insertOrUpdate(realm, (MedicalContact) obj, cache);
        } else if (clazz.equals(CarePlanReminder.class)) {
            CarePlanReminderRealmProxy.insertOrUpdate(realm, (CarePlanReminder) obj, cache);
        } else if (clazz.equals(Limit.class)) {
            LimitRealmProxy.insertOrUpdate(realm, (Limit) obj, cache);
        } else if (clazz.equals(Careplan.class)) {
            CareplanRealmProxy.insertOrUpdate(realm, (Careplan) obj, cache);
        } else if (clazz.equals(Message.class)) {
            MessageRealmProxy.insertOrUpdate(realm, (Message) obj, cache);
        } else if (clazz.equals(Action.class)) {
            ActionRealmProxy.insertOrUpdate(realm, (Action) obj, cache);
        } else if (clazz.equals(Days.class)) {
            DaysRealmProxy.insertOrUpdate(realm, (Days) obj, cache);
        } else if (clazz.equals(Activity.class)) {
            ActivityRealmProxy.insertOrUpdate(realm, (Activity) obj, cache);
        } else if (clazz.equals(Dinner.class)) {
            DinnerRealmProxy.insertOrUpdate(realm, (Dinner) obj, cache);
        } else if (clazz.equals(WaistSize.class)) {
            WaistSizeRealmProxy.insertOrUpdate(realm, (WaistSize) obj, cache);
        } else if (clazz.equals(RealmString.class)) {
            RealmStringRealmProxy.insertOrUpdate(realm, (RealmString) obj, cache);
        } else if (clazz.equals(Reminder.class)) {
            ReminderRealmProxy.insertOrUpdate(realm, (Reminder) obj, cache);
        } else if (clazz.equals(DietTemplate.class)) {
            DietTemplateRealmProxy.insertOrUpdate(realm, (DietTemplate) obj, cache);
        } else if (clazz.equals(MealTemplate.class)) {
            MealTemplateRealmProxy.insertOrUpdate(realm, (MealTemplate) obj, cache);
        } else if (clazz.equals(Monday.class)) {
            MondayRealmProxy.insertOrUpdate(realm, (Monday) obj, cache);
        } else if (clazz.equals(Tuesday.class)) {
            TuesdayRealmProxy.insertOrUpdate(realm, (Tuesday) obj, cache);
        } else if (clazz.equals(User.class)) {
            UserRealmProxy.insertOrUpdate(realm, (User) obj, cache);
        } else if (clazz.equals(Timings.class)) {
            TimingsRealmProxy.insertOrUpdate(realm, (Timings) obj, cache);
        } else if (clazz.equals(Todo.class)) {
            TodoRealmProxy.insertOrUpdate(realm, (Todo) obj, cache);
        } else if (clazz.equals(Height.class)) {
            HeightRealmProxy.insertOrUpdate(realm, (Height) obj, cache);
        } else if (clazz.equals(CareplanSummary.class)) {
            CareplanSummaryRealmProxy.insertOrUpdate(realm, (CareplanSummary) obj, cache);
        } else if (clazz.equals(Subjective.class)) {
            SubjectiveRealmProxy.insertOrUpdate(realm, (Subjective) obj, cache);
        } else if (clazz.equals(Symoptoms.class)) {
            SymoptomsRealmProxy.insertOrUpdate(realm, (Symoptoms) obj, cache);
        } else if (clazz.equals(ActivityFeature.class)) {
            ActivityFeatureRealmProxy.insertOrUpdate(realm, (ActivityFeature) obj, cache);
        } else if (clazz.equals(DietIntervention.class)) {
            DietInterventionRealmProxy.insertOrUpdate(realm, (DietIntervention) obj, cache);
        } else if (clazz.equals(Limits.class)) {
            LimitsRealmProxy.insertOrUpdate(realm, (Limits) obj, cache);
        } else if (clazz.equals(MealPlan.class)) {
            MealPlanRealmProxy.insertOrUpdate(realm, (MealPlan) obj, cache);
        } else if (clazz.equals(Objective.class)) {
            ObjectiveRealmProxy.insertOrUpdate(realm, (Objective) obj, cache);
        } else if (clazz.equals(Template.class)) {
            TemplateRealmProxy.insertOrUpdate(realm, (Template) obj, cache);
        } else if (clazz.equals(UserFeature.class)) {
            UserFeatureRealmProxy.insertOrUpdate(realm, (UserFeature) obj, cache);
        } else if (clazz.equals(ContentFeature.class)) {
            ContentFeatureRealmProxy.insertOrUpdate(realm, (ContentFeature) obj, cache);
        } else if (clazz.equals(HealthCondition.class)) {
            HealthConditionRealmProxy.insertOrUpdate(realm, (HealthCondition) obj, cache);
        } else if (clazz.equals(Allergy.class)) {
            AllergyRealmProxy.insertOrUpdate(realm, (Allergy) obj, cache);
        } else if (clazz.equals(CommonFeature.class)) {
            CommonFeatureRealmProxy.insertOrUpdate(realm, (CommonFeature) obj, cache);
        } else if (clazz.equals(Address.class)) {
            AddressRealmProxy.insertOrUpdate(realm, (Address) obj, cache);
        } else if (clazz.equals(DeviceAlert.class)) {
            DeviceAlertRealmProxy.insertOrUpdate(realm, (DeviceAlert) obj, cache);
        } else if (clazz.equals(VitalFeature.class)) {
            VitalFeatureRealmProxy.insertOrUpdate(realm, (VitalFeature) obj, cache);
        } else if (clazz.equals(HipSize.class)) {
            HipSizeRealmProxy.insertOrUpdate(realm, (HipSize) obj, cache);
        } else if (clazz.equals(Vital.class)) {
            VitalRealmProxy.insertOrUpdate(realm, (Vital) obj, cache);
        } else if (clazz.equals(ActivityIntervention.class)) {
            ActivityInterventionRealmProxy.insertOrUpdate(realm, (ActivityIntervention) obj, cache);
        } else if (clazz.equals(Alert.class)) {
            AlertRealmProxy.insertOrUpdate(realm, (Alert) obj, cache);
        } else if (clazz.equals(UrlIdentifier.class)) {
            UrlIdentifierRealmProxy.insertOrUpdate(realm, (UrlIdentifier) obj, cache);
        } else if (clazz.equals(Intervention.class)) {
            InterventionRealmProxy.insertOrUpdate(realm, (Intervention) obj, cache);
        } else if (clazz.equals(Saturday.class)) {
            SaturdayRealmProxy.insertOrUpdate(realm, (Saturday) obj, cache);
        } else if (clazz.equals(DietFeature.class)) {
            DietFeatureRealmProxy.insertOrUpdate(realm, (DietFeature) obj, cache);
        } else if (clazz.equals(FeedBackInput.class)) {
            FeedBackInputRealmProxy.insertOrUpdate(realm, (FeedBackInput) obj, cache);
        } else if (clazz.equals(Goal.class)) {
            GoalRealmProxy.insertOrUpdate(realm, (Goal) obj, cache);
        } else if (clazz.equals(Instructions.class)) {
            InstructionsRealmProxy.insertOrUpdate(realm, (Instructions) obj, cache);
        } else if (clazz.equals(Wednesday.class)) {
            WednesdayRealmProxy.insertOrUpdate(realm, (Wednesday) obj, cache);
        } else if (clazz.equals(Friday.class)) {
            FridayRealmProxy.insertOrUpdate(realm, (Friday) obj, cache);
        } else if (clazz.equals(UserSettings.class)) {
            UserSettingsRealmProxy.insertOrUpdate(realm, (UserSettings) obj, cache);
        } else if (clazz.equals(ContentIntervention.class)) {
            ContentInterventionRealmProxy.insertOrUpdate(realm, (ContentIntervention) obj, cache);
        } else if (clazz.equals(BreakFast.class)) {
            BreakFastRealmProxy.insertOrUpdate(realm, (BreakFast) obj, cache);
        } else if (clazz.equals(Thursday.class)) {
            ThursdayRealmProxy.insertOrUpdate(realm, (Thursday) obj, cache);
        } else if (clazz.equals(Feature.class)) {
            FeatureRealmProxy.insertOrUpdate(realm, (Feature) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        Map<RealmModel, Long> cache = new HashMap(objects.size());
        if (iterator.hasNext()) {
            RealmModel object = (RealmModel) iterator.next();
            Class<RealmModel> clazz = object instanceof RealmObjectProxy ? object.getClass().getSuperclass() : object.getClass();
            if (clazz.equals(RealmBoolean.class)) {
                RealmBooleanRealmProxy.insertOrUpdate(realm, (RealmBoolean) object, cache);
            } else if (clazz.equals(Assessment.class)) {
                AssessmentRealmProxy.insertOrUpdate(realm, (Assessment) object, cache);
            } else if (clazz.equals(Tag.class)) {
                TagRealmProxy.insertOrUpdate(realm, (Tag) object, cache);
            } else if (clazz.equals(ActivityItem.class)) {
                ActivityItemRealmProxy.insertOrUpdate(realm, (ActivityItem) object, cache);
            } else if (clazz.equals(TodoIntervention.class)) {
                TodoInterventionRealmProxy.insertOrUpdate(realm, (TodoIntervention) object, cache);
            } else if (clazz.equals(Medicine.class)) {
                MedicineRealmProxy.insertOrUpdate(realm, (Medicine) object, cache);
            } else if (clazz.equals(Diagnosis.class)) {
                DiagnosisRealmProxy.insertOrUpdate(realm, (Diagnosis) object, cache);
            } else if (clazz.equals(Course.class)) {
                CourseRealmProxy.insertOrUpdate(realm, (Course) object, cache);
            } else if (clazz.equals(Lunch.class)) {
                LunchRealmProxy.insertOrUpdate(realm, (Lunch) object, cache);
            } else if (clazz.equals(Schedule.class)) {
                ScheduleRealmProxy.insertOrUpdate(realm, (Schedule) object, cache);
            } else if (clazz.equals(DietMealPlan.class)) {
                DietMealPlanRealmProxy.insertOrUpdate(realm, (DietMealPlan) object, cache);
            } else if (clazz.equals(Note.class)) {
                NoteRealmProxy.insertOrUpdate(realm, (Note) object, cache);
            } else if (clazz.equals(VitalIntervention.class)) {
                VitalInterventionRealmProxy.insertOrUpdate(realm, (VitalIntervention) object, cache);
            } else if (clazz.equals(Guardian.class)) {
                GuardianRealmProxy.insertOrUpdate(realm, (Guardian) object, cache);
            } else if (clazz.equals(MedicalContact.class)) {
                MedicalContactRealmProxy.insertOrUpdate(realm, (MedicalContact) object, cache);
            } else if (clazz.equals(CarePlanReminder.class)) {
                CarePlanReminderRealmProxy.insertOrUpdate(realm, (CarePlanReminder) object, cache);
            } else if (clazz.equals(Limit.class)) {
                LimitRealmProxy.insertOrUpdate(realm, (Limit) object, cache);
            } else if (clazz.equals(Careplan.class)) {
                CareplanRealmProxy.insertOrUpdate(realm, (Careplan) object, cache);
            } else if (clazz.equals(Message.class)) {
                MessageRealmProxy.insertOrUpdate(realm, (Message) object, cache);
            } else if (clazz.equals(Action.class)) {
                ActionRealmProxy.insertOrUpdate(realm, (Action) object, cache);
            } else if (clazz.equals(Days.class)) {
                DaysRealmProxy.insertOrUpdate(realm, (Days) object, cache);
            } else if (clazz.equals(Activity.class)) {
                ActivityRealmProxy.insertOrUpdate(realm, (Activity) object, cache);
            } else if (clazz.equals(Dinner.class)) {
                DinnerRealmProxy.insertOrUpdate(realm, (Dinner) object, cache);
            } else if (clazz.equals(WaistSize.class)) {
                WaistSizeRealmProxy.insertOrUpdate(realm, (WaistSize) object, cache);
            } else if (clazz.equals(RealmString.class)) {
                RealmStringRealmProxy.insertOrUpdate(realm, (RealmString) object, cache);
            } else if (clazz.equals(Reminder.class)) {
                ReminderRealmProxy.insertOrUpdate(realm, (Reminder) object, cache);
            } else if (clazz.equals(DietTemplate.class)) {
                DietTemplateRealmProxy.insertOrUpdate(realm, (DietTemplate) object, cache);
            } else if (clazz.equals(MealTemplate.class)) {
                MealTemplateRealmProxy.insertOrUpdate(realm, (MealTemplate) object, cache);
            } else if (clazz.equals(Monday.class)) {
                MondayRealmProxy.insertOrUpdate(realm, (Monday) object, cache);
            } else if (clazz.equals(Tuesday.class)) {
                TuesdayRealmProxy.insertOrUpdate(realm, (Tuesday) object, cache);
            } else if (clazz.equals(User.class)) {
                UserRealmProxy.insertOrUpdate(realm, (User) object, cache);
            } else if (clazz.equals(Timings.class)) {
                TimingsRealmProxy.insertOrUpdate(realm, (Timings) object, cache);
            } else if (clazz.equals(Todo.class)) {
                TodoRealmProxy.insertOrUpdate(realm, (Todo) object, cache);
            } else if (clazz.equals(Height.class)) {
                HeightRealmProxy.insertOrUpdate(realm, (Height) object, cache);
            } else if (clazz.equals(CareplanSummary.class)) {
                CareplanSummaryRealmProxy.insertOrUpdate(realm, (CareplanSummary) object, cache);
            } else if (clazz.equals(Subjective.class)) {
                SubjectiveRealmProxy.insertOrUpdate(realm, (Subjective) object, cache);
            } else if (clazz.equals(Symoptoms.class)) {
                SymoptomsRealmProxy.insertOrUpdate(realm, (Symoptoms) object, cache);
            } else if (clazz.equals(ActivityFeature.class)) {
                ActivityFeatureRealmProxy.insertOrUpdate(realm, (ActivityFeature) object, cache);
            } else if (clazz.equals(DietIntervention.class)) {
                DietInterventionRealmProxy.insertOrUpdate(realm, (DietIntervention) object, cache);
            } else if (clazz.equals(Limits.class)) {
                LimitsRealmProxy.insertOrUpdate(realm, (Limits) object, cache);
            } else if (clazz.equals(MealPlan.class)) {
                MealPlanRealmProxy.insertOrUpdate(realm, (MealPlan) object, cache);
            } else if (clazz.equals(Objective.class)) {
                ObjectiveRealmProxy.insertOrUpdate(realm, (Objective) object, cache);
            } else if (clazz.equals(Template.class)) {
                TemplateRealmProxy.insertOrUpdate(realm, (Template) object, cache);
            } else if (clazz.equals(UserFeature.class)) {
                UserFeatureRealmProxy.insertOrUpdate(realm, (UserFeature) object, cache);
            } else if (clazz.equals(ContentFeature.class)) {
                ContentFeatureRealmProxy.insertOrUpdate(realm, (ContentFeature) object, cache);
            } else if (clazz.equals(HealthCondition.class)) {
                HealthConditionRealmProxy.insertOrUpdate(realm, (HealthCondition) object, cache);
            } else if (clazz.equals(Allergy.class)) {
                AllergyRealmProxy.insertOrUpdate(realm, (Allergy) object, cache);
            } else if (clazz.equals(CommonFeature.class)) {
                CommonFeatureRealmProxy.insertOrUpdate(realm, (CommonFeature) object, cache);
            } else if (clazz.equals(Address.class)) {
                AddressRealmProxy.insertOrUpdate(realm, (Address) object, cache);
            } else if (clazz.equals(DeviceAlert.class)) {
                DeviceAlertRealmProxy.insertOrUpdate(realm, (DeviceAlert) object, cache);
            } else if (clazz.equals(VitalFeature.class)) {
                VitalFeatureRealmProxy.insertOrUpdate(realm, (VitalFeature) object, cache);
            } else if (clazz.equals(HipSize.class)) {
                HipSizeRealmProxy.insertOrUpdate(realm, (HipSize) object, cache);
            } else if (clazz.equals(Vital.class)) {
                VitalRealmProxy.insertOrUpdate(realm, (Vital) object, cache);
            } else if (clazz.equals(ActivityIntervention.class)) {
                ActivityInterventionRealmProxy.insertOrUpdate(realm, (ActivityIntervention) object, cache);
            } else if (clazz.equals(Alert.class)) {
                AlertRealmProxy.insertOrUpdate(realm, (Alert) object, cache);
            } else if (clazz.equals(UrlIdentifier.class)) {
                UrlIdentifierRealmProxy.insertOrUpdate(realm, (UrlIdentifier) object, cache);
            } else if (clazz.equals(Intervention.class)) {
                InterventionRealmProxy.insertOrUpdate(realm, (Intervention) object, cache);
            } else if (clazz.equals(Saturday.class)) {
                SaturdayRealmProxy.insertOrUpdate(realm, (Saturday) object, cache);
            } else if (clazz.equals(DietFeature.class)) {
                DietFeatureRealmProxy.insertOrUpdate(realm, (DietFeature) object, cache);
            } else if (clazz.equals(FeedBackInput.class)) {
                FeedBackInputRealmProxy.insertOrUpdate(realm, (FeedBackInput) object, cache);
            } else if (clazz.equals(Goal.class)) {
                GoalRealmProxy.insertOrUpdate(realm, (Goal) object, cache);
            } else if (clazz.equals(Instructions.class)) {
                InstructionsRealmProxy.insertOrUpdate(realm, (Instructions) object, cache);
            } else if (clazz.equals(Wednesday.class)) {
                WednesdayRealmProxy.insertOrUpdate(realm, (Wednesday) object, cache);
            } else if (clazz.equals(Friday.class)) {
                FridayRealmProxy.insertOrUpdate(realm, (Friday) object, cache);
            } else if (clazz.equals(UserSettings.class)) {
                UserSettingsRealmProxy.insertOrUpdate(realm, (UserSettings) object, cache);
            } else if (clazz.equals(ContentIntervention.class)) {
                ContentInterventionRealmProxy.insertOrUpdate(realm, (ContentIntervention) object, cache);
            } else if (clazz.equals(BreakFast.class)) {
                BreakFastRealmProxy.insertOrUpdate(realm, (BreakFast) object, cache);
            } else if (clazz.equals(Thursday.class)) {
                ThursdayRealmProxy.insertOrUpdate(realm, (Thursday) object, cache);
            } else if (clazz.equals(Feature.class)) {
                FeatureRealmProxy.insertOrUpdate(realm, (Feature) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (!iterator.hasNext()) {
                return;
            }
            if (clazz.equals(RealmBoolean.class)) {
                RealmBooleanRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Assessment.class)) {
                AssessmentRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Tag.class)) {
                TagRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(ActivityItem.class)) {
                ActivityItemRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(TodoIntervention.class)) {
                TodoInterventionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Medicine.class)) {
                MedicineRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Diagnosis.class)) {
                DiagnosisRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Course.class)) {
                CourseRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Lunch.class)) {
                LunchRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Schedule.class)) {
                ScheduleRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(DietMealPlan.class)) {
                DietMealPlanRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Note.class)) {
                NoteRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(VitalIntervention.class)) {
                VitalInterventionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Guardian.class)) {
                GuardianRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(MedicalContact.class)) {
                MedicalContactRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(CarePlanReminder.class)) {
                CarePlanReminderRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Limit.class)) {
                LimitRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Careplan.class)) {
                CareplanRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Message.class)) {
                MessageRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Action.class)) {
                ActionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Days.class)) {
                DaysRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Activity.class)) {
                ActivityRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Dinner.class)) {
                DinnerRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(WaistSize.class)) {
                WaistSizeRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(RealmString.class)) {
                RealmStringRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Reminder.class)) {
                ReminderRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(DietTemplate.class)) {
                DietTemplateRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(MealTemplate.class)) {
                MealTemplateRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Monday.class)) {
                MondayRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Tuesday.class)) {
                TuesdayRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(User.class)) {
                UserRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Timings.class)) {
                TimingsRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Todo.class)) {
                TodoRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Height.class)) {
                HeightRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(CareplanSummary.class)) {
                CareplanSummaryRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Subjective.class)) {
                SubjectiveRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Symoptoms.class)) {
                SymoptomsRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(ActivityFeature.class)) {
                ActivityFeatureRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(DietIntervention.class)) {
                DietInterventionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Limits.class)) {
                LimitsRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(MealPlan.class)) {
                MealPlanRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Objective.class)) {
                ObjectiveRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Template.class)) {
                TemplateRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(UserFeature.class)) {
                UserFeatureRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(ContentFeature.class)) {
                ContentFeatureRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(HealthCondition.class)) {
                HealthConditionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Allergy.class)) {
                AllergyRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(CommonFeature.class)) {
                CommonFeatureRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Address.class)) {
                AddressRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(DeviceAlert.class)) {
                DeviceAlertRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(VitalFeature.class)) {
                VitalFeatureRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(HipSize.class)) {
                HipSizeRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Vital.class)) {
                VitalRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(ActivityIntervention.class)) {
                ActivityInterventionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Alert.class)) {
                AlertRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(UrlIdentifier.class)) {
                UrlIdentifierRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Intervention.class)) {
                InterventionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Saturday.class)) {
                SaturdayRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(DietFeature.class)) {
                DietFeatureRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(FeedBackInput.class)) {
                FeedBackInputRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Goal.class)) {
                GoalRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Instructions.class)) {
                InstructionsRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Wednesday.class)) {
                WednesdayRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Friday.class)) {
                FridayRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(UserSettings.class)) {
                UserSettingsRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(ContentIntervention.class)) {
                ContentInterventionRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(BreakFast.class)) {
                BreakFastRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Thursday.class)) {
                ThursdayRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else if (clazz.equals(Feature.class)) {
                FeatureRealmProxy.insertOrUpdate(realm, iterator, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
        }
    }

    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update) throws JSONException {
        checkClass(clazz);
        if (clazz.equals(RealmBoolean.class)) {
            return (RealmModel) clazz.cast(RealmBooleanRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Assessment.class)) {
            return (RealmModel) clazz.cast(AssessmentRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Tag.class)) {
            return (RealmModel) clazz.cast(TagRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(ActivityItem.class)) {
            return (RealmModel) clazz.cast(ActivityItemRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(TodoIntervention.class)) {
            return (RealmModel) clazz.cast(TodoInterventionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Medicine.class)) {
            return (RealmModel) clazz.cast(MedicineRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Diagnosis.class)) {
            return (RealmModel) clazz.cast(DiagnosisRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Course.class)) {
            return (RealmModel) clazz.cast(CourseRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Lunch.class)) {
            return (RealmModel) clazz.cast(LunchRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Schedule.class)) {
            return (RealmModel) clazz.cast(ScheduleRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(DietMealPlan.class)) {
            return (RealmModel) clazz.cast(DietMealPlanRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Note.class)) {
            return (RealmModel) clazz.cast(NoteRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(VitalIntervention.class)) {
            return (RealmModel) clazz.cast(VitalInterventionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Guardian.class)) {
            return (RealmModel) clazz.cast(GuardianRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(MedicalContact.class)) {
            return (RealmModel) clazz.cast(MedicalContactRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(CarePlanReminder.class)) {
            return (RealmModel) clazz.cast(CarePlanReminderRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Limit.class)) {
            return (RealmModel) clazz.cast(LimitRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Careplan.class)) {
            return (RealmModel) clazz.cast(CareplanRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Message.class)) {
            return (RealmModel) clazz.cast(MessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Action.class)) {
            return (RealmModel) clazz.cast(ActionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Days.class)) {
            return (RealmModel) clazz.cast(DaysRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Activity.class)) {
            return (RealmModel) clazz.cast(ActivityRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Dinner.class)) {
            return (RealmModel) clazz.cast(DinnerRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(WaistSize.class)) {
            return (RealmModel) clazz.cast(WaistSizeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(RealmString.class)) {
            return (RealmModel) clazz.cast(RealmStringRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Reminder.class)) {
            return (RealmModel) clazz.cast(ReminderRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(DietTemplate.class)) {
            return (RealmModel) clazz.cast(DietTemplateRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(MealTemplate.class)) {
            return (RealmModel) clazz.cast(MealTemplateRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Monday.class)) {
            return (RealmModel) clazz.cast(MondayRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Tuesday.class)) {
            return (RealmModel) clazz.cast(TuesdayRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(User.class)) {
            return (RealmModel) clazz.cast(UserRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Timings.class)) {
            return (RealmModel) clazz.cast(TimingsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Todo.class)) {
            return (RealmModel) clazz.cast(TodoRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Height.class)) {
            return (RealmModel) clazz.cast(HeightRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(CareplanSummary.class)) {
            return (RealmModel) clazz.cast(CareplanSummaryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Subjective.class)) {
            return (RealmModel) clazz.cast(SubjectiveRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Symoptoms.class)) {
            return (RealmModel) clazz.cast(SymoptomsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(ActivityFeature.class)) {
            return (RealmModel) clazz.cast(ActivityFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(DietIntervention.class)) {
            return (RealmModel) clazz.cast(DietInterventionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Limits.class)) {
            return (RealmModel) clazz.cast(LimitsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(MealPlan.class)) {
            return (RealmModel) clazz.cast(MealPlanRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Objective.class)) {
            return (RealmModel) clazz.cast(ObjectiveRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Template.class)) {
            return (RealmModel) clazz.cast(TemplateRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(UserFeature.class)) {
            return (RealmModel) clazz.cast(UserFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(ContentFeature.class)) {
            return (RealmModel) clazz.cast(ContentFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(HealthCondition.class)) {
            return (RealmModel) clazz.cast(HealthConditionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Allergy.class)) {
            return (RealmModel) clazz.cast(AllergyRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(CommonFeature.class)) {
            return (RealmModel) clazz.cast(CommonFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Address.class)) {
            return (RealmModel) clazz.cast(AddressRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(DeviceAlert.class)) {
            return (RealmModel) clazz.cast(DeviceAlertRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(VitalFeature.class)) {
            return (RealmModel) clazz.cast(VitalFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(HipSize.class)) {
            return (RealmModel) clazz.cast(HipSizeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Vital.class)) {
            return (RealmModel) clazz.cast(VitalRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(ActivityIntervention.class)) {
            return (RealmModel) clazz.cast(ActivityInterventionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Alert.class)) {
            return (RealmModel) clazz.cast(AlertRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(UrlIdentifier.class)) {
            return (RealmModel) clazz.cast(UrlIdentifierRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Intervention.class)) {
            return (RealmModel) clazz.cast(InterventionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Saturday.class)) {
            return (RealmModel) clazz.cast(SaturdayRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(DietFeature.class)) {
            return (RealmModel) clazz.cast(DietFeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(FeedBackInput.class)) {
            return (RealmModel) clazz.cast(FeedBackInputRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Goal.class)) {
            return (RealmModel) clazz.cast(GoalRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Instructions.class)) {
            return (RealmModel) clazz.cast(InstructionsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Wednesday.class)) {
            return (RealmModel) clazz.cast(WednesdayRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Friday.class)) {
            return (RealmModel) clazz.cast(FridayRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(UserSettings.class)) {
            return (RealmModel) clazz.cast(UserSettingsRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(ContentIntervention.class)) {
            return (RealmModel) clazz.cast(ContentInterventionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(BreakFast.class)) {
            return (RealmModel) clazz.cast(BreakFastRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Thursday.class)) {
            return (RealmModel) clazz.cast(ThursdayRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(Feature.class)) {
            return (RealmModel) clazz.cast(FeatureRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader) throws IOException {
        checkClass(clazz);
        if (clazz.equals(RealmBoolean.class)) {
            return (RealmModel) clazz.cast(RealmBooleanRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Assessment.class)) {
            return (RealmModel) clazz.cast(AssessmentRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Tag.class)) {
            return (RealmModel) clazz.cast(TagRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(ActivityItem.class)) {
            return (RealmModel) clazz.cast(ActivityItemRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(TodoIntervention.class)) {
            return (RealmModel) clazz.cast(TodoInterventionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Medicine.class)) {
            return (RealmModel) clazz.cast(MedicineRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Diagnosis.class)) {
            return (RealmModel) clazz.cast(DiagnosisRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Course.class)) {
            return (RealmModel) clazz.cast(CourseRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Lunch.class)) {
            return (RealmModel) clazz.cast(LunchRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Schedule.class)) {
            return (RealmModel) clazz.cast(ScheduleRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(DietMealPlan.class)) {
            return (RealmModel) clazz.cast(DietMealPlanRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Note.class)) {
            return (RealmModel) clazz.cast(NoteRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(VitalIntervention.class)) {
            return (RealmModel) clazz.cast(VitalInterventionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Guardian.class)) {
            return (RealmModel) clazz.cast(GuardianRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(MedicalContact.class)) {
            return (RealmModel) clazz.cast(MedicalContactRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(CarePlanReminder.class)) {
            return (RealmModel) clazz.cast(CarePlanReminderRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Limit.class)) {
            return (RealmModel) clazz.cast(LimitRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Careplan.class)) {
            return (RealmModel) clazz.cast(CareplanRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Message.class)) {
            return (RealmModel) clazz.cast(MessageRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Action.class)) {
            return (RealmModel) clazz.cast(ActionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Days.class)) {
            return (RealmModel) clazz.cast(DaysRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Activity.class)) {
            return (RealmModel) clazz.cast(ActivityRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Dinner.class)) {
            return (RealmModel) clazz.cast(DinnerRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(WaistSize.class)) {
            return (RealmModel) clazz.cast(WaistSizeRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(RealmString.class)) {
            return (RealmModel) clazz.cast(RealmStringRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Reminder.class)) {
            return (RealmModel) clazz.cast(ReminderRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(DietTemplate.class)) {
            return (RealmModel) clazz.cast(DietTemplateRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(MealTemplate.class)) {
            return (RealmModel) clazz.cast(MealTemplateRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Monday.class)) {
            return (RealmModel) clazz.cast(MondayRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Tuesday.class)) {
            return (RealmModel) clazz.cast(TuesdayRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(User.class)) {
            return (RealmModel) clazz.cast(UserRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Timings.class)) {
            return (RealmModel) clazz.cast(TimingsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Todo.class)) {
            return (RealmModel) clazz.cast(TodoRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Height.class)) {
            return (RealmModel) clazz.cast(HeightRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(CareplanSummary.class)) {
            return (RealmModel) clazz.cast(CareplanSummaryRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Subjective.class)) {
            return (RealmModel) clazz.cast(SubjectiveRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Symoptoms.class)) {
            return (RealmModel) clazz.cast(SymoptomsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(ActivityFeature.class)) {
            return (RealmModel) clazz.cast(ActivityFeatureRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(DietIntervention.class)) {
            return (RealmModel) clazz.cast(DietInterventionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Limits.class)) {
            return (RealmModel) clazz.cast(LimitsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(MealPlan.class)) {
            return (RealmModel) clazz.cast(MealPlanRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Objective.class)) {
            return (RealmModel) clazz.cast(ObjectiveRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Template.class)) {
            return (RealmModel) clazz.cast(TemplateRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(UserFeature.class)) {
            return (RealmModel) clazz.cast(UserFeatureRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(ContentFeature.class)) {
            return (RealmModel) clazz.cast(ContentFeatureRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(HealthCondition.class)) {
            return (RealmModel) clazz.cast(HealthConditionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Allergy.class)) {
            return (RealmModel) clazz.cast(AllergyRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(CommonFeature.class)) {
            return (RealmModel) clazz.cast(CommonFeatureRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Address.class)) {
            return (RealmModel) clazz.cast(AddressRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(DeviceAlert.class)) {
            return (RealmModel) clazz.cast(DeviceAlertRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(VitalFeature.class)) {
            return (RealmModel) clazz.cast(VitalFeatureRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(HipSize.class)) {
            return (RealmModel) clazz.cast(HipSizeRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Vital.class)) {
            return (RealmModel) clazz.cast(VitalRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(ActivityIntervention.class)) {
            return (RealmModel) clazz.cast(ActivityInterventionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Alert.class)) {
            return (RealmModel) clazz.cast(AlertRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(UrlIdentifier.class)) {
            return (RealmModel) clazz.cast(UrlIdentifierRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Intervention.class)) {
            return (RealmModel) clazz.cast(InterventionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Saturday.class)) {
            return (RealmModel) clazz.cast(SaturdayRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(DietFeature.class)) {
            return (RealmModel) clazz.cast(DietFeatureRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(FeedBackInput.class)) {
            return (RealmModel) clazz.cast(FeedBackInputRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Goal.class)) {
            return (RealmModel) clazz.cast(GoalRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Instructions.class)) {
            return (RealmModel) clazz.cast(InstructionsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Wednesday.class)) {
            return (RealmModel) clazz.cast(WednesdayRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Friday.class)) {
            return (RealmModel) clazz.cast(FridayRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(UserSettings.class)) {
            return (RealmModel) clazz.cast(UserSettingsRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(ContentIntervention.class)) {
            return (RealmModel) clazz.cast(ContentInterventionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(BreakFast.class)) {
            return (RealmModel) clazz.cast(BreakFastRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Thursday.class)) {
            return (RealmModel) clazz.cast(ThursdayRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(Feature.class)) {
            return (RealmModel) clazz.cast(FeatureRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        Class<E> clazz = realmObject.getClass().getSuperclass();
        if (clazz.equals(RealmBoolean.class)) {
            return (RealmModel) clazz.cast(RealmBooleanRealmProxy.createDetachedCopy((RealmBoolean) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Assessment.class)) {
            return (RealmModel) clazz.cast(AssessmentRealmProxy.createDetachedCopy((Assessment) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Tag.class)) {
            return (RealmModel) clazz.cast(TagRealmProxy.createDetachedCopy((Tag) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(ActivityItem.class)) {
            return (RealmModel) clazz.cast(ActivityItemRealmProxy.createDetachedCopy((ActivityItem) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(TodoIntervention.class)) {
            return (RealmModel) clazz.cast(TodoInterventionRealmProxy.createDetachedCopy((TodoIntervention) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Medicine.class)) {
            return (RealmModel) clazz.cast(MedicineRealmProxy.createDetachedCopy((Medicine) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Diagnosis.class)) {
            return (RealmModel) clazz.cast(DiagnosisRealmProxy.createDetachedCopy((Diagnosis) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Course.class)) {
            return (RealmModel) clazz.cast(CourseRealmProxy.createDetachedCopy((Course) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Lunch.class)) {
            return (RealmModel) clazz.cast(LunchRealmProxy.createDetachedCopy((Lunch) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Schedule.class)) {
            return (RealmModel) clazz.cast(ScheduleRealmProxy.createDetachedCopy((Schedule) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(DietMealPlan.class)) {
            return (RealmModel) clazz.cast(DietMealPlanRealmProxy.createDetachedCopy((DietMealPlan) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Note.class)) {
            return (RealmModel) clazz.cast(NoteRealmProxy.createDetachedCopy((Note) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(VitalIntervention.class)) {
            return (RealmModel) clazz.cast(VitalInterventionRealmProxy.createDetachedCopy((VitalIntervention) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Guardian.class)) {
            return (RealmModel) clazz.cast(GuardianRealmProxy.createDetachedCopy((Guardian) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(MedicalContact.class)) {
            return (RealmModel) clazz.cast(MedicalContactRealmProxy.createDetachedCopy((MedicalContact) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(CarePlanReminder.class)) {
            return (RealmModel) clazz.cast(CarePlanReminderRealmProxy.createDetachedCopy((CarePlanReminder) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Limit.class)) {
            return (RealmModel) clazz.cast(LimitRealmProxy.createDetachedCopy((Limit) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Careplan.class)) {
            return (RealmModel) clazz.cast(CareplanRealmProxy.createDetachedCopy((Careplan) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Message.class)) {
            return (RealmModel) clazz.cast(MessageRealmProxy.createDetachedCopy((Message) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Action.class)) {
            return (RealmModel) clazz.cast(ActionRealmProxy.createDetachedCopy((Action) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Days.class)) {
            return (RealmModel) clazz.cast(DaysRealmProxy.createDetachedCopy((Days) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Activity.class)) {
            return (RealmModel) clazz.cast(ActivityRealmProxy.createDetachedCopy((Activity) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Dinner.class)) {
            return (RealmModel) clazz.cast(DinnerRealmProxy.createDetachedCopy((Dinner) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(WaistSize.class)) {
            return (RealmModel) clazz.cast(WaistSizeRealmProxy.createDetachedCopy((WaistSize) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(RealmString.class)) {
            return (RealmModel) clazz.cast(RealmStringRealmProxy.createDetachedCopy((RealmString) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Reminder.class)) {
            return (RealmModel) clazz.cast(ReminderRealmProxy.createDetachedCopy((Reminder) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(DietTemplate.class)) {
            return (RealmModel) clazz.cast(DietTemplateRealmProxy.createDetachedCopy((DietTemplate) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(MealTemplate.class)) {
            return (RealmModel) clazz.cast(MealTemplateRealmProxy.createDetachedCopy((MealTemplate) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Monday.class)) {
            return (RealmModel) clazz.cast(MondayRealmProxy.createDetachedCopy((Monday) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Tuesday.class)) {
            return (RealmModel) clazz.cast(TuesdayRealmProxy.createDetachedCopy((Tuesday) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(User.class)) {
            return (RealmModel) clazz.cast(UserRealmProxy.createDetachedCopy((User) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Timings.class)) {
            return (RealmModel) clazz.cast(TimingsRealmProxy.createDetachedCopy((Timings) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Todo.class)) {
            return (RealmModel) clazz.cast(TodoRealmProxy.createDetachedCopy((Todo) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Height.class)) {
            return (RealmModel) clazz.cast(HeightRealmProxy.createDetachedCopy((Height) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(CareplanSummary.class)) {
            return (RealmModel) clazz.cast(CareplanSummaryRealmProxy.createDetachedCopy((CareplanSummary) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Subjective.class)) {
            return (RealmModel) clazz.cast(SubjectiveRealmProxy.createDetachedCopy((Subjective) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Symoptoms.class)) {
            return (RealmModel) clazz.cast(SymoptomsRealmProxy.createDetachedCopy((Symoptoms) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(ActivityFeature.class)) {
            return (RealmModel) clazz.cast(ActivityFeatureRealmProxy.createDetachedCopy((ActivityFeature) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(DietIntervention.class)) {
            return (RealmModel) clazz.cast(DietInterventionRealmProxy.createDetachedCopy((DietIntervention) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Limits.class)) {
            return (RealmModel) clazz.cast(LimitsRealmProxy.createDetachedCopy((Limits) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(MealPlan.class)) {
            return (RealmModel) clazz.cast(MealPlanRealmProxy.createDetachedCopy((MealPlan) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Objective.class)) {
            return (RealmModel) clazz.cast(ObjectiveRealmProxy.createDetachedCopy((Objective) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Template.class)) {
            return (RealmModel) clazz.cast(TemplateRealmProxy.createDetachedCopy((Template) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(UserFeature.class)) {
            return (RealmModel) clazz.cast(UserFeatureRealmProxy.createDetachedCopy((UserFeature) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(ContentFeature.class)) {
            return (RealmModel) clazz.cast(ContentFeatureRealmProxy.createDetachedCopy((ContentFeature) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(HealthCondition.class)) {
            return (RealmModel) clazz.cast(HealthConditionRealmProxy.createDetachedCopy((HealthCondition) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Allergy.class)) {
            return (RealmModel) clazz.cast(AllergyRealmProxy.createDetachedCopy((Allergy) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(CommonFeature.class)) {
            return (RealmModel) clazz.cast(CommonFeatureRealmProxy.createDetachedCopy((CommonFeature) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Address.class)) {
            return (RealmModel) clazz.cast(AddressRealmProxy.createDetachedCopy((Address) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(DeviceAlert.class)) {
            return (RealmModel) clazz.cast(DeviceAlertRealmProxy.createDetachedCopy((DeviceAlert) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(VitalFeature.class)) {
            return (RealmModel) clazz.cast(VitalFeatureRealmProxy.createDetachedCopy((VitalFeature) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(HipSize.class)) {
            return (RealmModel) clazz.cast(HipSizeRealmProxy.createDetachedCopy((HipSize) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Vital.class)) {
            return (RealmModel) clazz.cast(VitalRealmProxy.createDetachedCopy((Vital) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(ActivityIntervention.class)) {
            return (RealmModel) clazz.cast(ActivityInterventionRealmProxy.createDetachedCopy((ActivityIntervention) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Alert.class)) {
            return (RealmModel) clazz.cast(AlertRealmProxy.createDetachedCopy((Alert) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(UrlIdentifier.class)) {
            return (RealmModel) clazz.cast(UrlIdentifierRealmProxy.createDetachedCopy((UrlIdentifier) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Intervention.class)) {
            return (RealmModel) clazz.cast(InterventionRealmProxy.createDetachedCopy((Intervention) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Saturday.class)) {
            return (RealmModel) clazz.cast(SaturdayRealmProxy.createDetachedCopy((Saturday) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(DietFeature.class)) {
            return (RealmModel) clazz.cast(DietFeatureRealmProxy.createDetachedCopy((DietFeature) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(FeedBackInput.class)) {
            return (RealmModel) clazz.cast(FeedBackInputRealmProxy.createDetachedCopy((FeedBackInput) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Goal.class)) {
            return (RealmModel) clazz.cast(GoalRealmProxy.createDetachedCopy((Goal) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Instructions.class)) {
            return (RealmModel) clazz.cast(InstructionsRealmProxy.createDetachedCopy((Instructions) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Wednesday.class)) {
            return (RealmModel) clazz.cast(WednesdayRealmProxy.createDetachedCopy((Wednesday) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Friday.class)) {
            return (RealmModel) clazz.cast(FridayRealmProxy.createDetachedCopy((Friday) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(UserSettings.class)) {
            return (RealmModel) clazz.cast(UserSettingsRealmProxy.createDetachedCopy((UserSettings) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(ContentIntervention.class)) {
            return (RealmModel) clazz.cast(ContentInterventionRealmProxy.createDetachedCopy((ContentIntervention) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(BreakFast.class)) {
            return (RealmModel) clazz.cast(BreakFastRealmProxy.createDetachedCopy((BreakFast) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Thursday.class)) {
            return (RealmModel) clazz.cast(ThursdayRealmProxy.createDetachedCopy((Thursday) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(Feature.class)) {
            return (RealmModel) clazz.cast(FeatureRealmProxy.createDetachedCopy((Feature) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }
}
