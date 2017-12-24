package com.facebook.share.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.ParcelableResourceWithMimeType;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.CallbackManagerImpl.Callback;
import com.facebook.internal.GraphUtil;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.internal.NativeAppCallAttachmentStore.Attachment;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.Mapper;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.OpenGraphJSONUtility.PhotoJSONProcessor;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.LikeView.ObjectType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShareInternalUtility {
    private static final String CAPTION_PARAM = "caption";
    private static final String MY_ACTION_FORMAT = "me/%s";
    private static final String MY_FEED = "me/feed";
    private static final String MY_OBJECTS_FORMAT = "me/objects/%s";
    private static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String OBJECT_PARAM = "object";
    private static final String PICTURE_PARAM = "picture";
    private static final String STAGING_PARAM = "file";

    static class C13865 implements Mapper<Attachment, String> {
        C13865() {
        }

        public String apply(Attachment item) {
            return item.getAttachmentUrl();
        }
    }

    static class C13887 implements PhotoJSONProcessor {
        C13887() {
        }

        public JSONObject toJSONObject(SharePhoto photo) {
            Uri photoUri = photo.getImageUrl();
            JSONObject photoJSONObject = new JSONObject();
            try {
                photoJSONObject.put("url", photoUri.toString());
                return photoJSONObject;
            } catch (Throwable e) {
                throw new FacebookException("Unable to attach images", e);
            }
        }
    }

    public static void invokeCallbackWithException(FacebookCallback<Result> callback, Exception exception) {
        if (exception instanceof FacebookException) {
            invokeOnErrorCallback((FacebookCallback) callback, (FacebookException) exception);
        } else {
            invokeCallbackWithError(callback, "Error preparing share content: " + exception.getLocalizedMessage());
        }
    }

    public static void invokeCallbackWithError(FacebookCallback<Result> callback, String error) {
        invokeOnErrorCallback((FacebookCallback) callback, error);
    }

    public static void invokeCallbackWithResults(FacebookCallback<Result> callback, String postId, GraphResponse graphResponse) {
        FacebookRequestError requestError = graphResponse.getError();
        if (requestError != null) {
            String errorMessage = requestError.getErrorMessage();
            if (Utility.isNullOrEmpty(errorMessage)) {
                errorMessage = "Unexpected error sharing.";
            }
            invokeOnErrorCallback(callback, graphResponse, errorMessage);
            return;
        }
        invokeOnSuccessCallback(callback, postId);
    }

    public static boolean getNativeDialogDidComplete(Bundle result) {
        if (result.containsKey(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETE_KEY)) {
            return result.getBoolean(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETE_KEY);
        }
        return result.getBoolean(NativeProtocol.EXTRA_DIALOG_COMPLETE_KEY, false);
    }

    public static String getNativeDialogCompletionGesture(Bundle result) {
        if (result.containsKey(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY)) {
            return result.getString(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY);
        }
        return result.getString(NativeProtocol.EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    public static String getShareDialogPostId(Bundle result) {
        if (result.containsKey(ShareConstants.RESULT_POST_ID)) {
            return result.getString(ShareConstants.RESULT_POST_ID);
        }
        if (result.containsKey(ShareConstants.EXTRA_RESULT_POST_ID)) {
            return result.getString(ShareConstants.EXTRA_RESULT_POST_ID);
        }
        return result.getString(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID);
    }

    public static boolean handleActivityResult(int requestCode, int resultCode, Intent data, ResultProcessor resultProcessor) {
        AppCall appCall = getAppCallFromActivityResult(requestCode, resultCode, data);
        if (appCall == null) {
            return false;
        }
        NativeAppCallAttachmentStore.cleanupAttachmentsForCall(appCall.getCallId());
        if (resultProcessor == null) {
            return true;
        }
        FacebookException exception = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getErrorDataFromResultIntent(data));
        if (exception == null) {
            resultProcessor.onSuccess(appCall, NativeProtocol.getSuccessResultsFromIntent(data));
            return true;
        } else if (exception instanceof FacebookOperationCanceledException) {
            resultProcessor.onCancel(appCall);
            return true;
        } else {
            resultProcessor.onError(appCall, exception);
            return true;
        }
    }

    public static ResultProcessor getShareResultProcessor(final FacebookCallback<Result> callback) {
        return new ResultProcessor(callback) {
            public void onSuccess(AppCall appCall, Bundle results) {
                if (results != null) {
                    String gesture = ShareInternalUtility.getNativeDialogCompletionGesture(results);
                    if (gesture == null || "post".equalsIgnoreCase(gesture)) {
                        ShareInternalUtility.invokeOnSuccessCallback(callback, ShareInternalUtility.getShareDialogPostId(results));
                    } else if ("cancel".equalsIgnoreCase(gesture)) {
                        ShareInternalUtility.invokeOnCancelCallback(callback);
                    } else {
                        ShareInternalUtility.invokeOnErrorCallback(callback, new FacebookException(NativeProtocol.ERROR_UNKNOWN_ERROR));
                    }
                }
            }

            public void onCancel(AppCall appCall) {
                ShareInternalUtility.invokeOnCancelCallback(callback);
            }

            public void onError(AppCall appCall, FacebookException error) {
                ShareInternalUtility.invokeOnErrorCallback(callback, error);
            }
        };
    }

    private static AppCall getAppCallFromActivityResult(int requestCode, int resultCode, Intent data) {
        UUID callId = NativeProtocol.getCallIdFromIntent(data);
        if (callId == null) {
            return null;
        }
        return AppCall.finishPendingCall(callId, requestCode);
    }

    public static void registerStaticShareCallback(final int requestCode) {
        CallbackManagerImpl.registerStaticCallback(requestCode, new Callback() {
            public boolean onActivityResult(int resultCode, Intent data) {
                return ShareInternalUtility.handleActivityResult(requestCode, resultCode, data, ShareInternalUtility.getShareResultProcessor(null));
            }
        });
    }

    public static void registerSharerCallback(final int requestCode, CallbackManager callbackManager, final FacebookCallback<Result> callback) {
        if (callbackManager instanceof CallbackManagerImpl) {
            ((CallbackManagerImpl) callbackManager).registerCallback(requestCode, new Callback() {
                public boolean onActivityResult(int resultCode, Intent data) {
                    return ShareInternalUtility.handleActivityResult(requestCode, resultCode, data, ShareInternalUtility.getShareResultProcessor(callback));
                }
            });
            return;
        }
        throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
    }

    public static List<String> getPhotoUrls(SharePhotoContent photoContent, final UUID appCallId) {
        if (photoContent != null) {
            List<SharePhoto> photos = photoContent.getPhotos();
            if (photos != null) {
                List<Attachment> attachments = Utility.map(photos, new Mapper<SharePhoto, Attachment>() {
                    public Attachment apply(SharePhoto item) {
                        return ShareInternalUtility.getAttachment(appCallId, item);
                    }
                });
                List<String> attachmentUrls = Utility.map(attachments, new C13865());
                NativeAppCallAttachmentStore.addAttachments(attachments);
                return attachmentUrls;
            }
        }
        return null;
    }

    public static JSONObject toJSONObjectForCall(final UUID callId, ShareOpenGraphAction action) throws JSONException {
        final ArrayList<Attachment> attachments = new ArrayList();
        JSONObject actionJSON = OpenGraphJSONUtility.toJSONObject(action, new PhotoJSONProcessor() {
            public JSONObject toJSONObject(SharePhoto photo) {
                Attachment attachment = ShareInternalUtility.getAttachment(callId, photo);
                if (attachment == null) {
                    return null;
                }
                attachments.add(attachment);
                JSONObject photoJSONObject = new JSONObject();
                try {
                    photoJSONObject.put("url", attachment.getAttachmentUrl());
                    if (!photo.getUserGenerated()) {
                        return photoJSONObject;
                    }
                    photoJSONObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                    return photoJSONObject;
                } catch (Throwable e) {
                    throw new FacebookException("Unable to attach images", e);
                }
            }
        });
        NativeAppCallAttachmentStore.addAttachments(attachments);
        return actionJSON;
    }

    public static JSONObject toJSONObjectForWeb(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        return OpenGraphJSONUtility.toJSONObject(shareOpenGraphContent.getAction(), new C13887());
    }

    public static JSONArray removeNamespacesFromOGJsonArray(JSONArray jsonArray, boolean requireNamespace) throws JSONException {
        JSONArray newArray = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONArray) {
                value = removeNamespacesFromOGJsonArray((JSONArray) value, requireNamespace);
            } else if (value instanceof JSONObject) {
                value = removeNamespacesFromOGJsonObject((JSONObject) value, requireNamespace);
            }
            newArray.put(value);
        }
        return newArray;
    }

    public static JSONObject removeNamespacesFromOGJsonObject(JSONObject jsonObject, boolean requireNamespace) {
        if (jsonObject == null) {
            return null;
        }
        try {
            JSONObject newJsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            JSONArray names = jsonObject.names();
            for (int i = 0; i < names.length(); i++) {
                String key = names.getString(i);
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    value = removeNamespacesFromOGJsonObject((JSONObject) value, true);
                } else if (value instanceof JSONArray) {
                    value = removeNamespacesFromOGJsonArray((JSONArray) value, true);
                }
                Pair<String, String> fieldNameAndNamespace = getFieldNameAndNamespaceFromFullName(key);
                String namespace = fieldNameAndNamespace.first;
                String fieldName = fieldNameAndNamespace.second;
                if (requireNamespace) {
                    if (namespace != null && namespace.equals("fbsdk")) {
                        newJsonObject.put(key, value);
                    } else if (namespace == null || namespace.equals("og")) {
                        newJsonObject.put(fieldName, value);
                    } else {
                        data.put(fieldName, value);
                    }
                } else if (namespace == null || !namespace.equals("fb")) {
                    newJsonObject.put(fieldName, value);
                } else {
                    newJsonObject.put(key, value);
                }
            }
            if (data.length() <= 0) {
                return newJsonObject;
            }
            newJsonObject.put("data", data);
            return newJsonObject;
        } catch (JSONException e) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    public static Pair<String, String> getFieldNameAndNamespaceFromFullName(String fullName) {
        String fieldName;
        String namespace = null;
        int index = fullName.indexOf(58);
        if (index == -1 || fullName.length() <= index + 1) {
            fieldName = fullName;
        } else {
            namespace = fullName.substring(0, index);
            fieldName = fullName.substring(index + 1);
        }
        return new Pair(namespace, fieldName);
    }

    private ShareInternalUtility() {
    }

    private static Attachment getAttachment(UUID callId, SharePhoto photo) {
        Bitmap bitmap = photo.getBitmap();
        Uri photoUri = photo.getImageUrl();
        if (bitmap != null) {
            return NativeAppCallAttachmentStore.createAttachment(callId, bitmap);
        }
        if (photoUri != null) {
            return NativeAppCallAttachmentStore.createAttachment(callId, photoUri);
        }
        return null;
    }

    static void invokeOnCancelCallback(FacebookCallback<Result> callback) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, null);
        if (callback != null) {
            callback.onCancel();
        }
    }

    static void invokeOnSuccessCallback(FacebookCallback<Result> callback, String postId) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED, null);
        if (callback != null) {
            callback.onSuccess(new Result(postId));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Result> callback, GraphResponse response, String message) {
        logShareResult("error", message);
        if (callback != null) {
            callback.onError(new FacebookGraphResponseException(response, message));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Result> callback, String message) {
        logShareResult("error", message);
        if (callback != null) {
            callback.onError(new FacebookException(message));
        }
    }

    static void invokeOnErrorCallback(FacebookCallback<Result> callback, FacebookException ex) {
        logShareResult("error", ex.getMessage());
        if (callback != null) {
            callback.onError(ex);
        }
    }

    private static void logShareResult(String shareOutcome, String errorMessage) {
        AppEventsLogger logger = AppEventsLogger.newLogger(FacebookSdk.getApplicationContext());
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_SHARE_OUTCOME, shareOutcome);
        if (errorMessage != null) {
            parameters.putString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, errorMessage);
        }
        logger.logSdkEvent(AnalyticsEvents.EVENT_SHARE_RESULT, null, parameters);
    }

    public static GraphRequest newPostOpenGraphObjectRequest(AccessToken accessToken, JSONObject openGraphObject, GraphRequest.Callback callback) {
        if (openGraphObject == null) {
            throw new FacebookException("openGraphObject cannot be null");
        } else if (Utility.isNullOrEmpty(openGraphObject.optString("type"))) {
            throw new FacebookException("openGraphObject must have non-null 'type' property");
        } else if (Utility.isNullOrEmpty(openGraphObject.optString("title"))) {
            throw new FacebookException("openGraphObject must have non-null 'title' property");
        } else {
            String path = String.format(MY_OBJECTS_FORMAT, new Object[]{openGraphObject.optString("type")});
            Bundle bundle = new Bundle();
            bundle.putString(OBJECT_PARAM, openGraphObject.toString());
            return new GraphRequest(accessToken, path, bundle, HttpMethod.POST, callback);
        }
    }

    public static GraphRequest newPostOpenGraphObjectRequest(AccessToken accessToken, String type, String title, String imageUrl, String url, String description, JSONObject objectProperties, GraphRequest.Callback callback) {
        return newPostOpenGraphObjectRequest(accessToken, GraphUtil.createOpenGraphObjectForPost(type, title, imageUrl, url, description, objectProperties, null), callback);
    }

    public static GraphRequest newPostOpenGraphActionRequest(AccessToken accessToken, JSONObject openGraphAction, GraphRequest.Callback callback) {
        if (openGraphAction == null) {
            throw new FacebookException("openGraphAction cannot be null");
        }
        if (Utility.isNullOrEmpty(openGraphAction.optString("type"))) {
            throw new FacebookException("openGraphAction must have non-null 'type' property");
        }
        return GraphRequest.newPostRequest(accessToken, String.format(MY_ACTION_FORMAT, new Object[]{type}), openGraphAction, callback);
    }

    public static GraphRequest newUpdateOpenGraphObjectRequest(AccessToken accessToken, JSONObject openGraphObject, GraphRequest.Callback callback) {
        if (openGraphObject == null) {
            throw new FacebookException("openGraphObject cannot be null");
        }
        String path = openGraphObject.optString(ShareConstants.WEB_DIALOG_PARAM_ID);
        if (path == null) {
            throw new FacebookException("openGraphObject must have an id");
        }
        Bundle bundle = new Bundle();
        bundle.putString(OBJECT_PARAM, openGraphObject.toString());
        return new GraphRequest(accessToken, path, bundle, HttpMethod.POST, callback);
    }

    public static GraphRequest newUpdateOpenGraphObjectRequest(AccessToken accessToken, String id, String title, String imageUrl, String url, String description, JSONObject objectProperties, GraphRequest.Callback callback) {
        return newUpdateOpenGraphObjectRequest(accessToken, GraphUtil.createOpenGraphObjectForPost(null, title, imageUrl, url, description, objectProperties, id), callback);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken, Bitmap image, String caption, GraphRequest.Callback callback) {
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("picture", image);
        if (!(caption == null || caption.isEmpty())) {
            parameters.putString(CAPTION_PARAM, caption);
        }
        return new GraphRequest(accessToken, MY_PHOTOS, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken, File file, String caption, GraphRequest.Callback callback) throws FileNotFoundException {
        ParcelFileDescriptor descriptor = ParcelFileDescriptor.open(file, 268435456);
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("picture", descriptor);
        if (!(caption == null || caption.isEmpty())) {
            parameters.putString(CAPTION_PARAM, caption);
        }
        return new GraphRequest(accessToken, MY_PHOTOS, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken, Uri photoUri, String caption, GraphRequest.Callback callback) throws FileNotFoundException {
        if (Utility.isFileUri(photoUri)) {
            return newUploadPhotoRequest(accessToken, new File(photoUri.getPath()), caption, callback);
        }
        if (Utility.isContentUri(photoUri)) {
            Bundle parameters = new Bundle(1);
            parameters.putParcelable("picture", photoUri);
            return new GraphRequest(accessToken, MY_PHOTOS, parameters, HttpMethod.POST, callback);
        }
        throw new FacebookException("The photo Uri must be either a file:// or content:// Uri");
    }

    public static GraphRequest newStatusUpdateRequest(AccessToken accessToken, String message, GraphRequest.Callback callback) {
        return newStatusUpdateRequest(accessToken, message, (String) null, null, callback);
    }

    private static GraphRequest newStatusUpdateRequest(AccessToken accessToken, String message, String placeId, List<String> tagIds, GraphRequest.Callback callback) {
        Bundle parameters = new Bundle();
        parameters.putString("message", message);
        if (placeId != null) {
            parameters.putString("place", placeId);
        }
        if (tagIds != null && tagIds.size() > 0) {
            parameters.putString("tags", TextUtils.join(",", tagIds));
        }
        return new GraphRequest(accessToken, MY_FEED, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newStatusUpdateRequest(AccessToken accessToken, String message, JSONObject place, List<JSONObject> tags, GraphRequest.Callback callback) {
        List tagIds = null;
        if (tags != null) {
            tagIds = new ArrayList(tags.size());
            for (JSONObject tag : tags) {
                tagIds.add(tag.optString(ShareConstants.WEB_DIALOG_PARAM_ID));
            }
        }
        return newStatusUpdateRequest(accessToken, message, place == null ? null : place.optString(ShareConstants.WEB_DIALOG_PARAM_ID), tagIds, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Bitmap image, GraphRequest.Callback callback) {
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("file", image);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) throws FileNotFoundException {
        ParcelableResourceWithMimeType<ParcelFileDescriptor> resourceWithMimeType = new ParcelableResourceWithMimeType(ParcelFileDescriptor.open(file, 268435456), "image/png");
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("file", resourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Uri imageUri, GraphRequest.Callback callback) throws FileNotFoundException {
        if (Utility.isFileUri(imageUri)) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(imageUri.getPath()), callback);
        }
        if (Utility.isContentUri(imageUri)) {
            ParcelableResourceWithMimeType<Uri> resourceWithMimeType = new ParcelableResourceWithMimeType((Parcelable) imageUri, "image/png");
            Bundle parameters = new Bundle(1);
            parameters.putParcelable("file", resourceWithMimeType);
            return new GraphRequest(accessToken, MY_STAGING_RESOURCES, parameters, HttpMethod.POST, callback);
        }
        throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
    }

    @Nullable
    public static ObjectType getMostSpecificObjectType(ObjectType objectType1, ObjectType objectType2) {
        if (objectType1 == objectType2) {
            return objectType1;
        }
        if (objectType1 == ObjectType.UNKNOWN) {
            return objectType2;
        }
        return objectType2 != ObjectType.UNKNOWN ? null : objectType1;
    }
}
