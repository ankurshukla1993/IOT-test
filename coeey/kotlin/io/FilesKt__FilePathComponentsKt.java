package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: FilePathComponents.kt */
class FilesKt__FilePathComponentsKt {
    private static final int getRootLength$FilesKt__FilePathComponentsKt(@NotNull String $receiver) {
        int first = StringsKt__StringsKt.indexOf$default((CharSequence) $receiver, File.separatorChar, 0, false, 4, null);
        if (first == 0) {
            if ($receiver.length() > 1 && $receiver.charAt(1) == File.separatorChar) {
                first = StringsKt__StringsKt.indexOf$default((CharSequence) $receiver, File.separatorChar, 2, false, 4, null);
                if (first >= 0) {
                    first = StringsKt__StringsKt.indexOf$default((CharSequence) $receiver, File.separatorChar, first + 1, false, 4, null);
                    if (first >= 0) {
                        return first + 1;
                    }
                    return $receiver.length();
                }
            }
            return 1;
        } else if (first > 0 && $receiver.charAt(first - 1) == ':') {
            return first + 1;
        } else {
            if (first == -1 && StringsKt__StringsKt.endsWith$default((CharSequence) $receiver, ':', false, 2, null)) {
                return $receiver.length();
            }
            return 0;
        }
    }

    @NotNull
    public static final String getRootName(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        String path = $receiver.getPath();
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt($receiver.getPath());
        if (path == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        path = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(path, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return path;
    }

    @NotNull
    public static final File getRoot(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new File(getRootName($receiver));
    }

    public static final boolean isRooted(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return getRootLength$FilesKt__FilePathComponentsKt($receiver.getPath()) > 0;
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        String path = $receiver.getPath();
        int rootLength = getRootLength$FilesKt__FilePathComponentsKt(path);
        if (path == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String rootName = path.substring(0, rootLength);
        Intrinsics.checkExpressionValueIsNotNull(rootName, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (path == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        List list;
        String subPath = path.substring(rootLength);
        Intrinsics.checkExpressionValueIsNotNull(subPath, "(this as java.lang.String).substring(startIndex)");
        if (subPath.length() == 0) {
            int i = 1;
        } else {
            boolean z = false;
        }
        if (i != 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        } else {
            Iterable<String> $receiver$iv = StringsKt__StringsKt.split$default((CharSequence) subPath, new char[]{File.separatorChar}, false, 0, 6, null);
            Collection destination$iv$iv = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault($receiver$iv, 10));
            for (String file : $receiver$iv) {
                destination$iv$iv.add(new File(file));
            }
            list = (List) destination$iv$iv;
        }
        return new FilePathComponents(new File(rootName), list);
    }

    @NotNull
    public static final File subPath(@NotNull File $receiver, int beginIndex, int endIndex) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return toComponents($receiver).subPath(beginIndex, endIndex);
    }
}
