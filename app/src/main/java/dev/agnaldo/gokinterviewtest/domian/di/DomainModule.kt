package dev.agnaldo.gokinterviewtest.domian.di

import dev.agnaldo.gokinterviewtest.domian.usecase.ProductsUseCase
import org.koin.dsl.module

class DomainModule {
    companion object {

        val domainModule = module {
            single { ProductsUseCase(productsRepository = get()) }
        }

    }

}
