package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.Utility;
import com.facebook.share.model.AppGroupCreationContent;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import java.util.Locale;
import org.json.JSONObject;

public class WebDialogParameters {
    public static Bundle create(AppGroupCreationContent appGroupCreationContent) {
        Bundle webParams = new Bundle();
        Utility.putNonEmptyString(webParams, "name", appGroupCreationContent.getName());
        Utility.putNonEmptyString(webParams, "description", appGroupCreationContent.getDescription());
        Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_PRIVACY, appGroupCreationContent.getAppGroupPrivacy().toString().toLowerCase(Locale.ENGLISH));
        return webParams;
    }

    public static Bundle create(GameRequestContent gameRequestContent) {
        Bundle webParams = new Bundle();
        Utility.putNonEmptyString(webParams, "message", gameRequestContent.getMessage());
        Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_TO, gameRequestContent.getTo());
        Utility.putNonEmptyString(webParams, "title", gameRequestContent.getTitle());
        Utility.putNonEmptyString(webParams, "data", gameRequestContent.getData());
        if (gameRequestContent.getActionType() != null) {
            Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, gameRequestContent.getActionType().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putNonEmptyString(webParams, "object_id", gameRequestContent.getObjectId());
        if (gameRequestContent.getFilters() != null) {
            Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_FILTERS, gameRequestContent.getFilters().toString().toLowerCase(Locale.ENGLISH));
        }
        Utility.putCommaSeparatedStringList(webParams, ShareConstants.WEB_DIALOG_PARAM_SUGGESTIONS, gameRequestContent.getSuggestions());
        return webParams;
    }

    public static Bundle create(ShareLinkContent shareLinkContent) {
        Bundle params = new Bundle();
        Utility.putUri(params, ShareConstants.WEB_DIALOG_PARAM_HREF, shareLinkContent.getContentUrl());
        return params;
    }

    public static Bundle create(ShareOpenGraphContent shareOpenGraphContent) {
        Bundle params = new Bundle();
        Utility.putNonEmptyString(params, ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE, shareOpenGraphContent.getAction().getActionType());
        try {
            JSONObject ogJSON = ShareInternalUtility.removeNamespacesFromOGJsonObject(ShareInternalUtility.toJSONObjectForWeb(shareOpenGraphContent), false);
            if (ogJSON != null) {
                Utility.putNonEmptyString(params, ShareConstants.WEB_DIALOG_PARAM_ACTION_PROPERTIES, ogJSON.toString());
            }
            return params;
        } catch (Throwable e) {
            throw new FacebookException("Unable to serialize the ShareOpenGraphContent to JSON", e);
        }
    }

    public static Bundle createForFeed(ShareLinkContent shareLinkContent) {
        Bundle webParams = new Bundle();
        Utility.putNonEmptyString(webParams, "name", shareLinkContent.getContentTitle());
        Utility.putNonEmptyString(webParams, "description", shareLinkContent.getContentDescription());
        Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_LINK, Utility.getUriString(shareLinkContent.getContentUrl()));
        Utility.putNonEmptyString(webParams, ShareConstants.WEB_DIALOG_PARAM_PICTURE, Utility.getUriString(shareLinkContent.getImageUrl()));
        return webParams;
    }
}
