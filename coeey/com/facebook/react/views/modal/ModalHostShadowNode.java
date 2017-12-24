package com.facebook.react.views.modal;

import android.graphics.Point;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNode;

class ModalHostShadowNode extends LayoutShadowNode {
    ModalHostShadowNode() {
    }

    public void addChildAt(ReactShadowNode child, int i) {
        super.addChildAt(child, i);
        Point modalSize = ModalHostHelper.getModalHostSize(getThemedContext());
        child.setStyleWidth((float) modalSize.x);
        child.setStyleHeight((float) modalSize.y);
    }
}
