package dev.agnaldo.gokinterviewtest

import android.app.Application
import dev.agnaldo.gokinterviewtest.application.MyApplication
import io.mockk.mockk
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

object TestSetup {

    fun start(block: (KoinApplication) -> Unit) {
        val context: Application = mockk()
        startKoin {
//            androidContext(context)
            modules(MyApplication.getModules())
            block.invoke(this)
        }
    }

}
