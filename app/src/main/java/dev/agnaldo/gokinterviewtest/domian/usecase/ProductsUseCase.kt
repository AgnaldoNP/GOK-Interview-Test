package dev.agnaldo.gokinterviewtest.domian.usecase

import dev.agnaldo.gokinterviewtest.data.repository.ProductsRepository

class ProductsUseCase(
    val productsRepository: ProductsRepository
) {
    suspend fun updateProducts() {
        productsRepository.requestAndUpdateProductsFromApi()
    }
}
