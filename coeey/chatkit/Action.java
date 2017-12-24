package chatkit;

public class Action {
    private String contentId;
    private MayaIntent intent;
    private String name;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public MayaIntent getIntent() {
        return this.intent;
    }

    public void setIntent(MayaIntent intent) {
        this.intent = intent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
