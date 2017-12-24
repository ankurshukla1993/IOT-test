package com.ihealth.communication.cloud.data;

import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.PublicMethod;
import humanize.util.Constants;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Make_Data_Util {
    public static Data_AM_Activity makeDataSingleAMA(String userName, String mac, String type, ActivityData result, int calorie, int steps, int stepLen) {
        long b = C2051l.m390b();
        Data_AM_Activity data_AM_Activity = new Data_AM_Activity();
        data_AM_Activity.setChangeType(1);
        data_AM_Activity.setLastChangeTime(b);
        data_AM_Activity.setPhoneDataID(PublicMethod.getAMDataID(mac, result.getSteps() + "", PublicMethod.String2TS(result.getTime())));
        data_AM_Activity.setPhoneCreateTime(b);
        data_AM_Activity.setLat(0.0d);
        data_AM_Activity.setLon(0.0d);
        data_AM_Activity.setTimeZone(C2051l.m383a());
        data_AM_Activity.setStepLength(stepLen);
        data_AM_Activity.setCalorie((float) calorie);
        data_AM_Activity.setSteps(steps);
        data_AM_Activity.setSumCalorie(result.getCalorie());
        data_AM_Activity.setSumSteps(result.getSteps());
        data_AM_Activity.setMeasureTime(C2051l.m391b(result.getTime()));
        data_AM_Activity.setMechineType(type);
        data_AM_Activity.setMechineDeviceID(mac);
        data_AM_Activity.setiHealthID(userName);
        return data_AM_Activity;
    }

    public static Data_AM_ActivityDayReport makeDataSingleAMADR(String userName, String mac, String type, int calorie, int steps, int stepLen, long TS) {
        long b = C2051l.m390b();
        Data_AM_ActivityDayReport data_AM_ActivityDayReport = new Data_AM_ActivityDayReport();
        data_AM_ActivityDayReport.setChangeType(1);
        data_AM_ActivityDayReport.setLastChangeTime(b);
        data_AM_ActivityDayReport.setPhoneDataID(b + mac + steps);
        data_AM_ActivityDayReport.setPhoneCreateTime(b);
        data_AM_ActivityDayReport.setLat(0.0d);
        data_AM_ActivityDayReport.setLon(0.0d);
        data_AM_ActivityDayReport.setTimeZone(C2051l.m383a());
        data_AM_ActivityDayReport.setStepLength(stepLen);
        data_AM_ActivityDayReport.setCalorie((float) calorie);
        data_AM_ActivityDayReport.setSteps(steps);
        data_AM_ActivityDayReport.setPlanSteps(AbstractSpiCall.DEFAULT_TIMEOUT);
        data_AM_ActivityDayReport.setPlanCalorie(50.0f);
        data_AM_ActivityDayReport.setCity("");
        data_AM_ActivityDayReport.setWeather("");
        data_AM_ActivityDayReport.setComment("");
        data_AM_ActivityDayReport.setMeasureTime(TS);
        data_AM_ActivityDayReport.setMechineType(type);
        data_AM_ActivityDayReport.setMechineDeviceID(mac);
        data_AM_ActivityDayReport.setiHealthID(userName);
        return data_AM_ActivityDayReport;
    }

    public static Data_AM_Sleep makeDataSingleAMS(String userName, String mac, String type, int sleepLevel, String timeSectionId, String measureTime) {
        long b = C2051l.m390b();
        Data_AM_Sleep data_AM_Sleep = new Data_AM_Sleep();
        data_AM_Sleep.setChangeType(1);
        data_AM_Sleep.setLastChangeTime(b);
        data_AM_Sleep.setPhoneDataID(PublicMethod.getAMDataID(mac, sleepLevel + "", PublicMethod.String2TS(measureTime)));
        data_AM_Sleep.setPhoneCreateTime(b);
        data_AM_Sleep.setLat(0.0d);
        data_AM_Sleep.setLon(0.0d);
        data_AM_Sleep.setTimeZone(C2051l.m383a());
        data_AM_Sleep.setSleepLevel(sleepLevel);
        data_AM_Sleep.setTimeSectionId(timeSectionId);
        data_AM_Sleep.setMeasureTime(C2051l.m391b(measureTime));
        data_AM_Sleep.setMechineType(type);
        data_AM_Sleep.setMechineDeviceID(mac);
        data_AM_Sleep.setiHealthID(userName);
        return data_AM_Sleep;
    }

    public static Data_AM_SleepSectionReport makeDataSingleAMSSR(String userName, String mac, String type, int awake, int deepSleep, int fallSleep, int sleep, int awakenTime, String startTime, String endTime, String timeSectionId) {
        long b = C2051l.m390b();
        Data_AM_SleepSectionReport data_AM_SleepSectionReport = new Data_AM_SleepSectionReport();
        data_AM_SleepSectionReport.setChangeType(1);
        data_AM_SleepSectionReport.setLastChangeTime(b);
        data_AM_SleepSectionReport.setPhoneDataID(b + mac + sleep + "");
        data_AM_SleepSectionReport.setPhoneCreateTime(b);
        data_AM_SleepSectionReport.setLat(0.0d);
        data_AM_SleepSectionReport.setLon(0.0d);
        data_AM_SleepSectionReport.setTimeZone(C2051l.m383a());
        data_AM_SleepSectionReport.setAwake(awake);
        data_AM_SleepSectionReport.setDeepSleep(deepSleep);
        data_AM_SleepSectionReport.setFallSleep(fallSleep);
        data_AM_SleepSectionReport.setSleep(sleep);
        data_AM_SleepSectionReport.setAwakenTimes(awakenTime);
        data_AM_SleepSectionReport.setSleepStartTime(C2051l.m391b(startTime));
        data_AM_SleepSectionReport.setSleepEndTime(C2051l.m391b(endTime));
        data_AM_SleepSectionReport.setTimeSectionId(timeSectionId);
        data_AM_SleepSectionReport.setCity("");
        data_AM_SleepSectionReport.setWeather("");
        data_AM_SleepSectionReport.setComment("");
        data_AM_SleepSectionReport.setNap(0);
        data_AM_SleepSectionReport.setMood(1);
        data_AM_SleepSectionReport.setActivity(2);
        data_AM_SleepSectionReport.setMeasureTime(C2051l.m391b(startTime));
        data_AM_SleepSectionReport.setMechineType(type);
        data_AM_SleepSectionReport.setMechineDeviceID(mac);
        data_AM_SleepSectionReport.setiHealthID(userName);
        return data_AM_SleepSectionReport;
    }

    public static Data_TB_Swim makeDataSingleAMSwim(String userName, String dataID, String mac, String type, float calories, int pullTimes, int cycles, int storke, int distance, long endTime, int spendTimes, int cutIn, int cutOut, int flag, long startTime) {
        long b = C2051l.m390b();
        Data_TB_Swim data_TB_Swim = new Data_TB_Swim();
        data_TB_Swim.setSwim_ChangeType(1);
        data_TB_Swim.setSwim_PhoneDataID(dataID);
        data_TB_Swim.setSwim_LastChangeTime(b);
        data_TB_Swim.setSwim_PhoneCreateTime(b);
        data_TB_Swim.setSwim_Lat(0.0d);
        data_TB_Swim.setSwim_Lon(0.0d);
        data_TB_Swim.setSwim_TimeZone(C2051l.m383a());
        data_TB_Swim.setSwim_Cycles(cycles);
        data_TB_Swim.setSwim_SpendMinutes(spendTimes);
        data_TB_Swim.setSwim_Calories(calories);
        data_TB_Swim.setSwim_Distance(distance);
        data_TB_Swim.setSwim_Storke(storke);
        data_TB_Swim.setSwim_PullTimes(pullTimes);
        data_TB_Swim.setSwim_endtime(endTime);
        data_TB_Swim.setSwim_CutInTimeDif(cutIn);
        data_TB_Swim.setSwim_CutOutTimeDif(cutOut);
        data_TB_Swim.setSwim_ProcessFlag(flag);
        data_TB_Swim.setSwim_MechineType(type);
        data_TB_Swim.setSwim_MechineDeviceID(mac);
        data_TB_Swim.setSwim_iHealthCloud(userName);
        data_TB_Swim.setSwim_StartTimeStamp(Long.valueOf(startTime));
        return data_TB_Swim;
    }

    public static Data_TB_SwimSection makeDataSingleAMSwimSection(String userName, String dataID, String mac, String type, Data_TB_SwimSection swimSection) {
        long b = C2051l.m390b();
        swimSection.setSwimSection_DeviceID(mac);
        swimSection.setSwimSection_DataID(dataID);
        swimSection.setSwimSection_DeviceSource(type);
        swimSection.setSwimSection_iHealthCloud(userName);
        swimSection.setSwimSection_LastChangeTime(b);
        swimSection.setSwimSection_Lat(0.0d);
        swimSection.setSwimSection_Lon(0.0d);
        swimSection.setSwimSection_NoteTS(b);
        swimSection.setSwimSection_TimeZone(C2051l.m383a());
        return swimSection;
    }

    public static Data_TB_Workout makeDataSingleAMWorkOut(String dataID, int workout_SpendMinutes, int workout_Steps, int workout_Distance, float workout_Calories, String workout_MechineType, String workout_MechineDeviceID, String workout_iHealthCloud) {
        long b = C2051l.m390b();
        Data_TB_Workout data_TB_Workout = new Data_TB_Workout();
        data_TB_Workout.setWorkout_ChangeType(1);
        data_TB_Workout.setWorkout_LastChangeTime(b);
        data_TB_Workout.setWorkout_PhoneDataID(dataID);
        data_TB_Workout.setWorkout_PhoneCreateTime(b);
        data_TB_Workout.setWorkout_Lat(0.0d);
        data_TB_Workout.setWorkout_Lon(0.0d);
        data_TB_Workout.setWorkout_TimeZone(C2051l.m383a());
        data_TB_Workout.setWorkout_SpendMinutes(workout_SpendMinutes);
        data_TB_Workout.setWorkout_Steps(workout_Steps);
        data_TB_Workout.setWorkout_Distance(workout_Distance);
        data_TB_Workout.setWorkout_Calories(workout_Calories);
        data_TB_Workout.setWorkout_MechineType(workout_MechineType);
        data_TB_Workout.setWorkout_MechineDeviceID(workout_MechineDeviceID);
        data_TB_Workout.setWorkout_iHealthCloud(workout_iHealthCloud);
        return data_TB_Workout;
    }

    public static Data_BG_Result makeDataSingleBg(String userName, int result, String type, String deviceId, long bottleId) {
        long b = C2051l.m390b();
        int parseInt = Integer.parseInt(new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1).format(new Date(System.currentTimeMillis())).split(Constants.SPACE)[1].split(":")[0]);
        Data_BG_Result data_BG_Result = new Data_BG_Result();
        data_BG_Result.setChangeType(1);
        data_BG_Result.setLastChangeTime(b);
        data_BG_Result.setPhoneDataID(C2051l.m387a(deviceId, b, result));
        data_BG_Result.setPhoneCreateTime(b);
        data_BG_Result.setLat(0.0d);
        data_BG_Result.setLon(0.0d);
        data_BG_Result.setTimeZone(C2051l.m383a());
        data_BG_Result.setBGValue((float) result);
        data_BG_Result.setMedication(0);
        if (((parseInt < 7 ? 1 : 0) & (parseInt >= 4 ? 1 : 0)) != 0) {
            data_BG_Result.setMTimeType(1);
        } else {
            if (((parseInt < 10 ? 1 : 0) & (parseInt >= 7 ? 1 : 0)) != 0) {
                data_BG_Result.setMTimeType(1);
            } else {
                if (((parseInt < 13 ? 1 : 0) & (parseInt >= 10 ? 1 : 0)) != 0) {
                    data_BG_Result.setMTimeType(3);
                } else {
                    if (((parseInt < 16 ? 1 : 0) & (parseInt >= 13 ? 1 : 0)) != 0) {
                        data_BG_Result.setMTimeType(3);
                    } else {
                        if (((parseInt < 19 ? 1 : 0) & (parseInt >= 16 ? 1 : 0)) != 0) {
                            data_BG_Result.setMTimeType(5);
                        } else {
                            if (((parseInt < 22 ? 1 : 0) & (parseInt >= 19 ? 1 : 0)) != 0) {
                                data_BG_Result.setMTimeType(5);
                            } else {
                                data_BG_Result.setMTimeType(7);
                            }
                        }
                    }
                }
            }
        }
        data_BG_Result.setMeasureType(0);
        data_BG_Result.setSnacks(1);
        data_BG_Result.setSports(1);
        data_BG_Result.setMeasureTime(System.currentTimeMillis() / 1000);
        data_BG_Result.setNote("");
        data_BG_Result.setMechineType(type);
        data_BG_Result.setMechineDeviceID(deviceId);
        data_BG_Result.setEffective(1);
        data_BG_Result.setBottleId(bottleId + "");
        data_BG_Result.setiHealthID(userName);
        return data_BG_Result;
    }

    public static Data_BP_Result makeDataSingleBp(String userName, int Hp, int Lp, int HR, String type, String mac, long TS) {
        long b = C2051l.m390b();
        Data_BP_Result data_BP_Result = new Data_BP_Result();
        data_BP_Result.setChangeType(1);
        data_BP_Result.setLastChangeTime(b);
        data_BP_Result.setPhoneCreateTime(b);
        data_BP_Result.setPhoneDataID(PublicMethod.getBPDataID(mac, HR + "", TS));
        data_BP_Result.setLat(0.0d);
        data_BP_Result.setLon(0.0d);
        data_BP_Result.setTimeZone(C2051l.m383a());
        data_BP_Result.setBPL(1);
        data_BP_Result.setHP(Hp);
        data_BP_Result.setHR(HR);
        data_BP_Result.setLP(Lp);
        data_BP_Result.setIsArr(0);
        data_BP_Result.setMeasureType(0);
        data_BP_Result.setMeasureTime(b);
        data_BP_Result.setNote("");
        data_BP_Result.setNoteTS(b);
        data_BP_Result.setMechineType(type);
        data_BP_Result.setMechineDeviceID(mac);
        data_BP_Result.setWL("");
        data_BP_Result.setiHealthCloud(userName);
        data_BP_Result.setBpMood(1);
        data_BP_Result.setBpActivity(1);
        data_BP_Result.setTemp("");
        data_BP_Result.setWeather("");
        data_BP_Result.setHumidity("");
        data_BP_Result.setVisibility("");
        data_BP_Result.setUsedUserid(0);
        return data_BP_Result;
    }

    public static Data_HS_Result makeDataSingleHs(String dataId, String userName, float weightR, float fat, float water, float muscle, float skeleton, int vFatLevel, float DCI, String type, String mac) {
        long b = C2051l.m390b();
        Data_HS_Result data_HS_Result = new Data_HS_Result();
        data_HS_Result.setChangeType(1);
        data_HS_Result.setLastChangeTime(b);
        data_HS_Result.setPhoneDataID(dataId);
        data_HS_Result.setPhoneCreateTime(b);
        data_HS_Result.setLat(0.0d);
        data_HS_Result.setLon(0.0d);
        data_HS_Result.setTimeZone(C2051l.m383a());
        data_HS_Result.setBMI(0.0f);
        data_HS_Result.setBoneValue(skeleton);
        data_HS_Result.setDCI(DCI);
        data_HS_Result.setFatValue(fat);
        data_HS_Result.setWaterValue(water);
        data_HS_Result.setMuscaleValue(muscle);
        data_HS_Result.setMeasureType(1);
        data_HS_Result.setWeightValue(weightR);
        data_HS_Result.setMeasureTime(b);
        data_HS_Result.setNote("");
        data_HS_Result.setVisceraFatLevel(vFatLevel);
        data_HS_Result.setMechineType(type);
        data_HS_Result.setMechineDeviceID(mac);
        data_HS_Result.setiHealthID(userName);
        data_HS_Result.setEmotions(1);
        data_HS_Result.setNoteTS(b);
        data_HS_Result.setMood(1);
        data_HS_Result.setActivity(1);
        data_HS_Result.setTemp("");
        data_HS_Result.setWeather("");
        data_HS_Result.setHumidity("");
        data_HS_Result.setVisibility("");
        data_HS_Result.setUsedUserid(0);
        return data_HS_Result;
    }

    public static Data_PO_Result makeDataSinglePo(String dataId, String userName, int PR, int Result, int Flowrate, String mac, String wave) {
        long b = C2051l.m390b();
        Data_PO_Result data_PO_Result = new Data_PO_Result();
        data_PO_Result.setChangeType(1);
        data_PO_Result.setLastChangeTime(b);
        data_PO_Result.setPhoneDataID(dataId);
        data_PO_Result.setPhoneCreateTime(b);
        data_PO_Result.setLat(0.0d);
        data_PO_Result.setLon(0.0d);
        data_PO_Result.setTimeZone(C2051l.m383a());
        data_PO_Result.setActivity(0);
        data_PO_Result.setWave(wave);
        data_PO_Result.setPR(PR);
        data_PO_Result.setResult(Result);
        data_PO_Result.setFlowrate(Flowrate);
        data_PO_Result.setResultSource(0);
        data_PO_Result.setMood(0);
        data_PO_Result.setWeather("");
        data_PO_Result.setNote("");
        data_PO_Result.setNoteTS(b);
        data_PO_Result.setMeasureTime(b);
        data_PO_Result.setMechineType(iHealthDevicesManager.TYPE_PO3);
        data_PO_Result.setMechineDeviceID(mac);
        data_PO_Result.setiHealthID(userName);
        return data_PO_Result;
    }
}
