package com.example.productapp.data.remote

import com.example.productapp.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
    }
}
