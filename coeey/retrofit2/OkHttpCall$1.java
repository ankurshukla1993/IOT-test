package retrofit2;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

class OkHttpCall$1 implements Callback {
    final /* synthetic */ OkHttpCall this$0;
    final /* synthetic */ Callback val$callback;

    OkHttpCall$1(OkHttpCall this$0, Callback callback) {
        this.this$0 = this$0;
        this.val$callback = callback;
    }

    public void onResponse(Call call, Response rawResponse) throws IOException {
        try {
            callSuccess(this.this$0.parseResponse(rawResponse));
        } catch (Throwable e) {
            callFailure(e);
        }
    }

    public void onFailure(Call call, IOException e) {
        try {
            this.val$callback.onFailure(this.this$0, e);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void callFailure(Throwable e) {
        try {
            this.val$callback.onFailure(this.this$0, e);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void callSuccess(Response<T> response) {
        try {
            this.val$callback.onResponse(this.this$0, response);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
