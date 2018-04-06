package com.app.bourbon.clone.bourbonbaselib.data.remote;

import android.content.Context;

import com.app.bourbon.clone.bourbonbaselib.BuildConfig;
import com.app.bourbon.clone.bourbonbaselib.utils.BourbonServiceConstants;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 06/04/18.
 */

public class BourbonServiceFactory {

    private static Retrofit retrofit = null;
    private static Context mContext;

    /**
     * creating retorfit service to consume data
     *
     * @return
     */
    public static BourbonService makeBourbonService() {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BourbonServiceConstants.DRIBBLE_API_URL)// wait till i fix this
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            return retrofit.create(BourbonService.class);
        } else {
            return retrofit.create(BourbonService.class);
        }
    }

    /**
     * Currently not required
     */
   /* public static Retrofit makeImageDownloadServicCall(Context mContexts) {
        mContext = mContexts;
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor(), mContext);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BRQCConstants.SSO_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            return retrofit;
        } else {
            return retrofit;
        }
    }*/


    /**
     * creating OkHttpClient service
     *
     * @param httpLoggingInterceptor
     * @return OkHttpsClient service
     */
    public static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }


   /* public static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Context mContext) {
        int cacheSize = 50 * 1024 * 1024; // 50 MB
        Cache cache = new Cache(mContext.getCacheDir(), cacheSize);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }*/


    /**
     * Creating Logging interceptor
     *
     * @return HttpLoggingInterceptor
     */
    public static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }
}
