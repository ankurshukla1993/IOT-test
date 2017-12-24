package com.facebook.drawee.controller;

class AbstractDraweeController$InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO> {
    private AbstractDraweeController$InternalForwardingListener() {
    }

    public static <INFO> AbstractDraweeController$InternalForwardingListener<INFO> createInternal(ControllerListener<? super INFO> listener1, ControllerListener<? super INFO> listener2) {
        AbstractDraweeController$InternalForwardingListener<INFO> forwarder = new AbstractDraweeController$InternalForwardingListener();
        forwarder.addListener(listener1);
        forwarder.addListener(listener2);
        return forwarder;
    }
}
