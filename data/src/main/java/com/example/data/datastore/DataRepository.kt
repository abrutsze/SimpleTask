package com.example.data.datastore

import com.example.Result
import com.example.data.model.BaseResultModel

interface DataRepository {
     suspend fun getDataInfo(): Result<BaseResultModel>
}