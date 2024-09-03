package com.virtualworld.scorganizadores.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.virtualworld.scorganizadores.common.ScreenStateProducts
import com.virtualworld.scorganizadores.ui.common.Error
import com.virtualworld.scorganizadores.ui.common.Loading
import com.virtualworld.scorganizadores.ui.theme.AppTheme
import com.virtualworld.scorganizadores.ui.uiData.ProductUiData

@Composable
fun HomeRoute(
    onProductClicked: (ProductUiData) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val productState by viewModel.products.observeAsState(initial = ScreenStateProducts.Loading)
    val categoryState by viewModel.categories.observeAsState(initial = ScreenStateProducts.Loading)

    Log.e("vista",productState.toString())

    val onCategoryClicked = { category: String ->
        viewModel.getProductsByCategory(category)
    }




    HomeScreen(
        productState = productState,
        categoryState = categoryState,
        onProductClicked = onProductClicked,
        onCategoryClicked = onCategoryClicked,
      
    )



}

@Composable
fun HomeScreen(
    productState: ScreenStateProducts<List<ProductUiData>>?,
    categoryState: ScreenStateProducts<List<String>>,
    onProductClicked: (ProductUiData) -> Unit,
    onCategoryClicked: (String) -> Unit,

    ) {
    Box(modifier = Modifier.fillMaxSize()) {


        when {
            productState is ScreenStateProducts.Success && categoryState is ScreenStateProducts.Success  -> {
                SuccessScreen(
                    productUiData = productState.uiData,
                    categoryUiData = categoryState.uiData,
                    onCategoryClicked = onCategoryClicked,
                    onProductClicked = onProductClicked,

                    )
            }

            productState is ScreenStateProducts.Error || categoryState is ScreenStateProducts.Error -> {
                Error(message = "Error")
            }

            productState is ScreenStateProducts.Loading || categoryState is ScreenStateProducts.Loading -> {
                Loading()
            }

            else -> {
                Error(message = "Error")
            }
        }
    }
}


@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    productUiData: List<ProductUiData>,
    categoryUiData: List<String>,
    onProductClicked: (ProductUiData) -> Unit = {},
    onCategoryClicked: (String) -> Unit,

) {

    Column(modifier = modifier) {

        CategoryList(
            categories = categoryUiData,
            onCategoryClicked = onCategoryClicked,
        )

        ProductList(
            products = productUiData,
            onProductClicked = onProductClicked,
        )
    }
}

@Preview
@Composable
fun LoadingItemPreview() {
    AppTheme {
        Loading()
    }
}

@Preview
@Composable
fun ErrorPreview() {
    AppTheme {
        Box {
            Error("Unexpected Error")
        }
    }
}
