package com.example.data.repository

import com.example.Result
import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.DataRepository
import com.example.data.model.BaseResultModel
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall

class DataRepositoryImpl(private val allApiService: AllApiService) :
    DataRepository {

    override suspend fun getDataInfo(): Result<BaseResultModel> =

        makeApiCall({
            analyzeResponse(allApiService.getUserData())
        })
}