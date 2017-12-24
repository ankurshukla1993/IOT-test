package com.google.firebase.auth;

import android.support.annotation.Nullable;

public interface FirebaseUserMetadata {
    @Nullable
    long getCreationTimestamp();

    @Nullable
    long getLastSignInTimestamp();
}
