package dev.agnaldo.gokinterviewtest.common.di

import dev.agnaldo.gokinterviewtest.common.SpannableUtils
import org.koin.dsl.module

class CommonModule {
    companion object {

        val commonModule = module {
            single { SpannableUtils() }
        }

    }

}
