package org.jetbrains.anko;

import com.evernote.android.job.JobStorage;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/anko/AnkoLogger;", "", "loggerTag", "", "getLoggerTag", "()Ljava/lang/String;", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: Logger.kt */
public interface AnkoLogger {

    @Metadata(bv = {1, 0, 0}, k = 3, mv = {1, 1, 0})
    /* compiled from: Logger.kt */
    public static final class DefaultImpls {
        @NotNull
        public static String getLoggerTag(AnkoLogger $this) {
            String tag = $this.getClass().getSimpleName();
            if (tag.length() <= 23) {
                Intrinsics.checkExpressionValueIsNotNull(tag, JobStorage.COLUMN_TAG);
                return tag;
            } else if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            } else {
                tag = tag.substring(0, 23);
                Intrinsics.checkExpressionValueIsNotNull(tag, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return tag;
            }
        }
    }

    @NotNull
    String getLoggerTag();
}
