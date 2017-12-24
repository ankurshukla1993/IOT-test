package com.cooey.maya;

import com.google.gson.GsonBuilder;

class ContentManager$1 implements Runnable {
    final /* synthetic */ ContentManager this$0;
    final /* synthetic */ MayaRequest val$mayaRequest;

    ContentManager$1(ContentManager this$0, MayaRequest mayaRequest) {
        this.this$0 = this$0;
        this.val$mayaRequest = mayaRequest;
    }

    public void run() {
        try {
            if (ContentManager.contextId != null) {
                this.val$mayaRequest.setContextId(ContentManager.contextId);
            }
            ContentManager.access$000(this.this$0).getSession().getBasicRemote().sendText(new GsonBuilder().create().toJson(this.val$mayaRequest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
