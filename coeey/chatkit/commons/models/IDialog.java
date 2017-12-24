package chatkit.commons.models;

import java.util.List;

public interface IDialog {
    String getDialogName();

    String getDialogPhoto();

    String getId();

    IMessage getLastMessage();

    int getUnreadCount();

    List<IUser> getUsers();

    void setLastMessage(IMessage iMessage);
}
