package dev.agnaldo.gokinterviewtest.ui.main

import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.databinding.FragmentMainBinding
import dev.agnaldo.gokinterviewtest.ui.base.BaseFragment
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes = R.layout.fragment_main

    override fun bindViewModelToBinding() {
        viewDataBinding.vm = viewModel
    }

    override val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit = { event ->

    }


}
