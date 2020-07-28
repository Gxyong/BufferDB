package com.buffer;


import androidx.annotation.Nullable;

import io.reactivex.observers.DisposableObserver;

public abstract class ValueObserver<T> extends DisposableObserver<T> {
    public abstract void onResult(@Nullable T result);

    @Override
    public final void onNext(T t) {
        onResult(t);
    }

    @Override
    public final void onError(Throwable e) {
        onResult(null);
    }

    @Override
    public final void onComplete() {

    }
}