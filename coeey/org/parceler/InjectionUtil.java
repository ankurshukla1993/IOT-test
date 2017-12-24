package org.parceler;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public final class InjectionUtil {
    public static final String CALL_CONSTRUCTOR_METHOD = "callConstructor";
    public static final String CALL_METHOD_METHOD = "callMethod";
    public static final String GET_FIELD_METHOD = "getField";
    public static final String SET_FIELD_METHOD = "setField";

    private static abstract class AccessibleElementPrivilegedAction<T, E extends AccessibleObject> implements PrivilegedExceptionAction<T> {
        private final E accessible;

        public abstract T run(E e) throws Exception;

        protected AccessibleElementPrivilegedAction(E accessible) {
            this.accessible = accessible;
        }

        public T run() throws Exception {
            boolean previous = this.accessible.isAccessible();
            this.accessible.setAccessible(true);
            T output = run(this.accessible);
            this.accessible.setAccessible(previous);
            return output;
        }
    }

    private static final class GetFieldPrivilegedAction<T> extends AccessibleElementPrivilegedAction<T, Field> {
        private final Object target;

        private GetFieldPrivilegedAction(Field classField, Object target) {
            super(classField);
            this.target = target;
        }

        public T run(Field classField) throws IllegalAccessException {
            return classField.get(this.target);
        }
    }

    private static final class SetConstructorPrivilegedAction<T> extends AccessibleElementPrivilegedAction<T, Constructor> {
        private final Object[] args;

        private SetConstructorPrivilegedAction(Constructor classConstructor, Object[] args) {
            super(classConstructor);
            this.args = args;
        }

        public T run(Constructor classConstructor) throws InvocationTargetException, InstantiationException, IllegalAccessException {
            return classConstructor.newInstance(this.args);
        }
    }

    private static final class SetFieldPrivilegedAction extends AccessibleElementPrivilegedAction<Void, Field> {
        private final Object target;
        private final Object value;

        private SetFieldPrivilegedAction(Field classField, Object target, Object value) {
            super(classField);
            this.target = target;
            this.value = value;
        }

        public Void run(Field classField) throws IllegalAccessException {
            classField.set(this.target, this.value);
            return null;
        }
    }

    private static final class SetMethodPrivilegedAction<T> extends AccessibleElementPrivilegedAction<T, Method> {
        private final Object[] args;
        private final Object target;

        private SetMethodPrivilegedAction(Method classMethod, Object target, Object[] args) {
            super(classMethod);
            this.target = target;
            this.args = args;
        }

        public T run(Method classMethod) throws InvocationTargetException, IllegalAccessException {
            return classMethod.invoke(this.target, this.args);
        }
    }

    private InjectionUtil() {
    }

    public static <T> T getField(Class<T> cls, Class<?> targetClass, Object target, String field) {
        try {
            return AccessController.doPrivileged(new GetFieldPrivilegedAction(targetClass.getDeclaredField(field), target));
        } catch (NoSuchFieldException e) {
            throw new ParcelerRuntimeException("NoSuchFieldException Exception during field injection: " + field + " in " + target.getClass(), e);
        } catch (PrivilegedActionException e2) {
            throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", e2);
        } catch (Exception e3) {
            throw new ParcelerRuntimeException("Exception during field injection", e3);
        }
    }

    public static void setField(Class<?> targetClass, Object target, String field, Object value) {
        try {
            AccessController.doPrivileged(new SetFieldPrivilegedAction(targetClass.getDeclaredField(field), target, value));
        } catch (NoSuchFieldException e) {
            throw new ParcelerRuntimeException("NoSuchFieldException Exception during field injection: " + field + " in " + target.getClass(), e);
        } catch (PrivilegedActionException e2) {
            throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", e2);
        } catch (Exception e3) {
            throw new ParcelerRuntimeException("Exception during field injection", e3);
        }
    }

    public static <T> T callMethod(Class<T> cls, Class<?> targetClass, Object target, String method, Class[] argClasses, Object[] args) {
        try {
            return AccessController.doPrivileged(new SetMethodPrivilegedAction(targetClass.getDeclaredMethod(method, argClasses), target, args));
        } catch (NoSuchMethodException e) {
            throw new ParcelerRuntimeException("Exception during method injection: NoSuchFieldException", e);
        } catch (PrivilegedActionException e2) {
            throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", e2);
        } catch (Exception e3) {
            throw new ParcelerRuntimeException("Exception during field injection", e3);
        }
    }

    public static <T> T callConstructor(Class<T> targetClass, Class[] argClasses, Object[] args) {
        try {
            return AccessController.doPrivileged(new SetConstructorPrivilegedAction(targetClass.getDeclaredConstructor(argClasses), args));
        } catch (NoSuchMethodException e) {
            throw new ParcelerRuntimeException("Exception during method injection: NoSuchMethodException", e);
        } catch (PrivilegedActionException e2) {
            throw new ParcelerRuntimeException("PrivilegedActionException Exception during field injection", e2);
        } catch (Exception e3) {
            throw new ParcelerRuntimeException("Exception during field injection", e3);
        }
    }
}
