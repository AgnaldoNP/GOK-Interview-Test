package dev.agnaldo.gokinterviewtest.data.source.remote.di

import dev.agnaldo.gokinterviewtest.data.source.remote.ProductsApi
import agnaldo.test.certificationtrainingapp.data.source.remote.helper.interceptor.AuthInterceptor
import agnaldo.test.certificationtrainingapp.data.source.remote.helper.serializer.JsonDateDeserializer
import com.google.gson.GsonBuilder
import dev.agnaldo.gokinterviewtest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class NetworkModule {

    companion object {

        val networkModule = module {
            // Base  network injections
            single {
                Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.API_URL).client(
                        OkHttpClient().newBuilder()
                            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }).addInterceptor(AuthInterceptor()).build()
                    )
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().also {
                                it.registerTypeAdapter(
                                    Date::class.java, JsonDateDeserializer()
                                )
                            }.setDateFormat(JsonDateDeserializer.MAIN_DATE_FORMAT).create()
                        )
                    ).build()
            }

            // API injections
            single {
                inject<Retrofit>().value.create(ProductsApi::class.java)
            }
        }

    }

}
