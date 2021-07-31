package com.example.data.model

import com.squareup.moshi.Json

data class BaseResultModel(
    @field:Json(name = "data")
    val data: BaseDataModel,
    @field:Json(name = "success")
    val success: Boolean
)
