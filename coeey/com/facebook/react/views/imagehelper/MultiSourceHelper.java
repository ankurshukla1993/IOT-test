package com.facebook.react.views.imagehelper;

import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import java.util.List;

public class MultiSourceHelper {
    public static MultiSourceResult getBestSourceForSize(int width, int height, List<ImageSource> sources) {
        return getBestSourceForSize(width, height, sources, 1.0d);
    }

    public static MultiSourceResult getBestSourceForSize(int width, int height, List<ImageSource> sources, double multiplier) {
        if (sources.isEmpty()) {
            return new MultiSourceResult(null, null, null);
        }
        if (sources.size() == 1) {
            return new MultiSourceResult((ImageSource) sources.get(0), null, null);
        }
        if (width <= 0 || height <= 0) {
            return new MultiSourceResult(null, null, null);
        }
        ImagePipeline imagePipeline = ImagePipelineFactory.getInstance().getImagePipeline();
        ImageSource best = null;
        ImageSource bestCached = null;
        double viewArea = ((double) (width * height)) * multiplier;
        double bestPrecision = Double.MAX_VALUE;
        double bestCachePrecision = Double.MAX_VALUE;
        for (ImageSource source : sources) {
            double precision = Math.abs(1.0d - (source.getSize() / viewArea));
            if (precision < bestPrecision) {
                bestPrecision = precision;
                best = source;
            }
            if (precision < bestCachePrecision && (imagePipeline.isInBitmapMemoryCache(source.getUri()) || imagePipeline.isInDiskCacheSync(source.getUri()))) {
                bestCachePrecision = precision;
                bestCached = source;
            }
        }
        if (!(bestCached == null || best == null || !bestCached.getSource().equals(best.getSource()))) {
            bestCached = null;
        }
        return new MultiSourceResult(best, bestCached, null);
    }
}
