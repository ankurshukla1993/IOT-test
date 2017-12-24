package com.facebook.react.modules.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class ImageEditingManager$CropTask extends GuardedAsyncTask<Void, Void> {
    final Context mContext;
    final Callback mError;
    final int mHeight;
    final Callback mSuccess;
    int mTargetHeight;
    int mTargetWidth;
    final String mUri;
    final int mWidth;
    final int mX;
    final int mY;

    private ImageEditingManager$CropTask(ReactContext context, String uri, int x, int y, int width, int height, Callback success, Callback error) {
        super(context);
        this.mTargetWidth = 0;
        this.mTargetHeight = 0;
        if (x < 0 || y < 0 || width <= 0 || height <= 0) {
            throw new JSApplicationIllegalArgumentException(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", new Object[]{Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(width), Integer.valueOf(height)}));
        }
        this.mContext = context;
        this.mUri = uri;
        this.mX = x;
        this.mY = y;
        this.mWidth = width;
        this.mHeight = height;
        this.mSuccess = success;
        this.mError = error;
    }

    public void setTargetSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new JSApplicationIllegalArgumentException(String.format("Invalid target size: [%d, %d]", new Object[]{Integer.valueOf(width), Integer.valueOf(height)}));
        }
        this.mTargetWidth = width;
        this.mTargetHeight = height;
    }

    private InputStream openBitmapInputStream() throws IOException {
        InputStream stream;
        if (ImageEditingManager.access$200(this.mUri)) {
            stream = this.mContext.getContentResolver().openInputStream(Uri.parse(this.mUri));
        } else {
            stream = new URL(this.mUri).openConnection().getInputStream();
        }
        if (stream != null) {
            return stream;
        }
        throw new IOException("Cannot open bitmap: " + this.mUri);
    }

    protected void doInBackgroundGuarded(Void... params) {
        try {
            boolean hasTargetSize;
            Bitmap cropped;
            Options outOptions = new Options();
            if (this.mTargetWidth <= 0 || this.mTargetHeight <= 0) {
                hasTargetSize = false;
            } else {
                hasTargetSize = true;
            }
            if (hasTargetSize) {
                cropped = cropAndResize(this.mTargetWidth, this.mTargetHeight, outOptions);
            } else {
                cropped = crop(outOptions);
            }
            String mimeType = outOptions.outMimeType;
            if (mimeType == null || mimeType.isEmpty()) {
                throw new IOException("Could not determine MIME type");
            }
            File tempFile = ImageEditingManager.access$300(this.mContext, mimeType);
            ImageEditingManager.access$400(cropped, mimeType, tempFile);
            if (mimeType.equals("image/jpeg")) {
                ImageEditingManager.access$500(this.mContext, Uri.parse(this.mUri), tempFile);
            }
            this.mSuccess.invoke(Uri.fromFile(tempFile).toString());
        } catch (Exception e) {
            this.mError.invoke(e.getMessage());
        }
    }

    private Bitmap crop(Options outOptions) throws IOException {
        InputStream inputStream = openBitmapInputStream();
        try {
            Bitmap fullResolutionBitmap = BitmapFactory.decodeStream(inputStream, null, outOptions);
            if (fullResolutionBitmap == null) {
                throw new IOException("Cannot decode bitmap: " + this.mUri);
            }
            Bitmap createBitmap = Bitmap.createBitmap(fullResolutionBitmap, this.mX, this.mY, this.mWidth, this.mHeight);
            return createBitmap;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private Bitmap cropAndResize(int targetWidth, int targetHeight, Options outOptions) throws IOException {
        Assertions.assertNotNull(outOptions);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        InputStream inputStream = openBitmapInputStream();
        try {
            float newWidth;
            float newHeight;
            float newX;
            float newY;
            float scale;
            BitmapFactory.decodeStream(inputStream, null, options);
            float targetRatio = ((float) targetWidth) / ((float) targetHeight);
            if (((float) this.mWidth) / ((float) this.mHeight) > targetRatio) {
                newWidth = ((float) this.mHeight) * targetRatio;
                newHeight = (float) this.mHeight;
                newX = ((float) this.mX) + ((((float) this.mWidth) - newWidth) / 2.0f);
                newY = (float) this.mY;
                scale = ((float) targetHeight) / ((float) this.mHeight);
            } else {
                newWidth = (float) this.mWidth;
                newHeight = ((float) this.mWidth) / targetRatio;
                newX = (float) this.mX;
                newY = ((float) this.mY) + ((((float) this.mHeight) - newHeight) / 2.0f);
                scale = ((float) targetWidth) / ((float) this.mWidth);
            }
            outOptions.inSampleSize = ImageEditingManager.access$600(this.mWidth, this.mHeight, targetWidth, targetHeight);
            options.inJustDecodeBounds = false;
            inputStream = openBitmapInputStream();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, outOptions);
                if (bitmap == null) {
                    throw new IOException("Cannot decode bitmap: " + this.mUri);
                }
                int cropX = (int) Math.floor((double) (newX / ((float) outOptions.inSampleSize)));
                int cropY = (int) Math.floor((double) (newY / ((float) outOptions.inSampleSize)));
                int cropWidth = (int) Math.floor((double) (newWidth / ((float) outOptions.inSampleSize)));
                int cropHeight = (int) Math.floor((double) (newHeight / ((float) outOptions.inSampleSize)));
                float cropScale = scale * ((float) outOptions.inSampleSize);
                Matrix scaleMatrix = new Matrix();
                scaleMatrix.setScale(cropScale, cropScale);
                return Bitmap.createBitmap(bitmap, cropX, cropY, cropWidth, cropHeight, scaleMatrix, true);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
