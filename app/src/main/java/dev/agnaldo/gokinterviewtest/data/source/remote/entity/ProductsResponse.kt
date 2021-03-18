package dev.agnaldo.gokinterviewtest.data.source.remote.entity

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("spotlight")
    val spotlight: List<SpotlightResponse>,

    @SerializedName("products")
    val products: List<ProductResponse>,

    @SerializedName("cash")
    val cash: CashResponse
) {

    data class SpotlightResponse(
        @SerializedName("name")
        val name: String,

        @SerializedName("bannerURL")
        val bannerURL: String,

        @SerializedName("description")
        val description: String
    )

    data class ProductResponse(
        @SerializedName("name")
        val name: String,

        @SerializedName("imageURL")
        val imageURL: String,

        @SerializedName("description")
        val description: String
    )

    data class CashResponse(
        @SerializedName("title")
        val title: String,

        @SerializedName("bannerURL")
        val bannerURL: String,

        @SerializedName("description")
        val description: String
    )


}
