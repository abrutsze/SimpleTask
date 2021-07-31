package com.example.data.model

import com.squareup.moshi.Json

data class BaseDataModel(
    @field:Json(name = "memes")
    val memes: List<DataInfoResponse>
)
