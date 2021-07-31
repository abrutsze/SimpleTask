package com.example.domian.utils

import com.example.data.model.DataInfoResponse
import com.example.domian.model.DataInfo

fun DataInfoResponse.toDomain(): DataInfo =
    DataInfo(
        id,
        name,
        url,
        width,
        height
    )






