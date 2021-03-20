package dev.agnaldo.gokinterviewtest.ui.main

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import dev.agnaldo.gokinterviewtest.domian.entity.Cash
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel

class MainViewModel(

) : BaseViewModel() {

    val presenter = Presenter()

    fun init() {
        presenter.userName = "Maria"

        val spotligths = listOf(
            Spotlight(
                "Recarga",
                "https://s3-sa-east-1.amazonaws.com/digio-exame/recharge_banner.png",
                "Agora ficou mais fácil colocar créditos no seu celular! A digio Store traz a facilidade de fazer recargas... direto pelo seu aplicativo, com toda segurança e praticidade que você procura."
            ),
            Spotlight(
                "Recarga",
                "https://s3-sa-east-1.amazonaws.com/digio-exame/uber_banner.png",
                "Agora ficou mais fácil colocar créditos no seu celular! A digio Store traz a facilidade de fazer recargas... direto pelo seu aplicativo, com toda segurança e praticidade que você procura."
            )
        )
        postEventToView(Event.ShowSpotlights(spotligths))

        val products = listOf(
            Product(
                "XBOX",
                "https://s3-sa-east-1.amazonaws.com/digio-exame/xbox_icon.png",
                "Com o e-Gift Card Xbox você adquire créditos para comprar games, música, filmes, programas de TV e muito mais!"
            ),
            Product(
                "XBOX",
                "https://s3-sa-east-1.amazonaws.com/digio-exame/google_play_icon.png",
                "Com o e-Gift Card Xbox você adquire créditos para comprar games, música, filmes, programas de TV e muito mais!"
            ),
            Product(
                "XBOX",
                "https://s3-sa-east-1.amazonaws.com/digio-exame/level_up_icon.png",
                "Com o e-Gift Card Xbox você adquire créditos para comprar games, música, filmes, programas de TV e muito mais!"
            )
        )
        postEventToView(Event.ShowProducts(products))

        val cash = Cash(
            "digio Cash",
            "https://s3-sa-east-1.amazonaws.com/digio-exame/cash_banner.png",
            "Dinheiro na conta sem complicação. Transfira parte do limite do seu cartão para sua conta."
        )
        presenter.cashBannerURL = cash.bannerURL
        presenter.cashTitle = SpannableString(cash.title).apply {
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
    }

    sealed class Event : BaseViewModel.Event {
        data class ShowSpotlights(val spotlights: List<Spotlight>) : Event()
        data class ShowProducts(val products: List<Product>) : Event()
    }

    class Presenter {
        var userName = ""
        var cashTitle = SpannableString("")
        var cashBannerURL = ""
    }

}
