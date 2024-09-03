package com.virtualworld.scorganizadores.common

sealed class ScreenStateUser {

    object Loading : ScreenStateUser()

    data class Error(val message: String) : ScreenStateUser()

    object Success : ScreenStateUser()
}