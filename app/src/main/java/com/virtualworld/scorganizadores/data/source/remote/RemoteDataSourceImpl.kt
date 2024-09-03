package com.virtualworld.scorganizadores.data.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.data.api.ApiService
import com.virtualworld.scorganizadores.data.dto.Order
import com.virtualworld.scorganizadores.data.dto.Product
import com.virtualworld.scorganizadores.data.dto.Products
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService, private val firestore: FirebaseFirestore
) : RemoteDataSource {

    /*
    suspend fun addFirestore(){



            for (i in 1..20){
        val result = firestore.collection("Productos").add(


                Product(
                    brand = "no se",
                    category = "Zapatera",
                    description = "description",
                    discountPercentage = 0.0,
                    id = i,
                    images = "images",
                    price = 1000,
                    rating = 5.0,
                    stock = 8,
                    thumbnail = "thumbnail",
                    title = "Zapatera de x piso",
                )





        )
    }

    }


     */

    override fun getProductsListFromApi(): Flow<NetworkResponseState<Products>> = flow {
        emit(NetworkResponseState.Loading)
        try {

            val products = getProductsFromFirestore()
            emit(NetworkResponseState.Success(products))
            println(products.toString())
        } catch (e: IOException) {
            emit(NetworkResponseState.Error(e))
        }
    }


    private suspend fun getProductsFromFirestore(): Products {

        val response = mutableListOf<Product>()

        val result = firestore.collection("Productos").get().await()



        for (document in result.documents) {

            println(document.getString("title"))

            val a = Product(
                brand = document.getString("brand") ?: "",
                category = document.getString("category") ?: "",
                description = document.getString("description") ?: "",
                discountPercentage = document.getDouble("discountPercentage") ?: 0.0,
                id = (document.getDouble("id") ?: 0.0).toInt(),
                images = document.getString("images") ?: "",
                price = (document.getDouble("price") ?: 0.0).toInt(),
                rating = document.getDouble("rating") ?: 0.0,
                stock = (document.getDouble("stock") ?: 0.0).toInt(),
                thumbnail = document.getString("thumbnail") ?: "",
                title = document.getString("title") ?: "",
            )

            response.add(a)
        }

        return Products(products = response)
    }

//------------------------------------------------------------------------------------------

    override fun getAllCategoriesListFromApi(): Flow<NetworkResponseState<List<String>>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                //val response = apiService.getAllCategoriesListFromApi()
                val response = getCategoriesFromFirestore()
                println("DataSource $response")
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }


    private suspend fun getCategoriesFromFirestore(): List<String> {

        val response = mutableListOf<String>()

        val result = firestore.collection("Productos").get().await()
        for (document in result.documents) {

            var add = true
            for (i in response) {
                if (document.getString("category") == i)
                    add = false
            }
            if (add) {
                response.add(document.getString("category") ?: "")
            }

        }

        return response
    }

    //------------------------------------------------------------------------------------------

    override fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<NetworkResponseState<Products>> {
        return flow {


            try {
                emit(NetworkResponseState.Loading)
                // val response = apiService.getProductsListByCategoryNameFromApi(categoryName)


                val response = getProductsListByCategoryNameFromFirebase(categoryName)

                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    private suspend fun getProductsListByCategoryNameFromFirebase(categoryName: String): Products {

        val response = mutableListOf<Product>()

        val result =
            firestore.collection("Productos").whereEqualTo("category", categoryName).get().await()

        for (document in result.documents) {

            println(document.getString("title"))

            val a = Product(
                brand = document.getString("brand") ?: "",
                category = document.getString("category") ?: "",
                description = document.getString("description") ?: "",
                discountPercentage = document.getDouble("discountPercentage") ?: 0.0,
                id = (document.getDouble("id") ?: 0.0).toInt(),
                images = document.getString("images") ?: "",
                price = (document.getDouble("price") ?: 0.0).toInt(),
                rating = document.getDouble("rating") ?: 0.0,
                stock = (document.getDouble("stock") ?: 0.0).toInt(),
                thumbnail = document.getString("thumbnail") ?: "",
                title = document.getString("title") ?: "",
            )

            response.add(a)
        }

        return Products(products = response)
    }

    //-----------------------------------------------------------------------------------

    override fun setOrderedListForApi(listOrder: List<Order>): Flow<NetworkResponseState<String>> {


        return flow {

            println(listOrder.toString())
            emit(NetworkResponseState.Loading)
            try {


               // val response = apiService.setSentOrderForApi(Orders(listOrder))

                val a = setOrderedListForFirestore(listOrder)


                emit(NetworkResponseState.Success(a))
                println("exito")

            } catch (e: Exception) {
                println("error")
                emit(NetworkResponseState.Error(e))

            }
        }


    }

    suspend fun setOrderedListForFirestore(listOrder: List<Order>): String {

        listOrder.forEach { order ->

            firestore.collection("Orders").add(
                Order(

                    name = order.name,
                    address = order.address,
                    phone = order.phone,
                    product_id = order.product_id,
                    product_quantity = order.product_quantity,
                    product_title = order.product_title,
                    product_price = order.product_price,

                    )
            ).await()
        }

        return "ok"
    }


    //-----------------------------------------------------------------------------------

    override fun getSingleProductByIdFromApi(productId: Int): Flow<NetworkResponseState<Product>> {
        return flow {
            try {
                emit(NetworkResponseState.Loading)

                val a = getSingleProductByIdFromFirebase(productId)


                //  val response = apiService.getSingleProductByIdFromApi(productId)
                emit(NetworkResponseState.Success(a))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }

    private suspend fun getSingleProductByIdFromFirebase(productId: Int): Product {

        println("nnnnnnnn" + productId.toString())
        val result = firestore.collection("Productos").whereEqualTo("id", productId).get().await()


        println(result.documents[0].getString("brand") ?: "")


        val a = Product(
            brand = result.documents[0].getString("brand") ?: "",
            category = result.documents[0].getString("category") ?: "",
            description = result.documents[0].getString("description") ?: "",
            discountPercentage = result.documents[0].getDouble("discountPercentage") ?: 0.0,
            id = (result.documents[0].getDouble("id") ?: 0.0).toInt(),
            images = result.documents[0].getString("images") ?: "",
            price = (result.documents[0].getDouble("price") ?: 0.0).toInt(),
            rating = result.documents[0].getDouble("rating") ?: 0.0,
            stock = (result.documents[0].getDouble("stock") ?: 0.0).toInt(),
            thumbnail = result.documents[0].getString("thumbnail") ?: "",
            title = result.documents[0].getString("title") ?: "",

            )

        return a
    }

    //----------------------------------------------------------------------------------


    override fun getProductsListBySearchFromApi(query: String): Flow<NetworkResponseState<Products>> {
        return flow {
            emit(NetworkResponseState.Loading)
            try {
                val response = apiService.getProductsListBySearchFromApi(query)
                emit(NetworkResponseState.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponseState.Error(e))
            }
        }
    }


}