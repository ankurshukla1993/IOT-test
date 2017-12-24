package com.facebook.imagepipeline.backends.okhttp3;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher$Callback;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

public class OkHttpNetworkFetcher extends BaseNetworkFetcher<OkHttpNetworkFetchState> {
    private static final String FETCH_TIME = "fetch_time";
    private static final String IMAGE_SIZE = "image_size";
    private static final String QUEUE_TIME = "queue_time";
    private static final String TAG = "OkHttpNetworkFetchProducer";
    private static final String TOTAL_TIME = "total_time";
    private Executor mCancellationExecutor;
    private final OkHttpClient mOkHttpClient;

    public static class OkHttpNetworkFetchState extends FetchState {
        public long fetchCompleteTime;
        public long responseTime;
        public long submitTime;

        public OkHttpNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public OkHttpNetworkFetcher(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
        this.mCancellationExecutor = okHttpClient.dispatcher().executorService();
    }

    public OkHttpNetworkFetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext context) {
        return new OkHttpNetworkFetchState(consumer, context);
    }

    public void fetch(final OkHttpNetworkFetchState fetchState, final NetworkFetcher$Callback callback) {
        fetchState.submitTime = SystemClock.elapsedRealtime();
        final Call call = this.mOkHttpClient.newCall(new Builder().cacheControl(new CacheControl.Builder().noStore().build()).url(fetchState.getUri().toString()).get().build());
        fetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() {

            class C11421 implements Runnable {
                C11421() {
                }

                public void run() {
                    call.cancel();
                }
            }

            public void onCancellationRequested() {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    call.cancel();
                } else {
                    OkHttpNetworkFetcher.this.mCancellationExecutor.execute(new C11421());
                }
            }
        });
        call.enqueue(new Callback() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onResponse(okhttp3.Call r9, okhttp3.Response r10) throws java.io.IOException {
                /*
                r8 = this;
                r4 = r7;
                r6 = android.os.SystemClock.elapsedRealtime();
                r4.responseTime = r6;
                r0 = r10.body();
                r4 = r10.isSuccessful();	 Catch:{ Exception -> 0x0061 }
                if (r4 != 0) goto L_0x003e;
            L_0x0012:
                r4 = com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.this;	 Catch:{ Exception -> 0x0061 }
                r5 = new java.io.IOException;	 Catch:{ Exception -> 0x0061 }
                r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0061 }
                r6.<init>();	 Catch:{ Exception -> 0x0061 }
                r7 = "Unexpected HTTP code ";
                r6 = r6.append(r7);	 Catch:{ Exception -> 0x0061 }
                r6 = r6.append(r10);	 Catch:{ Exception -> 0x0061 }
                r6 = r6.toString();	 Catch:{ Exception -> 0x0061 }
                r5.<init>(r6);	 Catch:{ Exception -> 0x0061 }
                r6 = r8;	 Catch:{ Exception -> 0x0061 }
                r4.handleException(r9, r5, r6);	 Catch:{ Exception -> 0x0061 }
                r0.close();	 Catch:{ Exception -> 0x0035 }
            L_0x0034:
                return;
            L_0x0035:
                r1 = move-exception;
                r4 = "OkHttpNetworkFetchProducer";
                r5 = "Exception when closing response body";
                com.facebook.common.logging.FLog.m152w(r4, r5, r1);
                goto L_0x0034;
            L_0x003e:
                r2 = r0.contentLength();	 Catch:{ Exception -> 0x0061 }
                r4 = 0;
                r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                if (r4 >= 0) goto L_0x004a;
            L_0x0048:
                r2 = 0;
            L_0x004a:
                r4 = r8;	 Catch:{ Exception -> 0x0061 }
                r5 = r0.byteStream();	 Catch:{ Exception -> 0x0061 }
                r6 = (int) r2;	 Catch:{ Exception -> 0x0061 }
                r4.onResponse(r5, r6);	 Catch:{ Exception -> 0x0061 }
                r0.close();	 Catch:{ Exception -> 0x0058 }
                goto L_0x0034;
            L_0x0058:
                r1 = move-exception;
                r4 = "OkHttpNetworkFetchProducer";
                r5 = "Exception when closing response body";
                com.facebook.common.logging.FLog.m152w(r4, r5, r1);
                goto L_0x0034;
            L_0x0061:
                r1 = move-exception;
                r4 = com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.this;	 Catch:{ all -> 0x0076 }
                r5 = r8;	 Catch:{ all -> 0x0076 }
                r4.handleException(r9, r1, r5);	 Catch:{ all -> 0x0076 }
                r0.close();	 Catch:{ Exception -> 0x006d }
                goto L_0x0034;
            L_0x006d:
                r1 = move-exception;
                r4 = "OkHttpNetworkFetchProducer";
                r5 = "Exception when closing response body";
                com.facebook.common.logging.FLog.m152w(r4, r5, r1);
                goto L_0x0034;
            L_0x0076:
                r4 = move-exception;
                r0.close();	 Catch:{ Exception -> 0x007b }
            L_0x007a:
                throw r4;
            L_0x007b:
                r1 = move-exception;
                r5 = "OkHttpNetworkFetchProducer";
                r6 = "Exception when closing response body";
                com.facebook.common.logging.FLog.m152w(r5, r6, r1);
                goto L_0x007a;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.2.onResponse(okhttp3.Call, okhttp3.Response):void");
            }

            public void onFailure(Call call, IOException e) {
                OkHttpNetworkFetcher.this.handleException(call, e, callback);
            }
        });
    }

    public void onFetchCompletion(OkHttpNetworkFetchState fetchState, int byteSize) {
        fetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
    }

    public Map<String, String> getExtraMap(OkHttpNetworkFetchState fetchState, int byteSize) {
        Map<String, String> extraMap = new HashMap(4);
        extraMap.put(QUEUE_TIME, Long.toString(fetchState.responseTime - fetchState.submitTime));
        extraMap.put(FETCH_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.responseTime));
        extraMap.put(TOTAL_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.submitTime));
        extraMap.put(IMAGE_SIZE, Integer.toString(byteSize));
        return extraMap;
    }

    private void handleException(Call call, Exception e, NetworkFetcher$Callback callback) {
        if (call.isCanceled()) {
            callback.onCancellation();
        } else {
            callback.onFailure(e);
        }
    }
}
