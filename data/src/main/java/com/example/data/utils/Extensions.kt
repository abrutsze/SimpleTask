package com.example.data.utils

import android.util.Log
import com.example.CallException
import com.example.Result
import retrofit2.Response
import java.lang.Exception

suspend fun <R> makeApiCall(
    call: suspend () -> Result<R>,
    errorCode: Int = 1000
) = try {
    call()
} catch (e: Exception) {
    Log.i("ErrorMessage", "makeApiCall: ${e.message}")
    Result.Error(CallException<Nothing>(errorCode,e.message))
}

fun <R> analyzeResponse(response: Response<R>): Result<R> {
    return when (response.code()) {
        200 -> {
            val responseBody = response.body()
            Result.Success(responseBody)
        }
        else -> {
            Result.Error(CallException<Nothing>(response.code(),response.message()))
        }
    }
}
