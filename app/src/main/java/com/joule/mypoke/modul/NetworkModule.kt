package com.joule.mypoke.modul

import com.google.gson.Gson
import com.joule.mypoke.commons.Constants.BASE_URL
import com.joule.mypoke.commons.Constants.NETWORK_TIMEOUT
import com.joule.mypoke.domain.PokeApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideOkHttpClient() }
    single { Gson() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideMainApi(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun provideOkHttpClient(
) = OkHttpClient().newBuilder()
    .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().apply {
        level =
            HttpLoggingInterceptor.Level.BODY
    })
    .build()

private fun provideMainApi(retrofit: Retrofit): PokeApi = retrofit.create(PokeApi::class.java)