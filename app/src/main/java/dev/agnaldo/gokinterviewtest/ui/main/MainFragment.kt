package dev.agnaldo.gokinterviewtest.ui.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.databinding.FragmentMainBinding
import dev.agnaldo.gokinterviewtest.ui.base.BaseFragment
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import dev.agnaldo.gokinterviewtest.ui.custom.PagerMarginItemDecoration
import dev.agnaldo.gokinterviewtest.ui.main.adapter.ProductsAdapter
import dev.agnaldo.gokinterviewtest.ui.main.adapter.SpotlightsAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }

    override val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit = { event ->
    }

    override fun observeViewModelLiveData() {
        viewDataBinding.vm = viewModel
        viewModel.presentation.observe(viewLifecycleOwner) {
            viewDataBinding.presentation = it
        }
        viewModel.getCashData().observe(viewLifecycleOwner) { cash ->
            cash?.let { viewModel.updateCashInfo(it) }
        }
        viewModel.getSpotLights().observe(viewLifecycleOwner) { spotlights ->
            viewModel.onSpotlightsChange(spotlights)
        }
        viewModel.getProducts().observe(viewLifecycleOwner) { products ->
            viewModel.onProductsChange(products)
        }
    }

    override fun initViewModel(savedInstanceState: Bundle?) {
        super.initViewModel(savedInstanceState)
        viewModel.init()
    }

    private fun setupRecyclerViews() {
        rvProducts?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        rvSpotlights?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            PagerSnapHelper().attachToRecyclerView(this)
            addItemDecoration(
                PagerMarginItemDecoration(
                    horizontalMargin = 0,
                    adjacentVisibleSize = requireContext().resources.getDimensionPixelSize(
                        R.dimen.page_adjacent_margin
                    )
                )
            )
        }
    }

}
