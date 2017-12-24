package com.facebook.share.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.facebook.FacebookCallback;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.DialogFeature;
import com.facebook.internal.DialogPresenter;
import com.facebook.internal.DialogPresenter.ParameterProvider;
import com.facebook.internal.FacebookDialogBase;
import com.facebook.share.Sharer;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.LegacyNativeDialogParameters;
import com.facebook.share.internal.NativeDialogParameters;
import com.facebook.share.internal.OpenGraphActionDialogFeature;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareDialogFeature;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.WebDialogParameters;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.util.ArrayList;
import java.util.List;

public final class ShareDialog extends FacebookDialogBase<ShareContent, Result> implements Sharer {
    private static final int DEFAULT_REQUEST_CODE = RequestCodeOffset.Share.toRequestCode();
    private static final String FEED_DIALOG = "feed";
    private static final String WEB_OG_SHARE_DIALOG = "share_open_graph";
    private static final String WEB_SHARE_DIALOG = "share";
    private boolean isAutomaticMode = true;
    private boolean shouldFailOnDataError = false;

    private class FeedHandler extends ModeHandler {
        private FeedHandler() {
            super();
        }

        public Object getMode() {
            return Mode.FEED;
        }

        public boolean canShow(ShareContent content) {
            return content instanceof ShareLinkContent;
        }

        public AppCall createAppCall(ShareContent content) {
            ShareDialog.this.logDialogShare(ShareDialog.this.getActivityContext(), content, Mode.FEED);
            ShareLinkContent linkContent = (ShareLinkContent) content;
            AppCall appCall = ShareDialog.this.createBaseAppCall();
            ShareContentValidation.validateForWebShare(linkContent);
            DialogPresenter.setupAppCallForWebDialog(appCall, ShareDialog.FEED_DIALOG, WebDialogParameters.createForFeed(linkContent));
            return appCall;
        }
    }

    public enum Mode {
        AUTOMATIC,
        NATIVE,
        WEB,
        FEED
    }

    private class NativeHandler extends ModeHandler {
        private NativeHandler() {
            super();
        }

        public Object getMode() {
            return Mode.NATIVE;
        }

        public boolean canShow(ShareContent content) {
            return content != null && ShareDialog.canShowNative(content.getClass());
        }

        public AppCall createAppCall(final ShareContent content) {
            ShareDialog.this.logDialogShare(ShareDialog.this.getActivityContext(), content, Mode.NATIVE);
            ShareContentValidation.validateForNativeShare(content);
            final AppCall appCall = ShareDialog.this.createBaseAppCall();
            final boolean shouldFailOnDataError = ShareDialog.this.getShouldFailOnDataError();
            DialogPresenter.setupAppCallForNativeDialog(appCall, new ParameterProvider() {
                public Bundle getParameters() {
                    return NativeDialogParameters.create(appCall.getCallId(), content, shouldFailOnDataError);
                }

                public Bundle getLegacyParameters() {
                    return LegacyNativeDialogParameters.create(appCall.getCallId(), content, shouldFailOnDataError);
                }
            }, ShareDialog.getFeature(content.getClass()));
            return appCall;
        }
    }

    private class WebShareHandler extends ModeHandler {
        private WebShareHandler() {
            super();
        }

        public Object getMode() {
            return Mode.WEB;
        }

        public boolean canShow(ShareContent content) {
            return content != null && ShareDialog.canShowWebTypeCheck(content.getClass());
        }

        public AppCall createAppCall(ShareContent content) {
            Bundle params;
            ShareDialog.this.logDialogShare(ShareDialog.this.getActivityContext(), content, Mode.WEB);
            AppCall appCall = ShareDialog.this.createBaseAppCall();
            ShareContentValidation.validateForWebShare(content);
            if (content instanceof ShareLinkContent) {
                params = WebDialogParameters.create((ShareLinkContent) content);
            } else {
                params = WebDialogParameters.create((ShareOpenGraphContent) content);
            }
            DialogPresenter.setupAppCallForWebDialog(appCall, getActionName(content), params);
            return appCall;
        }

        private String getActionName(ShareContent shareContent) {
            if (shareContent instanceof ShareLinkContent) {
                return "share";
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                return ShareDialog.WEB_OG_SHARE_DIALOG;
            }
            return null;
        }
    }

    public static void show(Activity activity, ShareContent shareContent) {
        new ShareDialog(activity).show(shareContent);
    }

    public static void show(Fragment fragment, ShareContent shareContent) {
        new ShareDialog(fragment).show(shareContent);
    }

    public static boolean canShow(Class<? extends ShareContent> contentType) {
        return canShowWebTypeCheck(contentType) || canShowNative(contentType);
    }

