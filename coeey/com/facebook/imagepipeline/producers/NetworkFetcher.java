package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import java.util.Map;
import javax.annotation.Nullable;

public interface NetworkFetcher<FETCH_STATE extends FetchState> {
    FETCH_STATE createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext);

    void fetch(FETCH_STATE fetch_state, Callback callback);

    @Nullable
    Map<String, String> getExtraMap(FETCH_STATE fetch_state, int i);

    void onFetchCompletion(FETCH_STATE fetch_state, int i);

    boolean shouldPropagate(FETCH_STATE fetch_state);
}
