package com.virtualworld.scorganizadores.ui.screens.auth.viewModels // ktlint-disable package-name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.domain.entity.user.UserInformationEntity
import com.virtualworld.scorganizadores.domain.usecase.user.sign_up.FirebaseUserSignUpUseCase
import com.virtualworld.scorganizadores.domain.usecase.user.write_user.WriteFirebaseUserInfosUseCase
import com.virtualworld.scorganizadores.common.ScreenStateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: FirebaseUserSignUpUseCase,
    private val writeFirebaseUserInfosUseCase: WriteFirebaseUserInfosUseCase,

) : ViewModel() {
    private val _signUp = MutableLiveData<ScreenStateUser>()
    val signUp: LiveData<ScreenStateUser> get() = _signUp

    fun signUp(user: UserInformationEntity) {
        _signUp.value = ScreenStateUser.Loading

        viewModelScope.launch {
            signUpUseCase.invoke(
                user,
                onSuccess = {
                    _signUp.postValue(ScreenStateUser.Success)
                    writeUserToFirebaseDatabase(user)
                },
            ) {
                _signUp.postValue(ScreenStateUser.Error(it))
            }
        }
    }

    private fun writeUserToFirebaseDatabase(user: UserInformationEntity) {
        viewModelScope.launch {
            writeFirebaseUserInfosUseCase.invoke(
                user,
                onSuccess = {},
            ) {
                _signUp.postValue(ScreenStateUser.Error(it))
            }
        }
    }
}
