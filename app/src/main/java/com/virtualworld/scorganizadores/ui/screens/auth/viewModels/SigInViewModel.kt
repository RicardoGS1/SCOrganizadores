package com.virtualworld.scorganizadores.ui.screens.auth.viewModels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.common.Constants.PREF_FIREBASE_USERID_KEY
import com.virtualworld.scorganizadores.common.ScreenStateUser
import com.virtualworld.scorganizadores.domain.entity.user.FirebaseSignInUserEntity
import com.virtualworld.scorganizadores.domain.usecase.user.sign_in.FirebaseUserSingInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(private val firebaseUserSingInUseCase: FirebaseUserSingInUseCase, private val sharedPreferences: SharedPreferences) :
    ViewModel()
{

    private val _firebaseLoginState = MutableLiveData<ScreenStateUser>()
    val firebaseLoginState: LiveData<ScreenStateUser> get() = _firebaseLoginState

    fun loginWithFirebase(user: FirebaseSignInUserEntity)
    {
        viewModelScope.launch {
            _firebaseLoginState.postValue(ScreenStateUser.Loading)
            firebaseUserSingInUseCase.invoke(
                user,
                onSuccess = {
                    _firebaseLoginState.postValue(
                        ScreenStateUser.Success,
                    )
                    saveUserIdToSharedPref(it.id)
                },
            ) {
                _firebaseLoginState.postValue(ScreenStateUser.Error(it))
            }
        }
    }

    private fun saveUserIdToSharedPref(id: String)
    {
        sharedPreferences.edit().putString(PREF_FIREBASE_USERID_KEY, id).apply()
    }
}
