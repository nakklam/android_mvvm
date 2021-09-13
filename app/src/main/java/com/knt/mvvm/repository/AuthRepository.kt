package com.knt.mvvm.repository

import com.knt.mvvm.network.api.AuthApi
import com.knt.mvvm.preferences.UserPreferences

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(){

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}