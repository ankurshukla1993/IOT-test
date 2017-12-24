package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.List;

public abstract class BaseListBitmapDataSubscriber extends BaseDataSubscriber<List<CloseableReference<CloseableImage>>> {
    public void onNewResultImpl(com.facebook.datasource.DataSource<java.util.List<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>>> r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Incorrect nodes count for selectOther: B:30:0x0076 in [B:19:0x0050, B:30:0x0076, B:31:0x0007]
	at jadx.core.utils.BlockUtils.selectOther(BlockUtils.java:53)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:64)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r6 = this;
        r4 = 0;
        r3 = r7.isFinished();
        if (r3 != 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        r2 = r7.getResult();
        r2 = (java.util.List) r2;
        if (r2 != 0) goto L_0x0014;
    L_0x0010:
        r6.onNewResultListImpl(r4);
        goto L_0x0007;
    L_0x0014:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0045 }
        r3 = r2.size();	 Catch:{ all -> 0x0045 }
        r0.<init>(r3);	 Catch:{ all -> 0x0045 }
        r4 = r2.iterator();	 Catch:{ all -> 0x0045 }
    L_0x0021:
        r3 = r4.hasNext();	 Catch:{ all -> 0x0045 }
        if (r3 == 0) goto L_0x005f;	 Catch:{ all -> 0x0045 }
    L_0x0027:
        r1 = r4.next();	 Catch:{ all -> 0x0045 }
        r1 = (com.facebook.common.references.CloseableReference) r1;	 Catch:{ all -> 0x0045 }
        if (r1 == 0) goto L_0x005a;	 Catch:{ all -> 0x0045 }
    L_0x002f:
        r3 = r1.get();	 Catch:{ all -> 0x0045 }
        r3 = r3 instanceof com.facebook.imagepipeline.image.CloseableBitmap;	 Catch:{ all -> 0x0045 }
        if (r3 == 0) goto L_0x005a;	 Catch:{ all -> 0x0045 }
    L_0x0037:
        r3 = r1.get();	 Catch:{ all -> 0x0045 }
        r3 = (com.facebook.imagepipeline.image.CloseableBitmap) r3;	 Catch:{ all -> 0x0045 }
        r3 = r3.getUnderlyingBitmap();	 Catch:{ all -> 0x0045 }
        r0.add(r3);	 Catch:{ all -> 0x0045 }
        goto L_0x0021;
    L_0x0045:
        r3 = move-exception;
        r4 = r2.iterator();
    L_0x004a:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x0076;
    L_0x0050:
        r1 = r4.next();
        r1 = (com.facebook.common.references.CloseableReference) r1;
        com.facebook.common.references.CloseableReference.closeSafely(r1);
        goto L_0x004a;
    L_0x005a:
        r3 = 0;
        r0.add(r3);	 Catch:{ all -> 0x0045 }
        goto L_0x0021;	 Catch:{ all -> 0x0045 }
    L_0x005f:
        r6.onNewResultListImpl(r0);	 Catch:{ all -> 0x0045 }
        r3 = r2.iterator();
    L_0x0066:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0007;
    L_0x006c:
        r1 = r3.next();
        r1 = (com.facebook.common.references.CloseableReference) r1;
        com.facebook.common.references.CloseableReference.closeSafely(r1);
        goto L_0x0066;
    L_0x0076:
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.datasource.BaseListBitmapDataSubscriber.onNewResultImpl(com.facebook.datasource.DataSource):void");
    }

    protected abstract void onNewResultListImpl(List<Bitmap> list);
}
