package com.example.task.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.Result
import com.example.domian.interactors.DataInfoInteractor
import com.example.domian.model.DataInfo
import com.example.task.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(val dataInfoInteractor: DataInfoInteractor) : BaseViewModel() {

    private val _getDataInfoList by lazy { MutableLiveData<List<DataInfo>>() }
    val getDataInfoList: LiveData<List<DataInfo>> = _getDataInfoList

    private val _loadingData by lazy { MutableLiveData<Boolean>() }
    val loadingData: LiveData<Boolean> = _loadingData

    private val _errorNullData by lazy { MutableLiveData<String>() }
    val errorNullData get() = _errorNullData

    init {
        getActivityList()
    }

    private fun getActivityList() {
        _loadingData.value = true
        viewModelScope.launch {
            when (val result = dataInfoInteractor()) {
                is Result.Success -> {
                    _getDataInfoList.value = result.data
                }
                is Result.Error -> {
                    _errorNullData.value = result.errors.errorMessage
                }
            }
            _loadingData.value = false
        }
    }
}

