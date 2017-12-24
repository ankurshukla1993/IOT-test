package kotlin.text;

import com.facebook.internal.FacebookRequestErrorClassification;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.IntIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0011\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\b\u001a\u0019\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\b\u001a!\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\b\u001a)\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\b\u001a!\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\b\u001a!\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\b\u001a\n\u0010\u0016\u001a\u00020\u0002*\u00020\u0002\u001a\u0015\u0010\u0017\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0010H\b\u001a\u0015\u0010\u0019\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0010H\b\u001a\u001d\u0010\u001a\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\b\u001a\u001c\u0010\u001d\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\u0015\u0010!\u001a\u00020 *\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\b\u001a\u0015\u0010!\u001a\u00020 *\u00020\u00022\u0006\u0010\"\u001a\u00020#H\b\u001a\n\u0010$\u001a\u00020\u0002*\u00020\u0002\u001a\u001c\u0010%\u001a\u00020 *\u00020\u00022\u0006\u0010&\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a \u0010'\u001a\u00020 *\u0004\u0018\u00010\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a2\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020*2\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\b¢\u0006\u0002\u0010.\u001a*\u0010(\u001a\u00020\u0002*\u00020\u00022\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\b¢\u0006\u0002\u0010/\u001a:\u0010(\u001a\u00020\u0002*\u00020\u00032\u0006\u0010)\u001a\u00020*2\u0006\u0010(\u001a\u00020\u00022\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\b¢\u0006\u0002\u00100\u001a2\u0010(\u001a\u00020\u0002*\u00020\u00032\u0006\u0010(\u001a\u00020\u00022\u0016\u0010+\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010-0,\"\u0004\u0018\u00010-H\b¢\u0006\u0002\u00101\u001a\r\u00102\u001a\u00020\u0002*\u00020\u0002H\b\u001a\n\u00103\u001a\u00020 *\u00020#\u001a\u001d\u00104\u001a\u00020\u0010*\u00020\u00022\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0010H\b\u001a\u001d\u00104\u001a\u00020\u0010*\u00020\u00022\u0006\u00108\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u0010H\b\u001a\u001d\u00109\u001a\u00020\u0010*\u00020\u00022\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0010H\b\u001a\u001d\u00109\u001a\u00020\u0010*\u00020\u00022\u0006\u00108\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u0010H\b\u001a\u001d\u0010:\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010H\b\u001a4\u0010<\u001a\u00020 *\u00020#2\u0006\u0010=\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020#2\u0006\u0010>\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 \u001a4\u0010<\u001a\u00020 *\u00020\u00022\u0006\u0010=\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\u0012\u0010?\u001a\u00020\u0002*\u00020#2\u0006\u0010@\u001a\u00020\u0010\u001a$\u0010A\u001a\u00020\u0002*\u00020\u00022\u0006\u0010B\u001a\u0002062\u0006\u0010C\u001a\u0002062\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010A\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010F\u001a\u00020\u0002*\u00020\u00022\u0006\u0010B\u001a\u0002062\u0006\u0010C\u001a\u0002062\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010F\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\"\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00020H*\u00020#2\u0006\u0010I\u001a\u00020J2\b\b\u0002\u0010K\u001a\u00020\u0010\u001a\u001c\u0010L\u001a\u00020 *\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\u001f\u001a\u00020 \u001a$\u0010L\u001a\u00020 *\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010N\u001a\u00020\u00102\b\b\u0002\u0010\u001f\u001a\u00020 \u001a\u0015\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010N\u001a\u00020\u0010H\b\u001a\u001d\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010N\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\b\u001a\u0017\u0010P\u001a\u00020\f*\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u000eH\b\u001a\r\u0010Q\u001a\u00020\u0013*\u00020\u0002H\b\u001a3\u0010Q\u001a\u00020\u0013*\u00020\u00022\u0006\u0010R\u001a\u00020\u00132\b\b\u0002\u0010S\u001a\u00020\u00102\b\b\u0002\u0010N\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u0010H\b\u001a\r\u0010T\u001a\u00020\u0002*\u00020\u0002H\b\u001a\u0015\u0010T\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020*H\b\u001a\u0017\u0010U\u001a\u00020J*\u00020\u00022\b\b\u0002\u0010V\u001a\u00020\u0010H\b\u001a\r\u0010W\u001a\u00020\u0002*\u00020\u0002H\b\u001a\u0015\u0010W\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020*H\b\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006X"}, d2 = {"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", "charset", "Ljava/nio/charset/Charset;", "offset", "", "length", "chars", "", "codePoints", "", "capitalize", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "contentEquals", "charSequence", "", "decapitalize", "endsWith", "suffix", "equals", "format", "locale", "Ljava/util/Locale;", "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", "n", "replace", "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", "regex", "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "startIndex", "substring", "toByteArray", "toCharArray", "destination", "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: StringsJVM.kt */
class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    @InlineOnly
    private static final int nativeIndexOf(@NotNull String $receiver, char ch, int fromIndex) {
        if ($receiver != null) {
            return $receiver.indexOf(ch, fromIndex);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final int nativeIndexOf(@NotNull String $receiver, String str, int fromIndex) {
        if ($receiver != null) {
            return $receiver.indexOf(str, fromIndex);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final int nativeLastIndexOf(@NotNull String $receiver, char ch, int fromIndex) {
        if ($receiver != null) {
            return $receiver.lastIndexOf(ch, fromIndex);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final int nativeLastIndexOf(@NotNull String $receiver, String str, int fromIndex) {
        if ($receiver != null) {
            return $receiver.lastIndexOf(str, fromIndex);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static /* bridge */ /* synthetic */ boolean equals$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return equals(str, str2, z);
    }

    public static final boolean equals(@Nullable String $receiver, @Nullable String other, boolean ignoreCase) {
        if ($receiver == null) {
            return other == null;
        } else {
            if (ignoreCase) {
                return $receiver.equalsIgnoreCase(other);
            }
            return $receiver.equals(other);
        }
    }

    @NotNull
    public static final String replace(@NotNull String $receiver, char oldChar, char newChar, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if (ignoreCase) {
            return SequencesKt___SequencesKt.joinToString$default(StringsKt__StringsKt.splitToSequence$default((CharSequence) $receiver, new char[]{oldChar}, ignoreCase, 0, 4, null), String.valueOf(newChar), null, null, 0, null, null, 62, null);
        }
        String replace = $receiver.replace(oldChar, newChar);
        Intrinsics.checkExpressionValueIsNotNull(replace, "(this as java.lang.Strin…replace(oldChar, newChar)");
        return replace;
    }

    @NotNull
    public static final String replace(@NotNull String $receiver, @NotNull String oldValue, @NotNull String newValue, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(oldValue, "oldValue");
        Intrinsics.checkParameterIsNotNull(newValue, "newValue");
        return SequencesKt___SequencesKt.joinToString$default(StringsKt__StringsKt.splitToSequence$default((CharSequence) $receiver, new String[]{oldValue}, ignoreCase, 0, 4, null), newValue, null, null, 0, null, null, 62, null);
    }

    @NotNull
    public static final String replaceFirst(@NotNull String $receiver, char oldChar, char newChar, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        int index = StringsKt__StringsKt.indexOf$default((CharSequence) $receiver, oldChar, 0, ignoreCase, 2, null);
        if (index < 0) {
            return $receiver;
        }
        return StringsKt__StringsKt.replaceRange((CharSequence) $receiver, index, index + 1, (CharSequence) String.valueOf(newChar)).toString();
    }

    @NotNull
    public static final String replaceFirst(@NotNull String $receiver, @NotNull String oldValue, @NotNull String newValue, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(oldValue, "oldValue");
        Intrinsics.checkParameterIsNotNull(newValue, "newValue");
        int index = StringsKt__StringsKt.indexOf$default((CharSequence) $receiver, oldValue, 0, ignoreCase, 2, null);
        if (index < 0) {
            return $receiver;
        }
        return StringsKt__StringsKt.replaceRange((CharSequence) $receiver, index, oldValue.length() + index, (CharSequence) newValue).toString();
    }

    @InlineOnly
    private static final String toUpperCase(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String toUpperCase = $receiver.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(toUpperCase, "(this as java.lang.String).toUpperCase()");
        return toUpperCase;
    }

    @InlineOnly
    private static final String toLowerCase(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String toLowerCase = $receiver.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(toLowerCase, "(this as java.lang.String).toLowerCase()");
        return toLowerCase;
    }

    @InlineOnly
    private static final char[] toCharArray(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Object toCharArray = $receiver.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(toCharArray, "(this as java.lang.String).toCharArray()");
        return toCharArray;
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ char[] toCharArray$default(String $receiver, char[] destination, int destinationOffset, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            destinationOffset = 0;
        }
        if ((i & 4) != 0) {
            startIndex = 0;
        }
        if ((i & 8) != 0) {
            endIndex = $receiver.length();
        }
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        $receiver.getChars(startIndex, endIndex, destination, destinationOffset);
        return destination;
    }

    @InlineOnly
    private static final char[] toCharArray(@NotNull String $receiver, char[] destination, int destinationOffset, int startIndex, int endIndex) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        $receiver.getChars(startIndex, endIndex, destination, destinationOffset);
        return destination;
    }

    @InlineOnly
    private static final String format(@NotNull String $receiver, Object... args) {
        String format = String.format($receiver, Arrays.copyOf(args, args.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return format;
    }

    @InlineOnly
    private static final String format(@NotNull StringCompanionObject $receiver, String format, Object... args) {
        String format2 = String.format(format, Arrays.copyOf(args, args.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        return format2;
    }

    @InlineOnly
    private static final String format(@NotNull String $receiver, Locale locale, Object... args) {
        String format = String.format(locale, $receiver, Arrays.copyOf(args, args.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, this, *args)");
        return format;
    }

    @InlineOnly
    private static final String format(@NotNull StringCompanionObject $receiver, Locale locale, String format, Object... args) {
        String format2 = String.format(locale, format, Arrays.copyOf(args, args.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(locale, format, *args)");
        return format2;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List split$default(CharSequence charSequence, Pattern pattern, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return split(charSequence, pattern, i);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence $receiver, @NotNull Pattern regex, int limit) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(regex, "regex");
        if ((limit >= 0 ? 1 : null) == null) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
        }
        if (limit == 0) {
            limit = -1;
        }
        return ArraysKt___ArraysKt.asList((Object[]) regex.split($receiver, limit));
    }

    @InlineOnly
    private static final String substring(@NotNull String $receiver, int startIndex) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = $receiver.substring(startIndex);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
    }

    @InlineOnly
    private static final String substring(@NotNull String $receiver, int startIndex, int endIndex) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = $receiver.substring(startIndex, endIndex);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return startsWith(str, str2, z);
    }

    public static final boolean startsWith(@NotNull String $receiver, @NotNull String prefix, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!ignoreCase) {
            return $receiver.startsWith(prefix);
        }
        return regionMatches($receiver, 0, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return startsWith(str, str2, i, z);
    }

    public static final boolean startsWith(@NotNull String $receiver, @NotNull String prefix, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!ignoreCase) {
            return $receiver.startsWith(prefix, startIndex);
        }
        return regionMatches($receiver, startIndex, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* bridge */ /* synthetic */ boolean endsWith$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return endsWith(str, str2, z);
    }

    public static final boolean endsWith(@NotNull String $receiver, @NotNull String suffix, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (!ignoreCase) {
            return $receiver.endsWith(suffix);
        }
        return regionMatches($receiver, $receiver.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    @InlineOnly
    private static final String String(byte[] bytes, int offset, int length, Charset charset) {
        return new String(bytes, offset, length, charset);
    }

    @InlineOnly
    private static final String String(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }

    @InlineOnly
    private static final String String(byte[] bytes, int offset, int length) {
        return new String(bytes, offset, length, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(byte[] bytes) {
        return new String(bytes, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(char[] chars) {
        return new String(chars);
    }

    @InlineOnly
    private static final String String(char[] chars, int offset, int length) {
        return new String(chars, offset, length);
    }

    @InlineOnly
    private static final String String(int[] codePoints, int offset, int length) {
        return new String(codePoints, offset, length);
    }

    @InlineOnly
    private static final String String(StringBuffer stringBuffer) {
        return new String(stringBuffer);
    }

    @InlineOnly
    private static final String String(StringBuilder stringBuilder) {
        return new String(stringBuilder);
    }

    @InlineOnly
    private static final int codePointAt(@NotNull String $receiver, int index) {
        if ($receiver != null) {
            return $receiver.codePointAt(index);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final int codePointBefore(@NotNull String $receiver, int index) {
        if ($receiver != null) {
            return $receiver.codePointBefore(index);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final int codePointCount(@NotNull String $receiver, int beginIndex, int endIndex) {
        if ($receiver != null) {
            return $receiver.codePointCount(beginIndex, endIndex);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static /* bridge */ /* synthetic */ int compareTo$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return compareTo(str, str2, z);
    }

    public static final int compareTo(@NotNull String $receiver, @NotNull String other, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, FacebookRequestErrorClassification.KEY_OTHER);
        if (ignoreCase) {
            return $receiver.compareToIgnoreCase(other);
        }
        return $receiver.compareTo(other);
    }

    @InlineOnly
    private static final boolean contentEquals(@NotNull String $receiver, CharSequence charSequence) {
        if ($receiver != null) {
            return $receiver.contentEquals(charSequence);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final boolean contentEquals(@NotNull String $receiver, StringBuffer stringBuilder) {
        if ($receiver != null) {
            return $receiver.contentEquals(stringBuilder);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @InlineOnly
    private static final String intern(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String intern = $receiver.intern();
        Intrinsics.checkExpressionValueIsNotNull(intern, "(this as java.lang.String).intern()");
        return intern;
    }

    public static final boolean isBlank(@NotNull CharSequence $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ($receiver.length() != 0) {
            Object obj;
            Iterable $receiver$iv = StringsKt__StringsKt.getIndices($receiver);
            if (($receiver$iv instanceof Collection) && ((Collection) $receiver$iv).isEmpty()) {
                obj = 1;
            } else {
                Iterator it = $receiver$iv.iterator();
                while (it.hasNext()) {
                    if (!CharsKt__CharJVMKt.isWhitespace($receiver.charAt(((IntIterator) it).nextInt()))) {
                        obj = null;
                        break;
                    }
                }
                int i = 1;
            }
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    @InlineOnly
    private static final int offsetByCodePoints(@NotNull String $receiver, int index, int codePointOffset) {
        if ($receiver != null) {
            return $receiver.offsetByCodePoints(index, codePointOffset);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static final boolean regionMatches(@NotNull CharSequence $receiver, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, FacebookRequestErrorClassification.KEY_OTHER);
        if (!($receiver instanceof String) || !(other instanceof String)) {
            return StringsKt__StringsKt.regionMatchesImpl($receiver, thisOffset, other, otherOffset, length, ignoreCase);
        }
        return regionMatches((String) $receiver, thisOffset, (String) other, otherOffset, length, ignoreCase);
    }

    public static final boolean regionMatches(@NotNull String $receiver, int thisOffset, @NotNull String other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, FacebookRequestErrorClassification.KEY_OTHER);
        if (ignoreCase) {
            return $receiver.regionMatches(ignoreCase, thisOffset, other, otherOffset, length);
        }
        return $receiver.regionMatches(thisOffset, other, otherOffset, length);
    }

    @InlineOnly
    private static final String toLowerCase(@NotNull String $receiver, Locale locale) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String toLowerCase = $receiver.toLowerCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(toLowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return toLowerCase;
    }

    @InlineOnly
    private static final String toUpperCase(@NotNull String $receiver, Locale locale) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String toUpperCase = $receiver.toUpperCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(toUpperCase, "(this as java.lang.String).toUpperCase(locale)");
        return toUpperCase;
    }

    @InlineOnly
    private static final byte[] toByteArray(@NotNull String $receiver, Charset charset) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Object bytes = $receiver.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ byte[] toByteArray$default(String $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Object bytes = $receiver.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ Pattern toPattern$default(String $receiver, int flags, int i, Object obj) {
        if ((i & 1) != 0) {
            flags = 0;
        }
        Pattern compile = Pattern.compile($receiver, flags);
        Intrinsics.checkExpressionValueIsNotNull(compile, "java.util.regex.Pattern.compile(this, flags)");
        return compile;
    }

    @InlineOnly
    private static final Pattern toPattern(@NotNull String $receiver, int flags) {
        Pattern compile = Pattern.compile($receiver, flags);
        Intrinsics.checkExpressionValueIsNotNull(compile, "java.util.regex.Pattern.compile(this, flags)");
        return compile;
    }

    @NotNull
    public static final String capitalize(@NotNull String $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ((((CharSequence) $receiver).length() > 0 ? 1 : 0) == 0 || !Character.isLowerCase($receiver.charAt(0))) {
            return $receiver;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String substring = $receiver.substring(0, 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        substring = substring.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).toUpperCase()");
        stringBuilder = stringBuilder.append(substring);
        String substring2 = $receiver.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        return stringBuilder.append(substring2).toString();
    }

    @NotNull
    public static final String decapitalize(@NotNull String $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ((((CharSequence) $receiver).length() > 0 ? 1 : 0) == 0 || !Character.isUpperCase($receiver.charAt(0))) {
            return $receiver;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String substring = $receiver.substring(0, 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        substring = substring.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).toLowerCase()");
        stringBuilder = stringBuilder.append(substring);
        String substring2 = $receiver.substring(1);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        return stringBuilder.append(substring2).toString();
    }

    @NotNull
    public static final String repeat(@NotNull CharSequence $receiver, int n) {
        int i = 1;
        int i$iv = 0;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ((n >= 0 ? 1 : 0) == 0) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + n + '.').toString());
        }
        switch (n) {
            case 0:
                return "";
            case 1:
                return $receiver.toString();
            default:
                switch ($receiver.length()) {
                    case 0:
                        return "";
                    case 1:
                        char charR = $receiver.charAt(0);
                        char[] result$iv = new char[n];
                        int length = result$iv.length;
                        while (i$iv < length) {
                            result$iv[i$iv] = charR;
                            i$iv++;
                        }
                        return new String(result$iv);
                    default:
                        StringBuilder sb = new StringBuilder($receiver.length() * n);
                        if (1 <= n) {
                            while (true) {
                                sb.append($receiver);
                                if (i != n) {
                                    i++;
                                }
                            }
                        }
                        String stringBuilder = sb.toString();
                        Intrinsics.checkExpressionValueIsNotNull(stringBuilder, "sb.toString()");
                        return stringBuilder;
                }
        }
    }

    @NotNull
    public static final Comparator<String> getCASE_INSENSITIVE_ORDER(@NotNull StringCompanionObject $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        Intrinsics.checkExpressionValueIsNotNull(comparator, "java.lang.String.CASE_INSENSITIVE_ORDER");
        return comparator;
    }
}