    private static boolean canShowNative(Class<? extends ShareContent> contentType) {
        DialogFeature feature = getFeature(contentType);
        return feature != null && DialogPresenter.canPresentNativeDialogWithFeature(feature);
    }

    private static boolean canShowWebTypeCheck(Class<? extends ShareContent> contentType) {
        return ShareLinkContent.class.isAssignableFrom(contentType) || ShareOpenGraphContent.class.isAssignableFrom(contentType);
    }

    public ShareDialog(Activity activity) {
        super(activity, DEFAULT_REQUEST_CODE);
        ShareInternalUtility.registerStaticShareCallback(DEFAULT_REQUEST_CODE);
    }

    public ShareDialog(Fragment fragment) {
        super(fragment, DEFAULT_REQUEST_CODE);
        ShareInternalUtility.registerStaticShareCallback(DEFAULT_REQUEST_CODE);
    }

    ShareDialog(Activity activity, int requestCode) {
        super(activity, requestCode);
        ShareInternalUtility.registerStaticShareCallback(requestCode);
    }

    ShareDialog(Fragment fragment, int requestCode) {
        super(fragment, requestCode);
        ShareInternalUtility.registerStaticShareCallback(requestCode);
    }

    protected void registerCallbackImpl(CallbackManagerImpl callbackManager, FacebookCallback<Result> callback) {
        ShareInternalUtility.registerSharerCallback(getRequestCode(), callbackManager, callback);
    }

    public boolean getShouldFailOnDataError() {
        return this.shouldFailOnDataError;
    }

    public void setShouldFailOnDataError(boolean shouldFailOnDataError) {
        this.shouldFailOnDataError = shouldFailOnDataError;
    }

    public boolean canShow(ShareContent content, Mode mode) {
        if (mode == Mode.AUTOMATIC) {
            mode = BASE_AUTOMATIC_MODE;
        }
        return canShowImpl(content, mode);
    }

    public void show(ShareContent content, Mode mode) {
        this.isAutomaticMode = mode == Mode.AUTOMATIC;
        if (this.isAutomaticMode) {
            mode = BASE_AUTOMATIC_MODE;
        }
        showImpl(content, mode);
    }

    protected AppCall createBaseAppCall() {
        return new AppCall(getRequestCode());
    }

    protected List<ModeHandler> getOrderedModeHandlers() {
        ArrayList<ModeHandler> handlers = new ArrayList();
        handlers.add(new NativeHandler());
        handlers.add(new FeedHandler());
        handlers.add(new WebShareHandler());
        return handlers;
    }

    private static DialogFeature getFeature(Class<? extends ShareContent> contentType) {
        if (ShareLinkContent.class.isAssignableFrom(contentType)) {
            return ShareDialogFeature.SHARE_DIALOG;
        }
        if (SharePhotoContent.class.isAssignableFrom(contentType)) {
            return ShareDialogFeature.PHOTOS;
        }
        if (ShareVideoContent.class.isAssignableFrom(contentType)) {
            return ShareDialogFeature.VIDEO;
        }
        if (ShareOpenGraphContent.class.isAssignableFrom(contentType)) {
            return OpenGraphActionDialogFeature.OG_ACTION_DIALOG;
        }
        return null;
    }

    private void logDialogShare(Context context, ShareContent content, Mode mode) {
        String displayType;
        String contentType;
        if (this.isAutomaticMode) {
            mode = Mode.AUTOMATIC;
        }
        switch (mode) {
            case AUTOMATIC:
                displayType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC;
                break;
            case WEB:
                displayType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB;
                break;
            case NATIVE:
                displayType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                break;
            default:
                displayType = "unknown";
                break;
        }
        DialogFeature dialogFeature = getFeature(content.getClass());
        if (dialogFeature == ShareDialogFeature.SHARE_DIALOG) {
            contentType = "status";
        } else if (dialogFeature == ShareDialogFeature.PHOTOS) {
            contentType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO;
        } else if (dialogFeature == ShareDialogFeature.VIDEO) {
            contentType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO;
        } else if (dialogFeature == OpenGraphActionDialogFeature.OG_ACTION_DIALOG) {
            contentType = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_OPENGRAPH;
        } else {
            contentType = "unknown";
        }
        AppEventsLogger logger = AppEventsLogger.newLogger(context);
        Bundle parameters = new Bundle();
        parameters.putString("fb_share_dialog_show", displayType);
        parameters.putString(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_TYPE, contentType);
        logger.logSdkEvent("fb_share_dialog_show", null, parameters);
    }
}
