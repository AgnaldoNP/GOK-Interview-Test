package dev.agnaldo.gokinterviewtest.domain.usecase

import androidx.lifecycle.liveData
import dev.agnaldo.gokinterviewtest.TestSetup
import dev.agnaldo.gokinterviewtest.data.repository.ProductsRepository
import dev.agnaldo.gokinterviewtest.domian.usecase.ProductsUseCase
import dev.agnaldo.gokinterviewtest.setup.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
class ProductsUseCaseUnitTest : BaseUnitTest() {

    private val productsUseCase by inject<ProductsUseCase>()
    private val productsRepository by inject<ProductsRepository>()

    @Before
    override fun setup() {
        super.setup()
        TestSetup.start {
            loadKoinModules(module {
                single<ProductsRepository>(override = true) { mockk(relaxUnitFun = true) }
            })
        }
    }

    @Test
    fun `should call update products from repository`() {
        runBlocking { productsUseCase.updateProducts() }
        coVerify { productsRepository.requestAndUpdateProductsFromApi() }
    }

    @Test
    fun `should call cash data from repository`() {
        coEvery { productsRepository.getCashData() }.returns(liveData { })

        runBlocking { productsUseCase.getCashData() }
        coVerify { productsRepository.getCashData() }
    }

    @Test
    fun `should call spotlights from repository`() {
        coEvery { productsRepository.getSpotLights() }.returns(liveData { })

        runBlocking { productsUseCase.getSpotLights() }
        coVerify { productsRepository.getSpotLights() }
    }


    @Test
    fun `should call products from repository`() {
        coEvery { productsRepository.getSpotLights() }.returns(liveData { })

        runBlocking { productsUseCase.getSpotLights() }
        coVerify { productsRepository.getSpotLights() }
    }
}
