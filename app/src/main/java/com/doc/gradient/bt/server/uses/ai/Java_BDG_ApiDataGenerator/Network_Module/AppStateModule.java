package com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Network_Module;

import com.doc.gradient.bt.server.uses.ai.Java_BDG_ApiDataGenerator.Api_Service.AppStateApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppStateModule {

    @Provides
    @Singleton
    public static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public static Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://v9-api.aptyzeinfotech.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public static AppStateApiService provideApiClient(Retrofit retrofit) {
        return retrofit.create(AppStateApiService.class);
    }
}