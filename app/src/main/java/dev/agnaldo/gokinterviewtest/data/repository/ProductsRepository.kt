package dev.agnaldo.gokinterviewtest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import dev.agnaldo.gokinterviewtest.common.mapper.toDBEntities
import dev.agnaldo.gokinterviewtest.common.mapper.toDBEntity
import dev.agnaldo.gokinterviewtest.common.mapper.toEntities
import dev.agnaldo.gokinterviewtest.common.mapper.toEntity
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoCash
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoProduct
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoSpotlight
import dev.agnaldo.gokinterviewtest.data.source.remote.ProductsApi
import dev.agnaldo.gokinterviewtest.domian.entity.Cash
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight

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
                if (daoCash.countCashData() > 0) {
                    id = 1
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

    fun getCashData(): LiveData<Cash?> = liveData {
        val first = daoCash.getFirst()
        emitSource(first.map { it?.toEntity() })
    }

    fun getSpotLights(): LiveData<List<Spotlight>> = liveData {
        emitSource(daoSpotlight.getAll().map { it.toEntities() })
    }

    fun getProducts(): LiveData<List<Product>> = liveData {
        emitSource(daoProduct.getAll().map { it.toEntities() })
    }

}
