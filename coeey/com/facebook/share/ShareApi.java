package com.facebook.share;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.internal.CollectionMapper;
import com.facebook.internal.CollectionMapper.Collection;
import com.facebook.internal.CollectionMapper.OnErrorListener;
import com.facebook.internal.CollectionMapper.OnMapValueCompleteListener;
import com.facebook.internal.CollectionMapper.OnMapperCompleteListener;
import com.facebook.internal.CollectionMapper.ValueMapper;
import com.facebook.internal.Mutable;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.VideoUploader;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShareApi {
    private static final String TAG = "ShareApi";
    private String message;
    private final ShareContent shareContent;

    static class C13627 implements ValueMapper {
        C13627() {
        }

        public void mapValue(Object value, OnMapValueCompleteListener onMapValueCompleteListener) {
            if (value instanceof ArrayList) {
                ShareApi.stageArrayList((ArrayList) value, onMapValueCompleteListener);
            } else if (value instanceof ShareOpenGraphObject) {
                ShareApi.stageOpenGraphObject((ShareOpenGraphObject) value, onMapValueCompleteListener);
            } else if (value instanceof SharePhoto) {
                ShareApi.stagePhoto((SharePhoto) value, onMapValueCompleteListener);
            } else {
                onMapValueCompleteListener.onComplete(value);
            }
        }
    }

    public static void share(ShareContent shareContent, FacebookCallback<Result> callback) {
        new ShareApi(shareContent).share(callback);
    }

    public ShareApi(ShareContent shareContent) {
        this.shareContent = shareContent;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ShareContent getShareContent() {
        return this.shareContent;
    }

    public boolean canShare() {
        if (getShareContent() == null) {
            return false;
        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            return false;
        }
        Set<String> permissions = accessToken.getPermissions();
        if (permissions == null || !permissions.contains("publish_actions")) {
            Log.w(TAG, "The publish_actions permissions are missing, the share will fail unless this app was authorized to publish in another installation.");
        }
        return true;
    }

    public void share(FacebookCallback<Result> callback) {
        if (canShare()) {
            ShareContent shareContent = getShareContent();
            try {
                ShareContentValidation.validateForApiShare(shareContent);
                if (shareContent instanceof ShareLinkContent) {
                    shareLinkContent((ShareLinkContent) shareContent, callback);
                    return;
                } else if (shareContent instanceof SharePhotoContent) {
                    sharePhotoContent((SharePhotoContent) shareContent, callback);
                    return;
                } else if (shareContent instanceof ShareVideoContent) {
                    shareVideoContent((ShareVideoContent) shareContent, callback);
                    return;
                } else if (shareContent instanceof ShareOpenGraphContent) {
                    shareOpenGraphContent((ShareOpenGraphContent) shareContent, callback);
                    return;
                } else {
                    return;
                }
            } catch (FacebookException ex) {
                ShareInternalUtility.invokeCallbackWithException(callback, ex);
                return;
            }
        }
        ShareInternalUtility.invokeCallbackWithError(callback, "Insufficient permissions for sharing content via Api.");
    }

    private void addCommonParameters(Bundle bundle, ShareContent shareContent) {
        List<String> peopleIds = shareContent.getPeopleIds();
        if (!(peopleIds == null || peopleIds.isEmpty())) {
            bundle.putString("tags", TextUtils.join(", ", peopleIds));
        }
        bundle.putString("place", shareContent.getPlaceId());
        bundle.putString("ref", shareContent.getRef());
    }

    private void shareOpenGraphContent(ShareOpenGraphContent openGraphContent, final FacebookCallback<Result> callback) {
        final Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                JSONObject data = response.getJSONObject();
                ShareInternalUtility.invokeCallbackWithResults(callback, data == null ? null : data.optString(ShareConstants.WEB_DIALOG_PARAM_ID), response);
            }
        };
        final ShareOpenGraphAction action = openGraphContent.getAction();
        final Bundle parameters = action.getBundle();
        addCommonParameters(parameters, openGraphContent);
        if (!Utility.isNullOrEmpty(getMessage())) {
            parameters.putString("message", getMessage());
        }
        final FacebookCallback<Result> facebookCallback = callback;
        stageOpenGraphAction(parameters, new OnMapperCompleteListener() {
            public void onComplete() {
                try {
                    ShareApi.handleImagesOnAction(parameters);
                    new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/" + URLEncoder.encode(action.getActionType(), "UTF-8"), parameters, HttpMethod.POST, requestCallback).executeAsync();
                } catch (UnsupportedEncodingException ex) {
                    ShareInternalUtility.invokeCallbackWithException(facebookCallback, ex);
                }
            }

            public void onError(FacebookException exception) {
                ShareInternalUtility.invokeCallbackWithException(facebookCallback, exception);
            }
        });
    }

    private static void handleImagesOnAction(Bundle parameters) {
        String imageStr = parameters.getString("image");
        if (imageStr != null) {
            try {
                JSONArray images = new JSONArray(imageStr);
                for (int i = 0; i < images.length(); i++) {
                    JSONObject jsonImage = images.optJSONObject(i);
                    if (jsonImage != null) {
                        putImageInBundleWithArrayFormat(parameters, i, jsonImage);
                    } else {
                        parameters.putString(String.format(Locale.ROOT, "image[%d][url]", new Object[]{Integer.valueOf(i)}), images.getString(i));
                    }
                }
                parameters.remove("image");
            } catch (JSONException e) {
                try {
                    putImageInBundleWithArrayFormat(parameters, 0, new JSONObject(imageStr));
                    parameters.remove("image");
                } catch (JSONException e2) {
                }
            }
        }
    }

    private static void putImageInBundleWithArrayFormat(Bundle parameters, int index, JSONObject image) throws JSONException {
        Iterator<String> keys = image.keys();
        while (keys.hasNext()) {
            Object[] objArr = new Object[]{Integer.valueOf(index), (String) keys.next()};
            parameters.putString(String.format(Locale.ROOT, "image[%d][%s]", objArr), image.get((String) keys.next()).toString());
        }
    }

    private void sharePhotoContent(SharePhotoContent photoContent, FacebookCallback<Result> callback) {
        final Mutable<Integer> requestCount = new Mutable(Integer.valueOf(0));
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        ArrayList<GraphRequest> requests = new ArrayList();
        final ArrayList<JSONObject> results = new ArrayList();
        final ArrayList<GraphResponse> errorResponses = new ArrayList();
        final FacebookCallback<Result> facebookCallback = callback;
        Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                JSONObject result = response.getJSONObject();
                if (result != null) {
                    results.add(result);
                }
                if (response.getError() != null) {
                    errorResponses.add(response);
                }
                requestCount.value = Integer.valueOf(((Integer) requestCount.value).intValue() - 1);
                if (((Integer) requestCount.value).intValue() != 0) {
                    return;
                }
                if (!errorResponses.isEmpty()) {
                    ShareInternalUtility.invokeCallbackWithResults(facebookCallback, null, (GraphResponse) errorResponses.get(0));
                } else if (!results.isEmpty()) {
                    ShareInternalUtility.invokeCallbackWithResults(facebookCallback, ((JSONObject) results.get(0)).optString(ShareConstants.WEB_DIALOG_PARAM_ID), response);
                }
            }
        };
        try {
            for (SharePhoto photo : photoContent.getPhotos()) {
                Bitmap bitmap = photo.getBitmap();
                Uri photoUri = photo.getImageUrl();
                String caption = photo.getCaption();
                if (caption == null) {
                    caption = getMessage();
                }
                if (bitmap != null) {
                    requests.add(ShareInternalUtility.newUploadPhotoRequest(accessToken, bitmap, caption, requestCallback));
                } else if (photoUri != null) {
                    requests.add(ShareInternalUtility.newUploadPhotoRequest(accessToken, photoUri, caption, requestCallback));
                } else {
                    continue;
                }
            }
            requestCount.value = Integer.valueOf(((Integer) requestCount.value).intValue() + requests.size());
            Iterator i$ = requests.iterator();
            while (i$.hasNext()) {
                ((GraphRequest) i$.next()).executeAsync();
            }
        } catch (FileNotFoundException ex) {
            ShareInternalUtility.invokeCallbackWithException(callback, ex);
        }
    }

    private void shareLinkContent(ShareLinkContent linkContent, final FacebookCallback<Result> callback) {
        Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                JSONObject data = response.getJSONObject();
                ShareInternalUtility.invokeCallbackWithResults(callback, data == null ? null : data.optString(ShareConstants.WEB_DIALOG_PARAM_ID), response);
            }
        };
        Bundle parameters = new Bundle();
        addCommonParameters(parameters, linkContent);
        parameters.putString("message", getMessage());
        parameters.putString(ShareConstants.WEB_DIALOG_PARAM_LINK, Utility.getUriString(linkContent.getContentUrl()));
        parameters.putString(ShareConstants.WEB_DIALOG_PARAM_PICTURE, Utility.getUriString(linkContent.getImageUrl()));
        parameters.putString("name", linkContent.getContentTitle());
        parameters.putString("description", linkContent.getContentDescription());
        parameters.putString("ref", linkContent.getRef());
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/feed", parameters, HttpMethod.POST, requestCallback).executeAsync();
    }

    private void shareVideoContent(ShareVideoContent videoContent, FacebookCallback<Result> callback) {
        try {
            VideoUploader.uploadAsync(videoContent, callback);
        } catch (FileNotFoundException ex) {
            ShareInternalUtility.invokeCallbackWithException(callback, ex);
        }
    }

    private static void stageArrayList(final ArrayList arrayList, final OnMapValueCompleteListener onArrayListStagedListener) {
        final JSONArray stagedObject = new JSONArray();
        stageCollectionValues(new Collection<Integer>() {
            public Iterator<Integer> keyIterator() {
                final int size = arrayList.size();
                final Mutable<Integer> current = new Mutable(Integer.valueOf(0));
                return new Iterator<Integer>() {
                    public boolean hasNext() {
                        return ((Integer) current.value).intValue() < size;
                    }

                    public Integer next() {
                        Integer num = (Integer) current.value;
                        Mutable mutable = current;
                        mutable.value = Integer.valueOf(((Integer) mutable.value).intValue() + 1);
                        return num;
                    }

                    public void remove() {
                    }
                };
            }

            public Object get(Integer key) {
                return arrayList.get(key.intValue());
            }

            public void set(Integer key, Object value, OnErrorListener onErrorListener) {
                try {
                    stagedObject.put(key.intValue(), value);
                } catch (JSONException ex) {
                    String message = ex.getLocalizedMessage();
                    if (message == null) {
                        message = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(message));
                }
            }
        }, new OnMapperCompleteListener() {
            public void onComplete() {
                onArrayListStagedListener.onComplete(stagedObject);
            }

            public void onError(FacebookException exception) {
                onArrayListStagedListener.onError(exception);
            }
        });
    }

    private static <T> void stageCollectionValues(Collection<T> collection, OnMapperCompleteListener onCollectionValuesStagedListener) {
        CollectionMapper.iterate(collection, new C13627(), onCollectionValuesStagedListener);
    }

    private static void stageOpenGraphAction(final Bundle parameters, OnMapperCompleteListener onOpenGraphActionStagedListener) {
        stageCollectionValues(new Collection<String>() {
            public Iterator<String> keyIterator() {
                return parameters.keySet().iterator();
            }

            public Object get(String key) {
                return parameters.get(key);
            }

            public void set(String key, Object value, OnErrorListener onErrorListener) {
                if (!Utility.putJSONValueInBundle(parameters, key, value)) {
                    onErrorListener.onError(new FacebookException("Unexpected value: " + value.toString()));
                }
            }
        }, onOpenGraphActionStagedListener);
    }

    private static void stageOpenGraphObject(final ShareOpenGraphObject object, final OnMapValueCompleteListener onOpenGraphObjectStagedListener) {
        String type = object.getString("type");
        if (type == null) {
            type = object.getString("og:type");
        }
        if (type == null) {
            onOpenGraphObjectStagedListener.onError(new FacebookException("Open Graph objects must contain a type value."));
            return;
        }
        final JSONObject stagedObject = new JSONObject();
        Collection<String> collection = new Collection<String>() {
            public Iterator<String> keyIterator() {
                return object.keySet().iterator();
            }

            public Object get(String key) {
                return object.get(key);
            }

            public void set(String key, Object value, OnErrorListener onErrorListener) {
                try {
                    stagedObject.put(key, value);
                } catch (JSONException ex) {
                    String message = ex.getLocalizedMessage();
                    if (message == null) {
                        message = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(message));
                }
            }
        };
        final Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                FacebookRequestError error = response.getError();
                if (error != null) {
                    String message = error.getErrorMessage();
                    if (message == null) {
                        message = "Error staging Open Graph object.";
                    }
                    onOpenGraphObjectStagedListener.onError(new FacebookGraphResponseException(response, message));
                    return;
                }
                JSONObject data = response.getJSONObject();
                if (data == null) {
                    onOpenGraphObjectStagedListener.onError(new FacebookGraphResponseException(response, "Error staging Open Graph object."));
                    return;
                }
                String stagedObjectId = data.optString(ShareConstants.WEB_DIALOG_PARAM_ID);
                if (stagedObjectId == null) {
                    onOpenGraphObjectStagedListener.onError(new FacebookGraphResponseException(response, "Error staging Open Graph object."));
                } else {
                    onOpenGraphObjectStagedListener.onComplete(stagedObjectId);
                }
            }
        };
        final String ogType = type;
        stageCollectionValues(collection, new OnMapperCompleteListener() {
            public void onComplete() {
                String objectString = stagedObject.toString();
                Bundle parameters = new Bundle();
                parameters.putString("object", objectString);
                try {
                    new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/objects/" + URLEncoder.encode(ogType, "UTF-8"), parameters, HttpMethod.POST, requestCallback).executeAsync();
                } catch (UnsupportedEncodingException ex) {
                    String message = ex.getLocalizedMessage();
                    if (message == null) {
                        message = "Error staging Open Graph object.";
                    }
                    onOpenGraphObjectStagedListener.onError(new FacebookException(message));
                }
            }

            public void onError(FacebookException exception) {
                onOpenGraphObjectStagedListener.onError(exception);
            }
        });
    }

    private static void stagePhoto(final SharePhoto photo, final OnMapValueCompleteListener onPhotoStagedListener) {
        Bitmap bitmap = photo.getBitmap();
        Uri imageUrl = photo.getImageUrl();
        if (bitmap == null && imageUrl == null) {
            onPhotoStagedListener.onError(new FacebookException("Photos must have an imageURL or bitmap."));
            return;
        }
        Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                FacebookRequestError error = response.getError();
                String message;
                if (error != null) {
                    message = error.getErrorMessage();
                    if (message == null) {
                        message = "Error staging photo.";
                    }
                    onPhotoStagedListener.onError(new FacebookGraphResponseException(response, message));
                    return;
                }
                JSONObject data = response.getJSONObject();
                if (data == null) {
                    onPhotoStagedListener.onError(new FacebookException("Error staging photo."));
                    return;
                }
                String stagedImageUri = data.optString(RNFetchBlobConst.DATA_ENCODE_URI);
                if (stagedImageUri == null) {
                    onPhotoStagedListener.onError(new FacebookException("Error staging photo."));
                    return;
                }
                JSONObject stagedObject = new JSONObject();
                try {
                    stagedObject.put("url", stagedImageUri);
                    stagedObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, photo.getUserGenerated());
                    onPhotoStagedListener.onComplete(stagedObject);
                } catch (JSONException ex) {
                    message = ex.getLocalizedMessage();
                    if (message == null) {
                        message = "Error staging photo.";
                    }
                    onPhotoStagedListener.onError(new FacebookException(message));
                }
            }
        };
        if (bitmap != null) {
            ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), bitmap, requestCallback).executeAsync();
            return;
        }
        try {
            ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), imageUrl, requestCallback).executeAsync();
        } catch (FileNotFoundException ex) {
            String message = ex.getLocalizedMessage();
            if (message == null) {
                message = "Error staging photo.";
            }
            onPhotoStagedListener.onError(new FacebookException(message));
        }
    }
}
