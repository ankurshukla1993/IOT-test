package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.net.Uri;
import com.facebook.infer.annotation.Assertions;
import javax.annotation.Nullable;

public class ImageSource {
    private boolean isResource;
    private double mSize;
    private String mSource;
    @Nullable
    private Uri mUri;

    public ImageSource(Context context, String source, double width, double height) {
        this.mSource = source;
        this.mSize = width * height;
        this.mUri = computeUri(context);
    }

    public ImageSource(Context context, String source) {
        this(context, source, 0.0d, 0.0d);
    }

    public String getSource() {
        return this.mSource;
    }

    public Uri getUri() {
        return (Uri) Assertions.assertNotNull(this.mUri);
    }

    public double getSize() {
        return this.mSize;
    }

    public boolean isResource() {
        return this.isResource;
    }

    private Uri computeUri(Context context) {
        try {
            Uri uri = Uri.parse(this.mSource);
            if (uri.getScheme() == null) {
                return computeLocalUri(context);
            }
            return uri;
        } catch (Exception e) {
            return computeLocalUri(context);
        }
    }

    private Uri computeLocalUri(Context context) {
        this.isResource = true;
        return ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(context, this.mSource);
    }
}
