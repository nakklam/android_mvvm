package com.knt.mvvm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knt.mvvm.repository.AuthRepository
import com.knt.mvvm.repository.BaseRepository
import com.knt.mvvm.repository.UserRepository
import com.knt.mvvm.ui.auth.AuthViewModel
import com.knt.mvvm.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val repository: BaseRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}