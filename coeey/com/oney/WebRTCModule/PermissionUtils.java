package com.oney.WebRTCModule;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.v4.content.ContextCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import java.util.ArrayList;

public class PermissionUtils {
    private static final String GRANT_RESULTS = "GRANT_RESULT";
    private static final String PERMISSIONS = "PERMISSION";
    private static final String REQUEST_CODE = "REQUEST_CODE";
    private static final String RESULT_RECEIVER = "RESULT_RECEIVER";
    private static int requestCode;

    public interface Callback {
        void invoke(String[] strArr, int[] iArr);
    }

    public static class RequestPermissionsFragment extends Fragment {
        private void checkSelfPermissions(boolean requestPermissions) {
            Bundle args = getArguments();
            String[] permissions = args.getStringArray(PermissionUtils.PERMISSIONS);
            int size = permissions.length;
            Activity activity = getActivity();
            int[] grantResults = new int[size];
            ArrayList<String> deniedPermissions = new ArrayList();
            for (int i = 0; i < size; i++) {
                String permission = permissions[i];
                int grantResult = activity.checkSelfPermission(permission);
                grantResults[i] = grantResult;
                if (grantResult != 0) {
                    deniedPermissions.add(permission);
                }
            }
            int requestCode = args.getInt(PermissionUtils.REQUEST_CODE, 0);
            if (deniedPermissions.isEmpty() || !requestPermissions) {
                finish();
                PermissionUtils.send((ResultReceiver) args.getParcelable(PermissionUtils.RESULT_RECEIVER), requestCode, permissions, grantResults);
                return;
            }
            requestPermissions((String[]) deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
        }

        private void finish() {
            Activity activity = getActivity();
            if (activity != null) {
                activity.getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
            }
        }

        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            Bundle args = getArguments();
            if (args.getInt(PermissionUtils.REQUEST_CODE, 0) == requestCode) {
                if (permissions.length == 0 || grantResults.length == 0) {
                    Activity activity = getActivity();
                    finish();
                    PermissionUtils.requestPermissions((Context) activity, args.getStringArray(PermissionUtils.PERMISSIONS), (ResultReceiver) args.getParcelable(PermissionUtils.RESULT_RECEIVER));
                    return;
                }
                checkSelfPermissions(false);
            }
        }

        public void onResume() {
            super.onResume();
            checkSelfPermissions(true);
        }
    }

    private static Activity getActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ReactContext) {
            return ((ReactContext) context).getCurrentActivity();
        }
        return null;
    }

    private static void maybeRequestPermissionsOnHostResume(final Context context, final String[] permissions, int[] grantResults, final ResultReceiver resultReceiver, int requestCode) {
        if (context instanceof ReactContext) {
            final ReactContext reactContext = (ReactContext) context;
            reactContext.addLifecycleEventListener(new LifecycleEventListener() {
                public void onHostDestroy() {
                }

                public void onHostPause() {
                }

                public void onHostResume() {
                    reactContext.removeLifecycleEventListener(this);
                    PermissionUtils.requestPermissions(context, permissions, resultReceiver);
                }
            });
            return;
        }
        send(resultReceiver, requestCode, permissions, grantResults);
    }

    private static void requestPermissions(Context context, String[] permissions, ResultReceiver resultReceiver) {
        int size = permissions.length;
        int[] grantResults = new int[size];
        boolean permissionsGranted = true;
        for (int i = 0; i < size; i++) {
            int grantResult = ContextCompat.checkSelfPermission(context, permissions[i]);
            grantResults[i] = grantResult;
            if (grantResult != 0) {
                permissionsGranted = false;
            }
        }
        int requestCode = requestCode + 1;
        requestCode = requestCode;
        if (permissionsGranted || VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) {
            send(resultReceiver, requestCode, permissions, grantResults);
            return;
        }
        Activity activity = getActivity(context);
        if (activity == null) {
            maybeRequestPermissionsOnHostResume(context, permissions, grantResults, resultReceiver, requestCode);
            return;
        }
        Bundle args = new Bundle();
        args.putInt(REQUEST_CODE, requestCode);
        args.putParcelable(RESULT_RECEIVER, resultReceiver);
        args.putStringArray(PERMISSIONS, permissions);
        RequestPermissionsFragment fragment = new RequestPermissionsFragment();
        fragment.setArguments(args);
        try {
            activity.getFragmentManager().beginTransaction().add(fragment, fragment.getClass().getName() + "-" + requestCode).commit();
        } catch (IllegalStateException e) {
            maybeRequestPermissionsOnHostResume(context, permissions, grantResults, resultReceiver, requestCode);
        }
    }

    public static void requestPermissions(ReactContext context, String[] permissions, final Callback callback) {
        requestPermissions((Context) context, permissions, new ResultReceiver(new Handler(Looper.getMainLooper())) {
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                callback.invoke(resultData.getStringArray(PermissionUtils.PERMISSIONS), resultData.getIntArray(PermissionUtils.GRANT_RESULTS));
            }
        });
    }

    private static void send(ResultReceiver resultReceiver, int requestCode, String[] permissions, int[] grantResults) {
        Bundle resultData = new Bundle();
        resultData.putStringArray(PERMISSIONS, permissions);
        resultData.putIntArray(GRANT_RESULTS, grantResults);
        resultReceiver.send(requestCode, resultData);
    }
}
