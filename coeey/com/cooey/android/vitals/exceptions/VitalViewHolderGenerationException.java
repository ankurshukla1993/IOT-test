package com.cooey.android.vitals.exceptions;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\t"}, d2 = {"Lcom/cooey/android/vitals/exceptions/VitalViewHolderGenerationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalViewHolderGenerationException.kt */
public final class VitalViewHolderGenerationException extends Exception {
    @Nullable
    private String message;

    public VitalViewHolderGenerationException(@Nullable String message) {
        super(message);
        this.message = message;
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(@Nullable String <set-?>) {
        this.message = <set-?>;
    }
}
