package dev.agnaldo.gokinterviewtest.setup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.koin.test.AutoCloseKoinTest

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
open class BaseUnitTest : AutoCloseKoinTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    open fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    fun runTestWithUIContext(block: () -> Unit) {
        runBlocking {
            launch(Dispatchers.Main) {
                block.invoke()
            }
        }
    }
}
