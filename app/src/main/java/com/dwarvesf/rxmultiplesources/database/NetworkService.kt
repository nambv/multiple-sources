package com.dwarvesf.rxmultiplesources.database

import com.dwarvesf.rxmultiplesources.ApiEndPoint
import com.google.gson.Gson
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    object Factory {

            fun get(): NetworkService {

                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()

                val client = httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .method(original.method(), original.body())
                            .build()

                    chain.proceed(request)
                }
                        .addInterceptor(loggingInterceptor)
                        .build()

                val retrofit = Retrofit.Builder()
                        .baseUrl(ApiEndPoint.ROOT_API)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(Gson()))
                        .client(client)
                        .build()

                return retrofit.create(NetworkService::class.java)
            }
    }

    @GET(ApiEndPoint.USERS)
    fun getUsers(@Query("limit") limit: Int): Flowable<List<User>>
}