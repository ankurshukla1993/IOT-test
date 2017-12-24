package org.java_websocket.drafts;

class Draft_10$IncompleteException extends Throwable {
    private static final long serialVersionUID = 7330519489840500997L;
    private int preferedsize;
    final /* synthetic */ Draft_10 this$0;

    public Draft_10$IncompleteException(Draft_10 draft_10, int preferedsize) {
        this.this$0 = draft_10;
        this.preferedsize = preferedsize;
    }

    public int getPreferedSize() {
        return this.preferedsize;
    }
}
