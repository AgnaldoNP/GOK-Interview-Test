package dev.agnaldo.gokinterviewtest.ui.main

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.agnaldo.gokinterviewtest.domian.entity.Cash
import dev.agnaldo.gokinterviewtest.domian.usecase.ProductsUseCase
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val userCase: ProductsUseCase
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

    fun updateCashInfo(cash: Cash) {
        _presentation.value = getPresentation().copy(
            cashBannerURL = cash.bannerURL,
            cashTitle = SpannableString(cash.title).apply {
                val split = cash.title.trim().split(" ")
                if (split.count() > 1) {
                    setSpan(
                        ForegroundColorSpan(Color.GRAY),
                        split[0].length,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
        )
    }

    private fun getPresentation() = presentation.value ?: Presentation()

    sealed class Event : BaseViewModel.Event

    data class Presentation(
        val userName: String = "",
        val cashTitle: SpannableString = SpannableString(""),
        val cashBannerURL: String = ""
    )

}
