package com.dimache.yarabatman.data.remote.util;

import com.dimache.yarabatman.BuildConfig;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.dimache.yarabatman.data.sp.SPManager;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hamid Sayah.
 */

public class RetrofitBuilder
{

    private static final String baseUrl = "http://www.omdbapi.com/";
    private static RetrofitBuilder INSTANCE;
    private static WebApi webApi;
    public static WebApi mockWebApi;

    private RetrofitBuilder() {
    }

    public static void init(){
        if (INSTANCE == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {


                            Request original = chain.request();

                            Request request = original.newBuilder()
                                    .header("uuid", Objects.requireNonNull(SPManager.getUuid()))
                                    .header("Authorization",  SPManager.getToken())
                                    .build();

                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(
                            new LoggingInterceptor.Builder()
                                    .loggable(BuildConfig.DEBUG)
                                    .setLevel(Level.BASIC)
                                    .log(Platform.INFO)
                                    .log(Platform.WARN)
                                    .loggable(BuildConfig.DEBUG)
                                    .request("APIREQ")
                                    .response("APIRES")
                                    .build()
                    )
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            webApi = retrofit.create(WebApi.class);

         /*   // cretae Mock Retorfit
            NetworkBehavior behavior = NetworkBehavior.create();
            behavior.setDelay(900, TimeUnit.MILLISECONDS);
            MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                    .networkBehavior(behavior)
                    .build();
            BehaviorDelegate<WebApi> delegate = mockRetrofit.create(WebApi.class);
            webApi = retrofit.create(WebApi.class);
            mockWebApi = new MockWebApi(delegate);
            //----*/

            INSTANCE = new RetrofitBuilder();
        }
    }

    public static WebApi getWebApi() { return webApi; }
    public static WebApi getMockWebApi() { return mockWebApi; }

}
