package com.virtualworld.scorganizadores.common

sealed class ScreenStateProducts<out T : Any>
{
    object Loading : ScreenStateProducts<Nothing>()

    data class Error(val message: String) : ScreenStateProducts<Nothing>()

    data class Success<out T : Any>(val uiData: T) : ScreenStateProducts<T>()
}