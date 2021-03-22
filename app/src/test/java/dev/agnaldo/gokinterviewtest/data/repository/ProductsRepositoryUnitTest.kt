package dev.agnaldo.gokinterviewtest.data.repository

import androidx.lifecycle.liveData
import dev.agnaldo.gokinterviewtest.TestSetup
import dev.agnaldo.gokinterviewtest.common.mapper.toDBEntities
import dev.agnaldo.gokinterviewtest.common.mapper.toDBEntity
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoCash
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoProduct
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoSpotlight
import dev.agnaldo.gokinterviewtest.data.source.local.entity.CashDBEntity
import dev.agnaldo.gokinterviewtest.data.source.remote.ProductsApi
import dev.agnaldo.gokinterviewtest.data.source.remote.entity.ProductsResponse
import dev.agnaldo.gokinterviewtest.setup.BaseUnitTest
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ProductsRepositoryUnitTest : BaseUnitTest() {

    private val productsRepository by inject<ProductsRepository>()
    private val productsApi by inject<ProductsApi>()
    private val daoSpotlight by inject<DaoSpotlight>()
    private val daoProduct by inject<DaoProduct>()
    private val daoCash by inject<DaoCash>()

    private val responseMock = ProductsResponse(
        cash = ProductsResponse.CashResponse(
            title = "digio Cash",
            bannerURL = "BannerUrl",
            description = "Descrição"
        ),
        spotlights = listOf(
            ProductsResponse.SpotlightResponse(
                name = "Uber",
                bannerURL = "BannerUrl",
                description = "Descrição"
            )
        ),
        products = listOf(
            ProductsResponse.ProductResponse(
                name = "Google Play",
                imageURL = "BannerUrl",
                description = "Descrição"
            )
        )
    )


    @Before
    override fun setup() {
        super.setup()
        TestSetup.start {
            loadKoinModules(module {
                single<ProductsApi>(override = true) { mockk(relaxUnitFun = true) }
                single<DaoSpotlight>(override = true) { mockk(relaxUnitFun = true) }
                single<DaoProduct>(override = true) { mockk(relaxUnitFun = true) }
                single<DaoCash>(override = true) { mockk(relaxUnitFun = true) }
            })

            coEvery { productsApi.getProducts() }.coAnswers { responseMock }
        }
    }

    @Test
    fun `should call products from API`() {
        runBlocking { productsRepository.requestAndUpdateProductsFromApi() }
        coVerify { productsApi.getProducts() }
    }

    @Test
    fun `should save cash data to database`() {
        runBlocking { productsRepository.requestAndUpdateProductsFromApi() }
        coVerify { daoCash.insert(responseMock.cash.toDBEntity().apply { id = 1 }) }
    }

    @Test
    fun `should save products to database`() {
        runBlocking { productsRepository.requestAndUpdateProductsFromApi() }
        coVerify { daoProduct.insert(responseMock.products.toDBEntities()) }
    }

    @Test
    fun `should save spotlights to database`() {
        runBlocking { productsRepository.requestAndUpdateProductsFromApi() }
        coVerify { daoSpotlight.insert(responseMock.spotlights.toDBEntities()) }
    }

    @Test
    fun `should get cash data from database`() {
        val cashDBEntity = responseMock.cash.toDBEntity()
        every { daoCash.getFirst() }.returns(
            liveData<CashDBEntity?> { emit(cashDBEntity) })

        productsRepository.getCashData().observeForever { }
        verify { daoCash.getFirst() }
    }

    @Test
    fun `should get spotlights from database`() {
        val spotlightsDBEntity = responseMock.spotlights.toDBEntities()
        every { daoSpotlight.getAll() }.returns(
            liveData { emit(spotlightsDBEntity) })

        productsRepository.getSpotLights().observeForever { }
        verify { daoSpotlight.getAll() }
    }

    @Test
    fun `should get products from database`() {
        val productsDBEntity = responseMock.products.toDBEntities()
        every { daoProduct.getAll() }.returns(
            liveData { emit(productsDBEntity) })

        productsRepository.getProducts().observeForever { }
        verify { daoProduct.getAll() }
    }


}
