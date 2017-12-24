package net.vrallev.android.cat.print;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface CatPrinter {
    void println(int i, @NonNull String str, @NonNull String str2, @Nullable Throwable th);
}
