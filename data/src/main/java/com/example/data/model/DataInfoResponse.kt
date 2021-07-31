package com.example.data.model

import com.squareup.moshi.Json

data class DataInfoResponse(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "width")
    val width: Int,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "box_count")
    val boxCount: String,
)