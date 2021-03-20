package dev.agnaldo.gokinterviewtest.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.databinding.FragmentMainBinding
import dev.agnaldo.gokinterviewtest.domian.entity.Cash
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight
import dev.agnaldo.gokinterviewtest.ui.base.BaseFragment
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewModel
import dev.agnaldo.gokinterviewtest.ui.custom.PagerMarginItemDecoration
import dev.agnaldo.gokinterviewtest.ui.main.MainViewModel.Event
import dev.agnaldo.gokinterviewtest.ui.main.adapter.ProductsAdapter
import dev.agnaldo.gokinterviewtest.ui.main.adapter.SpotlightAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes = R.layout.fragment_main

    override fun bindViewModelToBinding() {
        viewDataBinding.vm = viewModel
    }

    override val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit = { event ->
        when (event) {
            is Event.ShowSpotlights -> showSpotlights(event.spotlights)
            is Event.ShowProducts -> showProducts(event.products)
        }
    }

    override fun initViewModel(savedInstanceState: Bundle?) {
        super.initViewModel(savedInstanceState)
        viewModel.init()
    }

    private fun showProducts(products: List<Product>) {
        rvProducts?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = ProductsAdapter(products)
        }
    }

    private fun showSpotlights(spotlights: List<Spotlight>) {
        rvSpotlights?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            PagerSnapHelper().attachToRecyclerView(this)
            adapter = SpotlightAdapter(spotlights)
            addItemDecoration(
                PagerMarginItemDecoration(
                    horizontalMargin = 0,
                    adjacentVisibleSize = 150
                )
            )
        }
    }

}
