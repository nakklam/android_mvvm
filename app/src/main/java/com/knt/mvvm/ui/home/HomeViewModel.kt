package com.knt.mvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knt.mvvm.network.NetworkResult
import com.knt.mvvm.repository.UserRepository
import com.knt.mvvm.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel (private val repository: UserRepository) : ViewModel() {
    private val _user: MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()
    val user: LiveData<NetworkResult<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = NetworkResult.Loading
        _user.value = repository.getUser()
    }

    suspend fun logout() = withContext(Dispatchers.IO) { repository.logout() }

}