package com.example.kisshtassignment

import com.example.kisshtassignment.model.Photos
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkEndpoints {
    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    @GET("photos")
    fun loadPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Observable<Response<List<Photos>>>



}
