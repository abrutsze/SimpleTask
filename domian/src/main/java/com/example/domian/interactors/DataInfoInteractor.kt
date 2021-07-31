package com.example.domian.interactors

import com.example.Result
import com.example.domian.model.DataInfo


interface DataInfoInteractor {
    suspend operator fun invoke(): Result<List<DataInfo>>
}