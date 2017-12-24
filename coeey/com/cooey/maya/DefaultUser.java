package com.cooey.maya;

import chatkit.commons.models.IUser;

public class DefaultUser implements IUser {
    private String avatar;
    private String id;
    private String name;
    private boolean online;

    public DefaultUser(String id, String name, String avatar, boolean online) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isOnline() {
        return this.online;
    }
}
