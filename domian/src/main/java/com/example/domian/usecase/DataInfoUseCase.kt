package com.example.domian.usecase

import com.example.CallException
import com.example.Constants.Companion.ERROR_NULL_DATA
import com.example.Result
import com.example.data.datastore.DataRepository
import com.example.domian.interactors.DataInfoInteractor
import com.example.domian.model.DataInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataInfoUseCase(
    private val dataRepository: DataRepository
) : DataInfoInteractor {

    override suspend operator fun invoke(): Result<List<DataInfo>> = withContext(Dispatchers.IO) {

        when (val apiData = dataRepository.getDataInfo()) {
            is Result.Success -> {
                apiData.data?.let { it ->
                    Result.Success(it.data.memes.map { it.toDomain() })
                } ?: Result.Error(
                    CallException(
                        ERROR_NULL_DATA
                    )
                )

            }
            is Result.Error -> {
                Result.Error(
                    CallException(
                        ERROR_NULL_DATA,
                        apiData.errors.errorMessage
                    )
                )

            }
        }

    }

}