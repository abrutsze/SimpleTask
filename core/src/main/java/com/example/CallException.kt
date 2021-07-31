package com.example

data class CallException<ErrorBody>(val errorCode: Int, val errorMessage:String?=null, val errorBody: ErrorBody? = null,)