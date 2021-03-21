package dev.agnaldo.gokinterviewtest.ui.main

import com.google.common.truth.Truth.assertThat
import dev.agnaldo.gokinterviewtest.TestSetup
import dev.agnaldo.gokinterviewtest.common.SpannableUtils
import dev.agnaldo.gokinterviewtest.domian.entity.Cash
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight
import dev.agnaldo.gokinterviewtest.domian.usecase.ProductsUseCase
import dev.agnaldo.gokinterviewtest.setup.BaseViewModelUnitTest
import dev.agnaldo.gokinterviewtest.ui.main.MainViewModel.Event
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.inject

private const val USER_NAME = "Maria"

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainViewModelUnitTest : BaseViewModelUnitTest() {

    private val viewModel by inject<MainViewModel>()
    private val productsUseCase by inject<ProductsUseCase>()
    private val spannableUtils by inject<SpannableUtils>()

    @Before
    override fun setup() {
        super.setup()
        TestSetup.start {
            loadKoinModules(module {
                single<ProductsUseCase>(override = true) { mockk(relaxUnitFun = true) }
                single<SpannableUtils>(override = true) { mockk(relaxUnitFun = true) }
            })

            every { productsUseCase.getUserName() }.returns(USER_NAME)
            coEvery { productsUseCase.updateProducts() }.coAnswers { }
        }
    }

    @Test
    fun `should update products by init the view model`() {
        viewModel.init()
        coVerify { productsUseCase.updateProducts() }
    }

    @Test
    fun `should update user name int the presentation value`() {
        viewModel.init()
        coVerify { productsUseCase.getUserName() }
        assertThat(viewModel.presentation.value?.userName).isEqualTo(USER_NAME)
    }

    @Test
    fun `should update cash title and banner url on cash's live data changes`() {
        val cash = Cash("Nome", "URL", "Descrição")
        every { spannableUtils.colorizeSecondWordAndSoOn(any(), any()) }.returns(cash.title)

        viewModel.onCashLiveDataUpdate(cash)
        assertThat(viewModel.presentation.value?.cashTitle).isEqualTo(cash.title)
        assertThat(viewModel.presentation.value?.cashBannerURL).isEqualTo(cash.bannerURL)
    }

    @Test
    fun `should update product list on product's live data changes`() {
        val products = listOf(Product("Nome", "URL", "Descrição"))

        viewModel.onProductsLiveDataUpdate(products)
        assertThat(viewModel.presentation.value?.productsAdapter?.products).isEqualTo(products)
    }

    @Test
    fun `should update spotlight list on spotlight's live data changes`() {
        val spotlights = listOf(Spotlight("Nome", "URL", "Descrição"))

        viewModel.onSpotlightsLiveDataUpdate(spotlights)
        assertThat(viewModel.presentation.value?.spotlightsAdapter?.spotlights).isEqualTo(spotlights)
    }

    @Test
    fun `should post event to view to open cash screen`() {
        val cash = Cash("Nome", "URL", "Descrição")
        every { spannableUtils.colorizeSecondWordAndSoOn(any(), any()) }.returns(cash.title)

        viewModel.onCashLiveDataUpdate(cash)
        viewModel.presentation.value?.onCashClick?.invoke()

        val event = Event.OpenCashScreen(cash)
        runTestWithUIContext {
            assertThat(viewModel.observableEvents.value).isEqualTo(event)
        }
    }

    @Test
    fun `should post event to view to open spotlight screen`() {
        val spotlight = Spotlight("Nome", "URL", "Descrição")
        viewModel.onSpotlightsLiveDataUpdate(listOf(spotlight))

        viewModel.presentation.value?.spotlightsAdapter?.onSpotLightClick?.invoke(spotlight)

        val event = Event.OpenSpotlightScreen(spotlight)
        runTestWithUIContext {
            assertThat(viewModel.observableEvents.value).isEqualTo(event)
        }
    }

    @Test
    fun `should post event to view to open product screen`() {
        val product = Product("Nome", "URL", "Descrição")
        viewModel.onProductsLiveDataUpdate(listOf(product))

        viewModel.presentation.value?.productsAdapter?.onProductClick?.invoke(product)

        val event = Event.OpenProductScreen(product)
        runTestWithUIContext {
            assertThat(viewModel.observableEvents.value).isEqualTo(event)
        }
    }
}
