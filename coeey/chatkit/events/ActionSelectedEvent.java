package chatkit.events;

import chatkit.Action;

public class ActionSelectedEvent {
    private final Action action;

    public Action getAction() {
        return this.action;
    }

    public ActionSelectedEvent(Action action) {
        this.action = action;
    }
}
