package dev.agnaldo.gokinterviewtest.ui.di

import dev.agnaldo.gokinterviewtest.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    companion object {

        val viewModelModule = module {
            viewModel { MainViewModel() }
        }

    }

}
