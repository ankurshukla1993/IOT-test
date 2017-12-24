package chatkit;

import chatkit.commons.models.IMessage;
import chatkit.commons.models.IUser;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Message implements IMessage {
    private List<Content> contents;
    private String sender;
    private String text;

    class C05181 implements IUser {
        C05181() {
        }

        public String getId() {
            if (Message.this.sender == null) {
                return "maya";
            }
            return Message.this.sender;
        }

        public String getName() {
            return "Maya";
        }

        public String getAvatar() {
            return "https://api.adorable.io/avatars/239/smile%40adorable.io";
        }
    }

    public List<Content> getContents() {
        return this.contents;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message(String sender, String text) {
        this.text = text;
        this.sender = sender;
    }

    public Message(String sender) {
        this.sender = sender;
    }

    public String getId() {
        return UUID.randomUUID().toString();
    }

    public String getText() {
        return this.text;
    }

    public IUser getUser() {
        return new C05181();
    }

    public Date getCreatedAt() {
        return Calendar.getInstance().getTime();
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
