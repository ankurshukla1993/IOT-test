package dagger.android;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.android.AndroidInjector.Factory;
import dagger.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

public final class DispatchingAndroidInjector<T> implements AndroidInjector<T> {
    private static final String NO_SUPERTYPES_BOUND_FORMAT = "No injector factory bound for Class<%s>";
    private static final String SUPERTYPES_BOUND_FORMAT = "No injector factory bound for Class<%1$s>. Injector factories were bound for supertypes of %1$s: %2$s. Did you mean to bind an injector factory for the subtype?";
    private final Map<Class<? extends T>, Provider<Factory<? extends T>>> injectorFactories;

    @Inject
    DispatchingAndroidInjector(Map<Class<? extends T>, Provider<Factory<? extends T>>> injectorFactories) {
        this.injectorFactories = injectorFactories;
    }

    @CanIgnoreReturnValue
    public boolean maybeInject(T instance) {
        Provider<Factory<? extends T>> factoryProvider = (Provider) this.injectorFactories.get(instance.getClass());
        if (factoryProvider == null) {
            return false;
        }
        Factory<T> factory = (Factory) factoryProvider.get();
        try {
            ((AndroidInjector) Preconditions.checkNotNull(factory.create(instance), "%s.create(I) should not return null.", factory.getClass().getCanonicalName())).inject(instance);
            return true;
        } catch (ClassCastException e) {
            throw new InvalidInjectorBindingException(String.format("%s does not implement AndroidInjector.Factory<%s>", new Object[]{factory.getClass().getCanonicalName(), instance.getClass().getCanonicalName()}), e);
        }
    }

    public void inject(T instance) {
        if (!maybeInject(instance)) {
            throw new IllegalArgumentException(errorMessageSuggestions(instance));
        }
    }

    private String errorMessageSuggestions(T instance) {
        List<String> suggestions = new ArrayList();
        for (Class<? extends T> activityClass : this.injectorFactories.keySet()) {
            if (activityClass.isInstance(instance)) {
                suggestions.add(activityClass.getCanonicalName());
            }
        }
        Collections.sort(suggestions);
        return String.format(suggestions.isEmpty() ? NO_SUPERTYPES_BOUND_FORMAT : SUPERTYPES_BOUND_FORMAT, new Object[]{instance.getClass().getCanonicalName(), suggestions});
    }
}
