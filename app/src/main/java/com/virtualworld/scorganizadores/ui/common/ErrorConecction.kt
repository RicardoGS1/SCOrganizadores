package com.virtualworld.scorganizadores.ui.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.virtualworld.scorganizadores.R


class StoreAppState(private val context: Context)
{
    var isOnline by mutableStateOf(checkIfOnline())
        private set

    private fun checkIfOnline(): Boolean
    {
        val cm = ContextCompat.getSystemService(context, ConnectivityManager::class.java)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

            val capabilities = cm?.getNetworkCapabilities(cm.activeNetwork) ?: return false
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else
        {
            cm?.activeNetworkInfo?.isConnectedOrConnecting == true
        }
    }

    fun refreshOnline()
    {
        isOnline = checkIfOnline()
    }


}


@Composable
fun rememberStoreAppState(context: Context = LocalContext.current) =

    remember(context) {
        StoreAppState(context)
    }




@Composable
fun OfflineDialog(onRetry: () -> Unit)
{
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.app_name)) },
        text = { Text(text = stringResource(R.string.no_internet_connection_dialog)) },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(R.string.retry))
            }
        },
    )
}


