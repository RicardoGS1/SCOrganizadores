package com.virtualworld.scorganizadores.ui.screens.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.virtualworld.scorganizadores.common.ScreenStateUser
import com.virtualworld.scorganizadores.domain.entity.order.OrderInfoEntity
import com.virtualworld.scorganizadores.ui.common.Error
import com.virtualworld.scorganizadores.ui.common.Loading

@Composable
fun PaymentRoute(viewModel: OrderViewModel)
{

    val setOrderState by viewModel.setOrderState.observeAsState()
    var confirm by remember { mutableStateOf("") }

    val onCreateInfoOrderButtonClicked = { infoOrder: OrderInfoEntity ->
        viewModel.setOrder(infoOrder)
    }

    PaymentScreen(onCreateInfoOrderButtonClicked = onCreateInfoOrderButtonClicked,confirm)


    when (setOrderState) {
        is ScreenStateUser.Loading -> {
            Loading()

        }
        is ScreenStateUser.Error -> {
            Error(message = "Error")
        }
        is ScreenStateUser.Success -> {
           // navigateToSignInScreen()
            confirm="El pedido se a realisado correctamente"
        }
        else -> {

        }
    }

}

@Composable
fun PaymentScreen(onCreateInfoOrderButtonClicked: (OrderInfoEntity) -> Unit, confirm: String)
{

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Datos de envio",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp),
        )


        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Su nombre") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )


        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text(text = "Numero telefono") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )



        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text(text = "Direccion") },
            maxLines = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )



        Button(
            onClick = {

                if (name.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()

                )
                {


                    onCreateInfoOrderButtonClicked(OrderInfoEntity(name = name, phone = phone, address = address
                    )
                    )


                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Text(text = "Realizar Pedido")
        }


        Text(
            text =confirm,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp),
        )

    }
}


