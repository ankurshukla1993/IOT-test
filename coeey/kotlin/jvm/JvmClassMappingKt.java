package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a!\u0010\u0018\u001a\u00020\u0019\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\r*\u0006\u0012\u0002\b\u00030\u001aH\u0007¢\u0006\u0002\u0010\u001b\"'\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"0\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018GX\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"&\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\u0002H\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000e\";\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018Ç\u0002X\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\t\u001a\u0004\b\u0010\u0010\u000b\"+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b\"-\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000b\"+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"annotationClass", "Lkotlin/reflect/KClass;", "T", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "java", "Ljava/lang/Class;", "java$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "javaClass$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-runtime"}, k = 2, mv = {1, 1, 7})
@JvmName(name = "JvmClassMappingKt")
/* compiled from: JvmClassMapping.kt */
public final class JvmClassMappingKt {
    public static /* synthetic */ void java$annotations(KClass kClass) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith = @ReplaceWith(expression = "(this as Any).javaClass", imports = {}))
    public static /* synthetic */ void javaClass$annotations(KClass kClass) {
    }

    @NotNull
    @JvmName(name = "getJavaClass")
    public static final <T> Class<T> getJavaClass(@NotNull KClass<T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Class<T> jClass = ((ClassBasedDeclarationContainer) $receiver).getJClass();
        if (jClass != null) {
            return jClass;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.Nullable
    public static final <T> java.lang.Class<T> getJavaPrimitiveType(@org.jetbrains.annotations.NotNull kotlin.reflect.KClass<T> r3) {
        /*
        r1 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r1);
        r3 = (kotlin.jvm.internal.ClassBasedDeclarationContainer) r3;
        r0 = r3.getJClass();
        r1 = r0.isPrimitive();
        if (r1 == 0) goto L_0x001b;
    L_0x0011:
        if (r0 != 0) goto L_0x0023;
    L_0x0013:
        r1 = new kotlin.TypeCastException;
        r2 = "null cannot be cast to non-null type java.lang.Class<T>";
        r1.<init>(r2);
        throw r1;
    L_0x001b:
        r1 = r0.getName();
        if (r1 != 0) goto L_0x0024;
    L_0x0021:
        r1 = 0;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        return r0;
    L_0x0024:
        r2 = r1.hashCode();
        switch(r2) {
            case -2056817302: goto L_0x002c;
            case -527879800: goto L_0x0037;
            case -515992664: goto L_0x0063;
            case 155276373: goto L_0x0042;
            case 344809556: goto L_0x006e;
            case 398507100: goto L_0x0079;
            case 398795216: goto L_0x004d;
            case 761287205: goto L_0x0058;
            default: goto L_0x002b;
        };
    L_0x002b:
        goto L_0x0021;
    L_0x002c:
        r2 = "java.lang.Integer";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x0034:
        r1 = java.lang.Integer.TYPE;
        goto L_0x0022;
    L_0x0037:
        r2 = "java.lang.Float";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x003f:
        r1 = java.lang.Float.TYPE;
        goto L_0x0022;
    L_0x0042:
        r2 = "java.lang.Character";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x004a:
        r1 = java.lang.Character.TYPE;
        goto L_0x0022;
    L_0x004d:
        r2 = "java.lang.Long";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x0055:
        r1 = java.lang.Long.TYPE;
        goto L_0x0022;
    L_0x0058:
        r2 = "java.lang.Double";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x0060:
        r1 = java.lang.Double.TYPE;
        goto L_0x0022;
    L_0x0063:
        r2 = "java.lang.Short";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x006b:
        r1 = java.lang.Short.TYPE;
        goto L_0x0022;
    L_0x006e:
        r2 = "java.lang.Boolean";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x0076:
        r1 = java.lang.Boolean.TYPE;
        goto L_0x0022;
    L_0x0079:
        r2 = "java.lang.Byte";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0021;
    L_0x0081:
        r1 = java.lang.Byte.TYPE;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.JvmClassMappingKt.getJavaPrimitiveType(kotlin.reflect.KClass):java.lang.Class<T>");
    }

    @NotNull
    public static final <T> Class<T> getJavaObjectType(@NotNull KClass<T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Class thisJClass = ((ClassBasedDeclarationContainer) $receiver).getJClass();
        if (thisJClass.isPrimitive()) {
            String name = thisJClass.getName();
            if (name != null) {
                switch (name.hashCode()) {
                    case -1325958191:
                        if (name.equals("double")) {
                            thisJClass = Double.class;
                            break;
                        }
                        break;
                    case 104431:
                        if (name.equals("int")) {
                            thisJClass = Integer.class;
                            break;
                        }
                        break;
                    case 3039496:
                        if (name.equals("byte")) {
                            thisJClass = Byte.class;
                            break;
                        }
                        break;
                    case 3052374:
                        if (name.equals("char")) {
                            thisJClass = Character.class;
                            break;
                        }
                        break;
                    case 3327612:
                        if (name.equals("long")) {
                            thisJClass = Long.class;
                            break;
                        }
                        break;
                    case 64711720:
                        if (name.equals("boolean")) {
                            thisJClass = Boolean.class;
                            break;
                        }
                        break;
                    case 97526364:
                        if (name.equals("float")) {
                            thisJClass = Float.class;
                            break;
                        }
                        break;
                    case 109413500:
                        if (name.equals("short")) {
                            thisJClass = Short.class;
                            break;
                        }
                        break;
                    default:
                        break;
                }
            }
            if (thisJClass == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
            }
        } else if (thisJClass == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return thisJClass;
    }

    @NotNull
    @JvmName(name = "getKotlinClass")
    public static final <T> KClass<T> getKotlinClass(@NotNull Class<T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return Reflection.getOrCreateKotlinClass($receiver);
    }

    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull T $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Class<T> cls = $receiver.getClass();
        if (cls != null) {
            return cls;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
    }

    @NotNull
    @JvmName(name = "getRuntimeClassOfKClassInstance")
    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(@NotNull KClass<T> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Class<KClass<T>> cls = $receiver.getClass();
        if (cls != null) {
            return cls;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T>>");
    }

    private static final <T> boolean isArrayOf(@NotNull Object[] $receiver) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return Object.class.isAssignableFrom($receiver.getClass().getComponentType());
    }

    @NotNull
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(@NotNull T $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        KClass<? extends T> kotlinClass = getKotlinClass($receiver.annotationType());
        if (kotlinClass != null) {
            return kotlinClass;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<out T>");
    }
}
