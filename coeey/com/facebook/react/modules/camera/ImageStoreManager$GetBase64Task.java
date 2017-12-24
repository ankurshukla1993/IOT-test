package com.facebook.react.modules.camera;

import android.net.Uri;
import android.util.Base64OutputStream;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class ImageStoreManager$GetBase64Task extends GuardedAsyncTask<Void, Void> {
    private final Callback mError;
    private final Callback mSuccess;
    private final String mUri;
    final /* synthetic */ ImageStoreManager this$0;

    private ImageStoreManager$GetBase64Task(ImageStoreManager imageStoreManager, ReactContext reactContext, String uri, Callback success, Callback error) {
        this.this$0 = imageStoreManager;
        super(reactContext);
        this.mUri = uri;
        this.mSuccess = success;
        this.mError = error;
    }

    protected void doInBackgroundGuarded(Void... params) {
        try {
            InputStream is = ImageStoreManager.access$100(this.this$0).getContentResolver().openInputStream(Uri.parse(this.mUri));
            Base64OutputStream b64os = new Base64OutputStream(new ByteArrayOutputStream(), 0);
            byte[] buffer = new byte[8192];
            while (true) {
                try {
                    int bytesRead = is.read(buffer);
                    if (bytesRead <= -1) {
                        break;
                    }
                    b64os.write(buffer, 0, bytesRead);
                } catch (IOException e) {
                    this.mError.invoke(e.getMessage());
                    return;
                } finally {
                    ImageStoreManager.access$200(is);
                    ImageStoreManager.access$200(b64os);
                }
            }
            this.mSuccess.invoke(baos.toString());
        } catch (FileNotFoundException e2) {
            this.mError.invoke(e2.getMessage());
        }
    }
}
