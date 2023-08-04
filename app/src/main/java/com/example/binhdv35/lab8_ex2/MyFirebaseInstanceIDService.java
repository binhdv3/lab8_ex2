package com.example.binhdv35.lab8_ex2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.IOException;

public class MyFirebaseInstanceIDService implements FirebaseInstanceIdInternal {

    @Override
    public String getId() {
        return null;
    }

    @Nullable
    @Override
    public String getToken() {
        return null;
    }

    @NonNull
    @Override
    public Task<String> getTokenTask() {
        return null;
    }

    @Override
    public void deleteToken(@NonNull String s, @NonNull String s1) throws IOException {

    }

    @Override
    public void addNewTokenListener(NewTokenListener newTokenListener) {

    }
}
