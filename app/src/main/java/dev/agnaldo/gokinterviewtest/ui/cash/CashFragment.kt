package dev.agnaldo.gokinterviewtest.ui.cash

import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.databinding.FragmentCashBinding
import dev.agnaldo.gokinterviewtest.ui.base.BaseFragment
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import dev.agnaldo.gokinterviewtest.ui.product.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CashFragment : BaseFragment<FragmentCashBinding, ProductViewModel>() {

    override val viewModel: ProductViewModel by viewModel()
    override val layoutRes = R.layout.fragment_cash
    override val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit = {}

    override fun observeViewModelLiveData() {
        viewDataBinding.vm = viewModel
    }

}
