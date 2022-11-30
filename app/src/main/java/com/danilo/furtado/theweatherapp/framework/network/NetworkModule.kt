package com.danilo.furtado.theweatherapp.framework.network

import com.danilo.furtado.theweatherapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.weatherapi.com/v1/"
const val API_KEY = "4239291fb2db45028ab80400223011"
const val DATE_FORMAT_SERVER = "yyyy-MM-dd HH:mm"

val networkModule = module {
    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setDateFormat(DATE_FORMAT_SERVER)
                            .serializeNulls()
                            .create()
                    )
            )
            .client(get())
            .build()
    }

    factory {
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
            }.build()
    }
}
