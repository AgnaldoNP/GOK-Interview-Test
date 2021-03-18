package dev.agnaldo.gokinterviewtest.application

import android.app.Application
import dev.agnaldo.gokinterviewtest.data.repository.di.RepositoryModule.Companion.repositoryModule
import dev.agnaldo.gokinterviewtest.data.source.local.di.DatabaseModule.Companion.databaseModule
import dev.agnaldo.gokinterviewtest.data.source.remote.di.NetworkModule.Companion.networkModule
import dev.agnaldo.gokinterviewtest.domian.di.DomainModule.Companion.domainModule
import dev.agnaldo.gokinterviewtest.ui.di.ViewModelModule.Companion.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    databaseModule,
                    networkModule,
                    domainModule
                )
            )
        }

    }

}
