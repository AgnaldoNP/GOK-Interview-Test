package dev.agnaldo.gokinterviewtest.ui.main

import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.databinding.ActivityMainBinding
import dev.agnaldo.gokinterviewtest.ui.base.BaseActivity
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutRes = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun bindViewModelToBinding() {}

    override val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit = { event ->

    }

}
