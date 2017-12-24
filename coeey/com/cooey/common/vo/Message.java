package com.cooey.common.vo;

import io.realm.MessageRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class Message extends RealmObject implements MessageRealmProxyInterface {
    private String from;
    @PrimaryKey
    private String id;
    private String message;
    private int messageType;
    private String parameters;
    private long timestamp;

    public String realmGet$from() {
        return this.from;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$message() {
        return this.message;
    }

    public int realmGet$messageType() {
        return this.messageType;
    }

    public String realmGet$parameters() {
        return this.parameters;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public void realmSet$from(String str) {
        this.from = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$message(String str) {
        this.message = str;
    }

    public void realmSet$messageType(int i) {
        this.messageType = i;
    }

    public void realmSet$parameters(String str) {
        this.parameters = str;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public Message() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getParameters() {
        return realmGet$parameters();
    }

    public void setParameters(String parameters) {
        realmSet$parameters(parameters);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String id) {
        realmSet$id(id);
    }

    public String getMessage() {
        return realmGet$message();
    }

    public void setMessage(String message) {
        realmSet$message(message);
    }

    public String getFrom() {
        return realmGet$from();
    }

    public void setFrom(String from) {
        realmSet$from(from);
    }

    public int getMessageType() {
        return realmGet$messageType();
    }

    public void setMessageType(int messageType) {
        realmSet$messageType(messageType);
    }

    public long getTimestamp() {
        return realmGet$timestamp();
    }

    public void setTimestamp(long timestamp) {
        realmSet$timestamp(timestamp);
    }
}
