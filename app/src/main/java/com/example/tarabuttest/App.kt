package com.example.tarabuttest

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.cristopher.mauratzjarl.data.network.responces.NetworkConnectionInterceptor
import com.example.tarabuttest.network.ApiService
import com.example.tarabuttest.db.AppDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class App  : Application() {

    var BASE_URL = "https://run.mocky.io/v3/"

    companion object {
        var api: ApiService? = null
        var appDatabase: AppDatabase? = null
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        val interceptor = NetworkConnectionInterceptor(this)

        val okkHttpClient =OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okkHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)
        appDatabase  = AppDatabase.getInstance()

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}