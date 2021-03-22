package dev.agnaldo.gokinterviewtest.ui.product

import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.databinding.FragmentProductBinding
import dev.agnaldo.gokinterviewtest.ui.base.BaseFragment
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<FragmentProductBinding, ProductViewModel>() {

    override val viewModel: ProductViewModel by viewModel()
    override val layoutRes = R.layout.fragment_product
    override val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit = {}

    override fun observeViewModelLiveData() {
        viewDataBinding.vm = viewModel
    }

}
