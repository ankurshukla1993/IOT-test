package chatkit.events;

import chatkit.Content;

public class OptionSelectedEvent {
    private final Content content;

    public OptionSelectedEvent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return this.content;
    }
}
