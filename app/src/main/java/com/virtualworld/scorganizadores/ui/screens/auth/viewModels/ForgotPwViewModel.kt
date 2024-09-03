package com.virtualworld.scorganizadores.ui.screens.auth.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.domain.usecase.user.forget_pw.ForgotPwFirebaseUserUseCase
import com.virtualworld.scorganizadores.common.ScreenStateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPwViewModel @Inject constructor(
    private val useCase: ForgotPwFirebaseUserUseCase,
): ViewModel() {
    private val _forgotPassword = MutableLiveData<ScreenStateUser>()
    val forgotPassword: LiveData<ScreenStateUser> get() = _forgotPassword

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPassword.postValue(ScreenStateUser.Loading)
            useCase.invoke(
                email,
                onSuccess = {
                    _forgotPassword.postValue(ScreenStateUser.Success)
                },
                onFailure = {
                    _forgotPassword.postValue(ScreenStateUser.Error(it))
                },
            )
        }
    }
}