package com.example.productapp.data.model

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("rate") val rate: Double = 0.0,
    @SerializedName("count") val count: Int = 0
)

data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("image") val image: String,
    @SerializedName("rating") val rating: Rating? = null
)
