package com.example.productapp.data.repository

import com.example.productapp.data.model.Product
import com.example.productapp.data.remote.FakeStoreApi
import com.example.productapp.data.remote.RetrofitInstance
import java.io.IOException
import retrofit2.HttpException

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val message: String) : DataResult<Nothing>()
}

/**
 * Repository separates API logic from UI (ViewModels).
 * Maps network/HTTP exceptions to user-friendly messages.
 */
class ProductRepository(
    private val api: FakeStoreApi = RetrofitInstance.api
) {

    suspend fun getProducts(): DataResult<List<Product>> {
        return try {
            val products = api.getProducts()
            DataResult.Success(products)
        } catch (e: IOException) {
            DataResult.Error("Unable to connect to the server. Please check your internet connection and try again.")
        } catch (e: HttpException) {
            DataResult.Error("Unable to connect to the server. Please check your internet connection and try again.")
        } catch (e: Exception) {
            DataResult.Error("Something went wrong. Please try again.")
        }
    }

    suspend fun getProductById(id: Int): DataResult<Product> {
        return try {
            DataResult.Success(api.getProductById(id))
        } catch (e: IOException) {
            DataResult.Error("Unable to connect to the server. Please check your internet connection and try again.")
        } catch (e: HttpException) {
            DataResult.Error("Unable to connect to the server. Please check your internet connection and try again.")
        } catch (e: Exception) {
            DataResult.Error("Something went wrong. Please try again.")
        }
    }
}
