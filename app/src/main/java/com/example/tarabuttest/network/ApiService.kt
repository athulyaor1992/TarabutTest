package com.example.tarabuttest.network



import com.example.tarabuttest.model.Match
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("bc1ce3b7-6ad2-4fef-af6c-08f8865b210e")
    suspend fun getMatch1(): Response<ArrayList<Match?>>

    @GET("23745f3a-5eaa-43cf-ab46-737eb273596b")
    suspend fun getMatch2(): Response<ArrayList<Match?>>


}