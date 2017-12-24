package com.facebook.react.bridge;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class JavaScriptModuleRegistration {
    private final Class<? extends JavaScriptModule> mModuleInterface;
    @Nullable
    private String mName;

    public JavaScriptModuleRegistration(Class<? extends JavaScriptModule> moduleInterface) {
        this.mModuleInterface = moduleInterface;
    }

    public Class<? extends JavaScriptModule> getModuleInterface() {
        return this.mModuleInterface;
    }

    public String getName() {
        if (this.mName == null) {
            String name = this.mModuleInterface.getSimpleName();
            int dollarSignIndex = name.lastIndexOf(36);
            if (dollarSignIndex != -1) {
                name = name.substring(dollarSignIndex + 1);
            }
            this.mName = name;
        }
        return this.mName;
    }

    public List<Method> getMethods() {
        return Arrays.asList(this.mModuleInterface.getDeclaredMethods());
    }
}
