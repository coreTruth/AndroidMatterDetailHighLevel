package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.BusinessItem
import com.example.myapplication.data.entity.BusinessResponse
import com.example.myapplication.repository.BusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BusinessRepository
) : ViewModel() {
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _business: MutableLiveData<List<BusinessItem>> = MutableLiveData()
    val business: LiveData<List<BusinessItem>> get() = _business

    fun getBusiness(term: String, location: String) = viewModelScope.launch {
        _loading.value = true
        _business.value = repository.getBusiness(term, location)
        _loading.value = false
    }
}
