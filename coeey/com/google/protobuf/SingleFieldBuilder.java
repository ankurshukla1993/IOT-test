package com.google.protobuf;

import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.GeneratedMessage.BuilderParent;

public class SingleFieldBuilder implements BuilderParent {
    private Builder builder;
    private boolean isClean;
    private GeneratedMessage message;
    private BuilderParent parent;

    public SingleFieldBuilder(GeneratedMessage generatedMessage, BuilderParent builderParent, boolean z) {
        if (generatedMessage == null) {
            throw new NullPointerException();
        }
        this.message = generatedMessage;
        this.parent = builderParent;
        this.isClean = z;
    }

    private void onChanged() {
        if (this.builder != null) {
            this.message = null;
        }
        if (this.isClean && this.parent != null) {
            this.parent.markDirty();
            this.isClean = false;
        }
    }

    public GeneratedMessage build() {
        this.isClean = true;
        return getMessage();
    }

    public SingleFieldBuilder clear() {
        this.message = (GeneratedMessage) (this.message != null ? this.message.getDefaultInstanceForType() : this.builder.getDefaultInstanceForType());
        if (this.builder != null) {
            this.builder.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }

    public void dispose() {
        this.parent = null;
    }

    public Builder getBuilder() {
        if (this.builder == null) {
            this.builder = (Builder) this.message.newBuilderForType(this);
            this.builder.mergeFrom(this.message);
            this.builder.markClean();
        }
        return this.builder;
    }

    public GeneratedMessage getMessage() {
        if (this.message == null) {
            this.message = (GeneratedMessage) this.builder.buildPartial();
        }
        return this.message;
    }

    public MessageOrBuilder getMessageOrBuilder() {
        return this.builder != null ? this.builder : this.message;
    }

    public void markDirty() {
        onChanged();
    }

    public SingleFieldBuilder mergeFrom(GeneratedMessage generatedMessage) {
        if (this.builder == null && this.message == this.message.getDefaultInstanceForType()) {
            this.message = generatedMessage;
        } else {
            getBuilder().mergeFrom((Message) generatedMessage);
        }
        onChanged();
        return this;
    }

    public SingleFieldBuilder setMessage(GeneratedMessage generatedMessage) {
        if (generatedMessage == null) {
            throw new NullPointerException();
        }
        this.message = generatedMessage;
        if (this.builder != null) {
            this.builder.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }
}
