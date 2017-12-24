package com.facebook.react.devsupport;

import com.facebook.react.bridge.Inspector;
import com.facebook.react.bridge.Inspector.Page;
import java.util.List;

class DevSupportManagerImpl$7 implements DevOptionHandler {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$7(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onOptionSelected() {
        List<Page> pages = Inspector.getPages();
        if (pages.size() > 0) {
            DevSupportManagerImpl.access$100(this.this$0).openInspector(String.valueOf(((Page) pages.get(0)).getId()));
        }
    }
}
