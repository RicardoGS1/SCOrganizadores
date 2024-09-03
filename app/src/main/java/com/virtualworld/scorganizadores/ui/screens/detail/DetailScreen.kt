package com.virtualworld.scorganizadores.ui.screens.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.virtualworld.scorganizadores.common.ScreenStateProducts
import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import com.virtualworld.scorganizadores.ui.common.Error
import com.virtualworld.scorganizadores.ui.common.Loading
import com.virtualworld.scorganizadores.ui.uiData.DetailProductUiData
import com.virtualworld.scorganizadores.ui.screens.cart.CartViewModel

@Composable
fun DetailRoute(viewModel: DetailViewModel = hiltViewModel(), onBadgeCountChange: (Int) -> Unit, cartViewModel: CartViewModel = hiltViewModel() ) {



    val product by viewModel.product.observeAsState(initial = ScreenStateProducts.Loading)

    val onAddToCartButtonClicked: (UserCartEntity) -> Unit =
        { userCart ->
            viewModel.addToCart(userCart)
            onBadgeCountChange(cartViewModel.badgeCount.value.plus(1))
        }

    val onAddToFavoritesButtonClicked: (UserCartEntity) -> Unit =
        { userCartUiData -> viewModel.addToFavorite(userCartUiData) }


    ProductDetailScreen(
        product = product,
        onAddToCartButtonClicked = onAddToCartButtonClicked,
        onAddToFavoritesButtonClicked = onAddToFavoritesButtonClicked,
    )
}

@Composable
fun ProductDetailScreen(
    product: ScreenStateProducts<DetailProductUiData>,
    onAddToCartButtonClicked: (UserCartEntity) -> Unit,
    onAddToFavoritesButtonClicked: (UserCartEntity) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (product) {
            is ScreenStateProducts.Error -> Error(message = product.message)
            ScreenStateProducts.Loading -> Loading()
            is ScreenStateProducts.Success -> SuccessScreen(
                uiData = product.uiData,
                modifier = Modifier.padding(16.dp),
                onAddToCartButtonClicked = onAddToCartButtonClicked,
                onAddToFavoritesButtonClicked = onAddToFavoritesButtonClicked,
                )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuccessScreen(
    uiData: DetailProductUiData,
    modifier: Modifier = Modifier,
    onAddToCartButtonClicked: (UserCartEntity) -> Unit,
    onAddToFavoritesButtonClicked: (UserCartEntity) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top,
        ) {
            val state = rememberPagerState { 1 }
            val pageCount = 1
            HorizontalPager(
                state = state,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) { page ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = uiData.imageUrl,
                    contentDescription = uiData.title,
                )
            }
            Row(
                Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
            ) {
                repeat(pageCount) { iteration ->
                    val color =
                        if (state.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .size(20.dp)
                            .background(color = color),

                        )
                }
            }
            Text(
                text = uiData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
            )

            Text(
                text = uiData.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
            )

            Text(
                text = "${uiData.price} MN",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    onClick = {
                        onAddToCartButtonClicked(
                            UserCartEntity(
                                userId = "",
                                productId = uiData.productId,
                                quantity = 1,
                                price = uiData.price.toInt(),
                                title = uiData.title,
                                image = uiData.imageUrl,
                            ),
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                ) {
                    Text(text = "Agregar al pedido")
                }

                Button(
                    onClick = {
                        onAddToFavoritesButtonClicked(
                            UserCartEntity(
                                userId = "",
                                productId = uiData.productId,
                                quantity = 1,
                                price = uiData.price.toInt(),
                                title = uiData.title,
                                image = uiData.imageUrl,
                            ),
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                ) {
                    Text(text = "Agregar a Favorito")
                }
            }
        }
    }
}
