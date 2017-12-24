package chatkit.commons.models;

import chatkit.Content;
import java.util.Date;
import java.util.List;

public interface IMessage {
    List<Content> getContents();

    Date getCreatedAt();

    String getId();

    String getText();

    IUser getUser();
}
