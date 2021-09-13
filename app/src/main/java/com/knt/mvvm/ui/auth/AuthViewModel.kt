package com.knt.mvvm.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knt.mvvm.network.NetworkResult
import com.knt.mvvm.repository.AuthRepository
import com.knt.mvvm.model.LoginResponse
import kotlinx.coroutines.launch

class AuthViewModel (private val repository:AuthRepository): ViewModel(){
    val loginResponse: MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        loginResponse.value = repository.login(email,password)
    }

    fun saveAuthToken(token: String) = viewModelScope.launch {
        repository.saveAuthToken(token)
    }
}