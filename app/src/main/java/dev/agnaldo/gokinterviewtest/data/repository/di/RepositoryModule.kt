package dev.agnaldo.gokinterviewtest.data.repository.di

import dev.agnaldo.gokinterviewtest.data.repository.ProductsRepository
import org.koin.dsl.module

class RepositoryModule {
    companion object {

        val repositoryModule = module {
            factory {
                ProductsRepository(
                    productsApi = get(),
                    daoSpotlight = get(),
                    daoProduct = get(),
                    daoCash = get()
                )
            }
        }

    }

}
