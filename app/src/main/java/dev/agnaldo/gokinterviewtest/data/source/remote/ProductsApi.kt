package dev.agnaldo.gokinterviewtest.data.source.remote

import dev.agnaldo.gokinterviewtest.data.source.remote.entity.ProductsResponse
import retrofit2.http.GET

interface ProductsApi {

    @GET("/sandbox/products")
    suspend fun getProducts(): ProductsResponse

}
