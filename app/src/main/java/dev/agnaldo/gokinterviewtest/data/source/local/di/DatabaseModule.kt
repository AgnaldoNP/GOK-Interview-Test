package dev.agnaldo.gokinterviewtest.data.source.local.di

import dev.agnaldo.gokinterviewtest.data.source.local.database.MyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class DatabaseModule {

    companion object {

        val databaseModule = module {
            single { MyDatabase.getInstance(androidContext()) }
            single { inject<MyDatabase>().value.daoSpotlight() }
            single { inject<MyDatabase>().value.daoProduct() }
            single { inject<MyDatabase>().value.daoCash() }
        }

    }

}
