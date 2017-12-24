package com.google.protobuf;

import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RepeatedFieldBuilder implements BuilderParent {
    private List builders;
    private BuilderExternalList externalBuilderList;
    private MessageExternalList externalMessageList;
    private MessageOrBuilderExternalList externalMessageOrBuilderList;
    private boolean isClean;
    private boolean isMessagesListMutable;
    private List messages;
    private BuilderParent parent;

    class BuilderExternalList extends AbstractList implements List {
        RepeatedFieldBuilder builder;

        BuilderExternalList(RepeatedFieldBuilder repeatedFieldBuilder) {
            this.builder = repeatedFieldBuilder;
        }

        public Builder get(int i) {
            return this.builder.getBuilder(i);
        }

        void incrementModCount() {
            this.modCount++;
        }

        public int size() {
            return this.builder.getCount();
        }
    }

    class MessageExternalList extends AbstractList implements List {
        RepeatedFieldBuilder builder;

        MessageExternalList(RepeatedFieldBuilder repeatedFieldBuilder) {
            this.builder = repeatedFieldBuilder;
        }

        public GeneratedMessage get(int i) {
            return this.builder.getMessage(i);
        }

        void incrementModCount() {
            this.modCount++;
        }

        public int size() {
            return this.builder.getCount();
        }
    }

    class MessageOrBuilderExternalList extends AbstractList implements List {
        RepeatedFieldBuilder builder;

        MessageOrBuilderExternalList(RepeatedFieldBuilder repeatedFieldBuilder) {
            this.builder = repeatedFieldBuilder;
        }

        public MessageOrBuilder get(int i) {
            return this.builder.getMessageOrBuilder(i);
        }

        void incrementModCount() {
            this.modCount++;
        }

        public int size() {
            return this.builder.getCount();
        }
    }

    public RepeatedFieldBuilder(List list, boolean z, BuilderParent builderParent, boolean z2) {
        this.messages = list;
        this.isMessagesListMutable = z;
        this.parent = builderParent;
        this.isClean = z2;
    }

    private void ensureBuilders() {
        if (this.builders == null) {
            this.builders = new ArrayList(this.messages.size());
            for (int i = 0; i < this.messages.size(); i++) {
                this.builders.add(null);
            }
        }
    }

    private void ensureMutableMessageList() {
        if (!this.isMessagesListMutable) {
            this.messages = new ArrayList(this.messages);
            this.isMessagesListMutable = true;
        }
    }

    private GeneratedMessage getMessage(int i, boolean z) {
        if (this.builders == null) {
            return (GeneratedMessage) this.messages.get(i);
        }
        SingleFieldBuilder singleFieldBuilder = (SingleFieldBuilder) this.builders.get(i);
        return singleFieldBuilder == null ? (GeneratedMessage) this.messages.get(i) : z ? singleFieldBuilder.build() : singleFieldBuilder.getMessage();
    }

    private void incrementModCounts() {
        if (this.externalMessageList != null) {
            this.externalMessageList.incrementModCount();
        }
        if (this.externalBuilderList != null) {
            this.externalBuilderList.incrementModCount();
        }
        if (this.externalMessageOrBuilderList != null) {
            this.externalMessageOrBuilderList.incrementModCount();
        }
    }

    private void onChanged() {
        if (this.isClean && this.parent != null) {
            this.parent.markDirty();
            this.isClean = false;
        }
    }

    public RepeatedFieldBuilder addAllMessages(Iterable iterable) {
        for (GeneratedMessage generatedMessage : iterable) {
            if (generatedMessage == null) {
                throw new NullPointerException();
            }
        }
        if (!(iterable instanceof Collection)) {
            ensureMutableMessageList();
            for (GeneratedMessage generatedMessage2 : iterable) {
                addMessage(generatedMessage2);
            }
            onChanged();
            incrementModCounts();
        } else if (((Collection) iterable).size() != 0) {
            ensureMutableMessageList();
            for (GeneratedMessage generatedMessage22 : iterable) {
                addMessage(generatedMessage22);
            }
            onChanged();
            incrementModCounts();
        }
        return this;
    }

    public Builder addBuilder(int i, GeneratedMessage generatedMessage) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilder singleFieldBuilder = new SingleFieldBuilder(generatedMessage, this, this.isClean);
        this.messages.add(i, null);
        this.builders.add(i, singleFieldBuilder);
        onChanged();
        incrementModCounts();
        return singleFieldBuilder.getBuilder();
    }

    public Builder addBuilder(GeneratedMessage generatedMessage) {
        ensureMutableMessageList();
        ensureBuilders();
        SingleFieldBuilder singleFieldBuilder = new SingleFieldBuilder(generatedMessage, this, this.isClean);
        this.messages.add(null);
        this.builders.add(singleFieldBuilder);
        onChanged();
        incrementModCounts();
        return singleFieldBuilder.getBuilder();
    }

    public RepeatedFieldBuilder addMessage(int i, GeneratedMessage generatedMessage) {
        if (generatedMessage == null) {
            throw new NullPointerException();
        }
        ensureMutableMessageList();
        this.messages.add(i, generatedMessage);
        if (this.builders != null) {
            this.builders.add(i, null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public RepeatedFieldBuilder addMessage(GeneratedMessage generatedMessage) {
        if (generatedMessage == null) {
            throw new NullPointerException();
        }
        ensureMutableMessageList();
        this.messages.add(generatedMessage);
        if (this.builders != null) {
            this.builders.add(null);
        }
        onChanged();
        incrementModCounts();
        return this;
    }

    public List build() {
        this.isClean = true;
        if (!this.isMessagesListMutable && this.builders == null) {
            return this.messages;
        }
        if (!this.isMessagesListMutable) {
            boolean z;
            for (int i = 0; i < this.messages.size(); i++) {
                Object obj = (Message) this.messages.get(i);
                SingleFieldBuilder singleFieldBuilder = (SingleFieldBuilder) this.builders.get(i);
                if (singleFieldBuilder != null && singleFieldBuilder.build() != obj) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return this.messages;
            }
        }
        ensureMutableMessageList();
        for (int i2 = 0; i2 < this.messages.size(); i2++) {
            this.messages.set(i2, getMessage(i2, true));
        }
        this.messages = Collections.unmodifiableList(this.messages);
        this.isMessagesListMutable = false;
        return this.messages;
    }

    public void clear() {
        this.messages = Collections.emptyList();
        this.isMessagesListMutable = false;
        if (this.builders != null) {
            for (SingleFieldBuilder singleFieldBuilder : this.builders) {
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.dispose();
                }
            }
            this.builders = null;
        }
        onChanged();
        incrementModCounts();
    }

    public void dispose() {
        this.parent = null;
    }

    public Builder getBuilder(int i) {
        ensureBuilders();
        SingleFieldBuilder singleFieldBuilder = (SingleFieldBuilder) this.builders.get(i);
        if (singleFieldBuilder == null) {
            SingleFieldBuilder singleFieldBuilder2 = new SingleFieldBuilder((GeneratedMessage) this.messages.get(i), this, this.isClean);
            this.builders.set(i, singleFieldBuilder2);
            singleFieldBuilder = singleFieldBuilder2;
        }
        return singleFieldBuilder.getBuilder();
    }

    public List getBuilderList() {
        if (this.externalBuilderList == null) {
            this.externalBuilderList = new BuilderExternalList(this);
        }
        return this.externalBuilderList;
    }

    public int getCount() {
        return this.messages.size();
    }

    public GeneratedMessage getMessage(int i) {
        return getMessage(i, false);
    }

    public List getMessageList() {
        if (this.externalMessageList == null) {
            this.externalMessageList = new MessageExternalList(this);
        }
        return this.externalMessageList;
    }

    public MessageOrBuilder getMessageOrBuilder(int i) {
        if (this.builders == null) {
            return (MessageOrBuilder) this.messages.get(i);
        }
        SingleFieldBuilder singleFieldBuilder = (SingleFieldBuilder) this.builders.get(i);
        return singleFieldBuilder == null ? (MessageOrBuilder) this.messages.get(i) : singleFieldBuilder.getMessageOrBuilder();
    }

    public List getMessageOrBuilderList() {
        if (this.externalMessageOrBuilderList == null) {
            this.externalMessageOrBuilderList = new MessageOrBuilderExternalList(this);
        }
        return this.externalMessageOrBuilderList;
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    public void markDirty() {
        onChanged();
    }

    public void remove(int i) {
        ensureMutableMessageList();
        this.messages.remove(i);
        if (this.builders != null) {
            SingleFieldBuilder singleFieldBuilder = (SingleFieldBuilder) this.builders.remove(i);
            if (singleFieldBuilder != null) {
                singleFieldBuilder.dispose();
            }
        }
        onChanged();
        incrementModCounts();
    }

    public RepeatedFieldBuilder setMessage(int i, GeneratedMessage generatedMessage) {
        if (generatedMessage == null) {
            throw new NullPointerException();
        }
        ensureMutableMessageList();
        this.messages.set(i, generatedMessage);
        if (this.builders != null) {
            SingleFieldBuilder singleFieldBuilder = (SingleFieldBuilder) this.builders.set(i, null);
            if (singleFieldBuilder != null) {
                singleFieldBuilder.dispose();
            }
        }
        onChanged();
        incrementModCounts();
        return this;
    }
}
