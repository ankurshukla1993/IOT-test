package com.facebook.internal;

import android.content.Context;
import android.os.Looper;
import com.facebook.FacebookException;
import java.lang.reflect.Method;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r14) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r0 = recentlyFetchedIdentifiers;
        if (r0 == 0) goto L_0x0017;
    L_0x0004:
        r4 = java.lang.System.currentTimeMillis();
        r0 = recentlyFetchedIdentifiers;
        r12 = r0.fetchTime;
        r4 = r4 - r12;
        r12 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));
        if (r0 >= 0) goto L_0x0017;
    L_0x0014:
        r10 = recentlyFetchedIdentifiers;
    L_0x0016:
        return r10;
    L_0x0017:
        r10 = getAndroidId(r14);
        r8 = 0;
        r0 = 3;
        r2 = new java.lang.String[r0];	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = 0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = "aid";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r2[r0] = r3;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = 1;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = "androidid";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r2[r0] = r3;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = 2;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = "limit_tracking";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r2[r0] = r3;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r1 = 0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = r14.getPackageManager();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = "com.facebook.katana.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r4 = 0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = r0.resolveContentProvider(r3, r4);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        if (r0 == 0) goto L_0x004a;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x003c:
        r0 = "content://com.facebook.katana.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r1 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x0042:
        if (r1 != 0) goto L_0x005e;
    L_0x0044:
        if (r8 == 0) goto L_0x0016;
    L_0x0046:
        r8.close();
        goto L_0x0016;
    L_0x004a:
        r0 = r14.getPackageManager();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = "com.facebook.wakizashi.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r4 = 0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = r0.resolveContentProvider(r3, r4);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        if (r0 == 0) goto L_0x0042;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x0057:
        r0 = "content://com.facebook.wakizashi.provider.AttributionIdProvider";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r1 = android.net.Uri.parse(r0);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        goto L_0x0042;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x005e:
        r0 = r14.getContentResolver();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = 0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r4 = 0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r5 = 0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r8 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        if (r8 == 0) goto L_0x0071;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x006b:
        r0 = r8.moveToFirst();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        if (r0 != 0) goto L_0x0077;
    L_0x0071:
        if (r8 == 0) goto L_0x0016;
    L_0x0073:
        r8.close();
        goto L_0x0016;
    L_0x0077:
        r0 = "aid";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r7 = r8.getColumnIndex(r0);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = "androidid";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r6 = r8.getColumnIndex(r0);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = "limit_tracking";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r11 = r8.getColumnIndex(r0);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = r8.getString(r7);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r10.attributionId = r0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        if (r6 <= 0) goto L_0x00a9;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x0091:
        if (r11 <= 0) goto L_0x00a9;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x0093:
        r0 = r10.getAndroidAdvertiserId();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        if (r0 != 0) goto L_0x00a9;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x0099:
        r0 = r8.getString(r6);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r10.androidAdvertiserId = r0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = r8.getString(r11);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r0 = java.lang.Boolean.parseBoolean(r0);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r10.limitTracking = r0;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
    L_0x00a9:
        if (r8 == 0) goto L_0x00ae;
    L_0x00ab:
        r8.close();
    L_0x00ae:
        r4 = java.lang.System.currentTimeMillis();
        r10.fetchTime = r4;
        recentlyFetchedIdentifiers = r10;
        goto L_0x0016;
    L_0x00b8:
        r9 = move-exception;
        r0 = TAG;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3.<init>();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r4 = "Caught unexpected exception in getAttributionId(): ";	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r4 = r9.toString();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        android.util.Log.d(r0, r3);	 Catch:{ Exception -> 0x00b8, all -> 0x00dd }
        r10 = 0;
        if (r8 == 0) goto L_0x0016;
    L_0x00d8:
        r8.close();
        goto L_0x0016;
    L_0x00dd:
        r0 = move-exception;
        if (r8 == 0) goto L_0x00e3;
    L_0x00e0:
        r8.close();
    L_0x00e3:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers identifiers = new AttributionIdentifiers();
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method isGooglePlayServicesAvailable = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (isGooglePlayServicesAvailable != null) {
                Object connectionResult = Utility.invokeMethodQuietly(null, isGooglePlayServicesAvailable, context);
                if ((connectionResult instanceof Integer) && ((Integer) connectionResult).intValue() == 0) {
                    Method getAdvertisingIdInfo = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
                    if (getAdvertisingIdInfo != null) {
                        Object advertisingInfo = Utility.invokeMethodQuietly(null, getAdvertisingIdInfo, context);
                        if (advertisingInfo != null) {
                            Method getId = Utility.getMethodQuietly(advertisingInfo.getClass(), "getId", new Class[0]);
                            Method isLimitAdTrackingEnabled = Utility.getMethodQuietly(advertisingInfo.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
                            if (!(getId == null || isLimitAdTrackingEnabled == null)) {
                                identifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(advertisingInfo, getId, new Object[0]);
                                identifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(advertisingInfo, isLimitAdTrackingEnabled, new Object[0])).booleanValue();
                            }
                        }
                    }
                }
            }
            return identifiers;
        } catch (Exception e) {
            Utility.logd("android_id", e);
        }
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }
}
