package com.cooey.maya;

import android.app.Activity;
import chatkit.Message;
import chatkit.events.OptionSelectedEvent;
import com.google.gson.GsonBuilder;

class ContentManager$2 implements Runnable {
    final /* synthetic */ ContentManager this$0;
    final /* synthetic */ OptionSelectedEvent val$optionSelectedEvent;

    class C09631 implements Runnable {
        C09631() {
        }

        public void run() {
            ContentManager.access$100(ContentManager$2.this.this$0).addToStart(new Message("Maya", ContentManager$2.this.val$optionSelectedEvent.getContent().getParameters()), true);
        }
    }

    ContentManager$2(ContentManager this$0, OptionSelectedEvent optionSelectedEvent) {
        this.this$0 = this$0;
        this.val$optionSelectedEvent = optionSelectedEvent;
    }

    public void run() {
        try {
            Object mayaRequest = new MayaRequest();
            mayaRequest.setQuery(this.val$optionSelectedEvent.getContent().getParameters());
            ContentManager.access$000(this.this$0).getSession().getBasicRemote().sendText(new GsonBuilder().create().toJson(mayaRequest));
            ((Activity) ContentManager.access$200(this.this$0)).runOnUiThread(new C09631());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
