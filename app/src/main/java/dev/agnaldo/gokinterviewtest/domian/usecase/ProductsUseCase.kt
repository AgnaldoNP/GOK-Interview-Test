package dev.agnaldo.gokinterviewtest.domian.usecase

import androidx.lifecycle.LiveData
import dev.agnaldo.gokinterviewtest.data.repository.ProductsRepository
import dev.agnaldo.gokinterviewtest.domian.entity.Cash
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight

class ProductsUseCase(
    val productsRepository: ProductsRepository
) {
    suspend fun updateProducts() {
        productsRepository.requestAndUpdateProductsFromApi()
    }

    fun getUserName() = "Maria"

    fun getCashData(): LiveData<Cash?> = productsRepository.getCashData()

    fun getSpotLights(): LiveData<List<Spotlight>> = productsRepository.getSpotLights()

    fun getProducts(): LiveData<List<Product>> = productsRepository.getProducts()
}
