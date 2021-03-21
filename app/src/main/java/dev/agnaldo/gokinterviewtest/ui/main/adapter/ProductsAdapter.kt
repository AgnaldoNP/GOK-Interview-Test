package dev.agnaldo.gokinterviewtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.ui.base.BaseListAdapter
import dev.agnaldo.gokinterviewtest.ui.main.adapter.viewholder.ProductViewHolder

class ProductsAdapter(
    private val products: List<Product>,
    private val onProductClick: (Product) -> Unit
) : BaseListAdapter<Product, ProductViewHolder>(products) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.view_holder_product, parent, false
        )
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], onProductClick)
    }

}
