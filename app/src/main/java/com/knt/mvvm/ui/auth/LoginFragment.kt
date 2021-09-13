package com.knt.mvvm.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.knt.mvvm.databinding.FragmentLoginBinding
import com.knt.mvvm.network.api.AuthApi
import com.knt.mvvm.network.NetworkResult
import com.knt.mvvm.repository.AuthRepository
import com.knt.mvvm.ui.base.BaseFragment
import com.knt.mvvm.ui.enable
import com.knt.mvvm.ui.home.HomeActivity
import com.knt.mvvm.ui.startNewActivity
import com.knt.mvvm.ui.visible

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.buttonLogin.enable(true)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(false)
            when (it) {
                is NetworkResult.Success -> {
                    viewModel.saveAuthToken("it.value.user.access_token!!")
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is NetworkResult.Failure -> {
                    viewModel.saveAuthToken("it.value.user.access_token!!")
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
            }
        })

        binding.buttonLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin(){
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        binding.progressbar.visible(true)
        viewModel.login(email, password)
    }

    override fun getViewModel(): Class<AuthViewModel> = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): AuthRepository =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java,null),userPreferences)

}