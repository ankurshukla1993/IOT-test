package com.facebook.drawee.controller;

import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.share.internal.ShareConstants;

class AbstractDraweeControllerBuilder$2 implements Supplier<DataSource<IMAGE>> {
    final /* synthetic */ AbstractDraweeControllerBuilder this$0;
    final /* synthetic */ boolean val$bitmapCacheOnly;
    final /* synthetic */ Object val$callerContext;
    final /* synthetic */ Object val$imageRequest;

    AbstractDraweeControllerBuilder$2(AbstractDraweeControllerBuilder this$0, Object obj, Object obj2, boolean z) {
        this.this$0 = this$0;
        this.val$imageRequest = obj;
        this.val$callerContext = obj2;
        this.val$bitmapCacheOnly = z;
    }

    public DataSource<IMAGE> get() {
        return this.this$0.getDataSourceForRequest(this.val$imageRequest, this.val$callerContext, this.val$bitmapCacheOnly);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, this.val$imageRequest.toString()).toString();
    }
}
