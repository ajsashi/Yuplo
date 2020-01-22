package com.yuplo.support.di.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.yuplo.support.Constants;
import com.yuplo.support.MyPreferenceManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bajic Dusko (www.bajicdusko.com) on 23/03/17.
 */

@Singleton
@Module
public class AndroidModule {
    private final Context context;

    public AndroidModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    @Named(Constants.APPLICATION_CONTEXT)
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    public MyPreferenceManager provideSharedPreferences(@Named(Constants.APPLICATION_CONTEXT) Context context) {
        return new MyPreferenceManager(context);
    }

    @Singleton
    @Provides
    public Resources provideResources(@Named(Constants.APPLICATION_CONTEXT) Context context) {
        return context.getResources();
    }

    @Provides
public Retrofit provideRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(180, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
