package chatkit;

public class Content {
    private String parameters;
    private ContentType type;

    public ContentType getType() {
        return this.type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
