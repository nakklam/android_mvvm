package com.knt.mvvm.repository

import com.knt.mvvm.network.api.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

    suspend fun logout() = safeApiCall {
        api.logout()
    }
}