package com.knt.mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.knt.mvvm.R
import com.knt.mvvm.databinding.FragmentHomeBinding
import com.knt.mvvm.network.api.UserApi
import com.knt.mvvm.repository.UserRepository
import com.knt.mvvm.ui.auth.AuthActivity
import com.knt.mvvm.ui.base.BaseFragment
import com.knt.mvvm.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() = lifecycleScope.launch{
        //val authToken = userPreferences.authToken.first()
        viewModel.logout()
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }
}