package retrofit2;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@IgnoreJRERequirement
class Platform$Java8 extends Platform {
    Platform$Java8() {
    }

    boolean isDefaultMethod(Method method) {
        return method.isDefault();
    }

    Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, @Nullable Object... args) throws Throwable {
        Constructor<Lookup> constructor = Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
        constructor.setAccessible(true);
        return ((Lookup) constructor.newInstance(new Object[]{declaringClass, Integer.valueOf(-1)})).unreflectSpecial(method, declaringClass).bindTo(object).invokeWithArguments(args);
    }
}
