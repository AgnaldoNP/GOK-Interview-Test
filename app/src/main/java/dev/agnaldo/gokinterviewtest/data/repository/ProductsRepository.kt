package dev.agnaldo.gokinterviewtest.data.repository

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

}
