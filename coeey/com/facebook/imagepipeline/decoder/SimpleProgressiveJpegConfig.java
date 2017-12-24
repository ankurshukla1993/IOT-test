package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import java.util.Collections;
import java.util.List;

public class SimpleProgressiveJpegConfig implements ProgressiveJpegConfig {
    private final DynamicValueConfig mDynamicValueConfig;

    public interface DynamicValueConfig {
        int getGoodEnoughScanNumber();

        List<Integer> getScansToDecode();
    }

    private static class DefaultDynamicValueConfig implements DynamicValueConfig {
        private DefaultDynamicValueConfig() {
        }

        public List<Integer> getScansToDecode() {
            return Collections.EMPTY_LIST;
        }

        public int getGoodEnoughScanNumber() {
            return 0;
        }
    }

    public SimpleProgressiveJpegConfig() {
        this(new DefaultDynamicValueConfig());
    }

    public SimpleProgressiveJpegConfig(DynamicValueConfig dynamicValueConfig) {
        this.mDynamicValueConfig = (DynamicValueConfig) Preconditions.checkNotNull(dynamicValueConfig);
    }

    public int getNextScanNumberToDecode(int scanNumber) {
        List<Integer> scansToDecode = this.mDynamicValueConfig.getScansToDecode();
        if (scansToDecode == null || scansToDecode.isEmpty()) {
            return scanNumber + 1;
        }
        for (int i = 0; i < scansToDecode.size(); i++) {
            if (((Integer) scansToDecode.get(i)).intValue() > scanNumber) {
                return ((Integer) scansToDecode.get(i)).intValue();
            }
        }
        return Integer.MAX_VALUE;
    }

    public QualityInfo getQualityInfo(int scanNumber) {
        boolean z;
        if (scanNumber >= this.mDynamicValueConfig.getGoodEnoughScanNumber()) {
            z = true;
        } else {
            z = false;
        }
        return ImmutableQualityInfo.of(scanNumber, z, false);
    }
}
