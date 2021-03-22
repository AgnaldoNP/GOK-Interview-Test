package dev.agnaldo.gokinterviewtest.ui.di

import dev.agnaldo.gokinterviewtest.ui.cash.CashViewModel
import dev.agnaldo.gokinterviewtest.ui.product.ProductViewModel
import dev.agnaldo.gokinterviewtest.ui.main.MainViewModel
import dev.agnaldo.gokinterviewtest.ui.spotlight.SpotlightViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    companion object {

        val viewModelModule = module {
            viewModel { MainViewModel(get(), get()) }
            viewModel { ProductViewModel() }
            viewModel { CashViewModel() }
            viewModel { SpotlightViewModel() }
        }

    }

}
