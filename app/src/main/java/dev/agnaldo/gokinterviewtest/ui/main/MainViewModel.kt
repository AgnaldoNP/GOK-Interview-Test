package dev.agnaldo.gokinterviewtest.ui.main

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.agnaldo.gokinterviewtest.common.SpannableUtils
import dev.agnaldo.gokinterviewtest.domian.entity.Cash
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight
import dev.agnaldo.gokinterviewtest.domian.usecase.ProductsUseCase
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import dev.agnaldo.gokinterviewtest.ui.main.adapter.ProductsAdapter
import dev.agnaldo.gokinterviewtest.ui.main.adapter.SpotlightsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val userCase: ProductsUseCase,
    private val spannableUtils: SpannableUtils
) : BaseViewModel() {

    private var _presentation = MutableLiveData<Presentation>()
    var presentation: LiveData<Presentation> = _presentation

    fun getCashData() = userCase.getCashData()
    fun getSpotLights() = userCase.getSpotLights()
    fun getProducts() = userCase.getProducts()

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            userCase.updateProducts()
        }

        _presentation.value = getPresentation().copy(userName = userCase.getUserName())
    }

    fun onCashLiveDataUpdate(cash: Cash) {
        _presentation.value = getPresentation().copy(
            cashBannerURL = cash.bannerURL,
            cashTitle = spannableUtils.colorizeSecondWordAndSoOn(cash.title, Color.GRAY),
            onCashClick = { onCashClick(cash) }
        )
    }

    fun onSpotlightsLiveDataUpdate(spotlights: List<Spotlight>) {
        val adapter = SpotlightsAdapter(
            spotlights = spotlights,
            onSpotLightClick = { onSpotlightClick(it) }
        )

        _presentation.value = getPresentation().copy(
            spotlightsAdapter = adapter
        )
    }

    fun onProductsLiveDataUpdate(products: List<Product>) {
        val adapter = ProductsAdapter(
            products = products,
            onProductClick = { onProductClick(it) }
        )

        _presentation.value = getPresentation().copy(
            productsAdapter = adapter
        )
    }

    private fun onSpotlightClick(spotlight: Spotlight) {
        postEventToView(Event.OpenSpotlightScreen(spotlight))
    }

    private fun onProductClick(product: Product) {
        postEventToView(Event.OpenProductScreen(product))
    }

    private fun onCashClick(cash: Cash) {
        postEventToView(Event.OpenCashScreen(cash))
    }

    private fun getPresentation() = presentation.value ?: Presentation()

    sealed class Event : BaseViewModel.Event {
        data class OpenCashScreen(val cash: Cash) : Event()
        data class OpenProductScreen(val product: Product) : Event()
        data class OpenSpotlightScreen(val spotlight: Spotlight) : Event()
    }

    data class Presentation(
        val userName: String = "",
        val cashTitle: CharSequence = "",
        val cashBannerURL: String = "",
        val onCashClick: () -> Unit = {},
        val spotlightsAdapter: SpotlightsAdapter = SpotlightsAdapter(listOf()) {},
        val productsAdapter: ProductsAdapter = ProductsAdapter(listOf()) {}
    )

}
