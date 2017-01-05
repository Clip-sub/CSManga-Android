<<<<<<< HEAD
package com.clipsub.csmanga.data.networks;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Response;

import rx.Observable;

public interface NetworkService {

    public Observable<Response> getResponse(String url, CacheControl cacheControl, Headers headers);

    public Observable<String> mapResponseToString(Response response);
}
=======
package com.clipsub.csmanga.data.networks;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Response;

import rx.Observable;

public interface NetworkService {

    public Observable<Response> getResponse(String url, CacheControl cacheControl, Headers headers);

    public Observable<String> mapResponseToString(Response response);
}
>>>>>>> 78ab0b8a13b06d156a61db6d7ae3c71d13993a7f
