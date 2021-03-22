package dev.agnaldo.gokinterviewtest.ui.spotlight

import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.databinding.FragmentSpotlightBinding
import dev.agnaldo.gokinterviewtest.ui.base.BaseFragment
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpotlightFragment : BaseFragment<FragmentSpotlightBinding, SpotlightViewModel>() {

    override val viewModel: SpotlightViewModel by viewModel()
    override val layoutRes = R.layout.fragment_spotlight
    override val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit = {}

    override fun observeViewModelLiveData() {
        viewDataBinding.vm = viewModel
    }

}
