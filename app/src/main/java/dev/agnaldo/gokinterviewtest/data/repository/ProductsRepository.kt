package dev.agnaldo.gokinterviewtest.data.repository

import dev.agnaldo.gokinterviewtest.common.mapper.toDBEntities
import dev.agnaldo.gokinterviewtest.common.mapper.toDBEntity
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoCash
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoProduct
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoSpotlight
import dev.agnaldo.gokinterviewtest.data.source.remote.ProductsApi

class ProductsRepository(
    private val productsApi: ProductsApi,
    private val daoSpotlight: DaoSpotlight,
    private val daoProduct: DaoProduct,
    private val daoCash: DaoCash
) {

    suspend fun requestAndUpdateProductsFromApi() {
        val productsResponse = productsApi.getProducts()

        productsResponse.cash.toDBEntity().let {
            daoCash.insert(it.apply {
                if (daoCash.hasCashData()) {
                    id = 0
                }
            })
        }

        productsResponse.spotlights.toDBEntities().let {
            daoSpotlight.insert(it)
        }

        productsResponse.products.toDBEntities().let {
            daoProduct.insert(it)
        }
    }

}
