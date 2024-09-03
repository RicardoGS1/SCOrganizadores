package com.virtualworld.scorganizadores.ui.screens.profile

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.common.Constants.PREF_DEF_STR
import com.virtualworld.scorganizadores.common.ScreenStateUser

import com.virtualworld.scorganizadores.domain.usecase.user.read_user.ReadFirebaseUserInfosUseCase


import com.virtualworld.scorganizadores.domain.entity.user.UserInformationEntity
import com.virtualworld.scorganizadores.domain.usecase.user.SignOutUseCase
import com.virtualworld.scorganizadores.common.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

    private val readFirebaseUserInfosUseCase: ReadFirebaseUserInfosUseCase,private val  signOutUseCase: SignOutUseCase ,private val sharedPreferences: SharedPreferences) : ViewModel()
{
    private val _userInfos = MutableLiveData<ScreenStateUser>()
    val userInfos: LiveData<ScreenStateUser> get() = _userInfos

    private val _userInformation = MutableLiveData<UserInformationEntity>()
    val userInformation: LiveData<UserInformationEntity> get() = _userInformation

    private val _toLogIn = MutableLiveData<Boolean>()
    val toLogIn: LiveData<Boolean> get() = _toLogIn



    var a =0

    init
    {
        chequeRegistry()
        println("uuufffff")

    }

     fun chequeRegistry()
    {
        println(getUserIdFromSharedPref(sharedPreferences))

        if(getUserIdFromSharedPref(sharedPreferences) !=PREF_DEF_STR)
            getUserInfosFromFirebase()
        else{
            _toLogIn.value=true
        }

    }

    fun salir(){
        signOutUseCase.invoke()
        _toLogIn.value=true

    }

    private fun getUserInfosFromFirebase()
    {
        _userInfos.value = ScreenStateUser.Loading
        viewModelScope.launch {
            readFirebaseUserInfosUseCase.invoke(
                getUserIdFromSharedPref(sharedPreferences),
                onSuccess = {
                    _userInfos.postValue(ScreenStateUser.Success)
                    _userInformation.postValue(it)
                },
            ) {
                _userInfos.postValue(ScreenStateUser.Error(it))
            }
        }
    }
}
