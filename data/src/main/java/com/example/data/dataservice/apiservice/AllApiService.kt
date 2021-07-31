package com.example.data.dataservice.apiservice

import com.example.data.model.BaseResultModel
import retrofit2.Response
import retrofit2.http.GET

interface AllApiService {

    @GET("get_memes")
    suspend fun getUserData():Response<BaseResultModel>
}
