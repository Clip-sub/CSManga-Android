<<<<<<< HEAD
package com.clipsub.csmanga.data.networks;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

public class NetworkServiceImpl implements NetworkService {

    public static final String TAG = NetworkServiceImpl.class.getSimpleName();

    private OkHttpClient mClient;

    public NetworkServiceImpl(OkHttpClient client) {
        this.mClient = client;
    }

    @Override
    public Observable<Response> getResponse(final String url, final CacheControl cacheControl, final Headers headers) {
        return Observable.create(new Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        Request request = new Request.Builder()
                                .url(url)
                                .cacheControl(cacheControl)
                                .headers(headers)
                                .build();

                        subscriber.onNext(mClient.newCall(request).execute());
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<String> mapResponseToString(final Response response) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(response.body().string());
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
=======
package com.clipsub.csmanga.data.networks;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

public class NetworkServiceImpl implements NetworkService {

    public static final String TAG = NetworkServiceImpl.class.getSimpleName();

    private OkHttpClient mClient;

    public NetworkServiceImpl(OkHttpClient client) {
        this.mClient = client;
    }

    @Override
    public Observable<Response> getResponse(final String url, final CacheControl cacheControl, final Headers headers) {
        return Observable.create(new Observable.OnSubscribe<Response>() {
            @Override
            public void call(Subscriber<? super Response> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        Request request = new Request.Builder()
                                .url(url)
                                .cacheControl(cacheControl)
                                .headers(headers)
                                .build();

                        subscriber.onNext(mClient.newCall(request).execute());
                    }

                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Override
    public Observable<String> mapResponseToString(final Response response) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(response.body().string());
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
>>>>>>> 78ab0b8a13b06d156a61db6d7ae3c71d13993a7f