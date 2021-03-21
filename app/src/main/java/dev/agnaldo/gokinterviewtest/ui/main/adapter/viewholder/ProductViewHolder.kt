package dev.agnaldo.gokinterviewtest.ui.main.adapter.viewholder

import dev.agnaldo.gokinterviewtest.databinding.ViewHolderProductBinding
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewHolder

class ProductViewHolder(private val binding: ViewHolderProductBinding) :
    BaseViewHolder<ViewHolderProductBinding>(binding) {
    fun bind(product: Product, onProductClick: (Product) -> Unit) {
        binding.presentation = Presentation(product, onProductClick)
        binding.executePendingBindings()
    }

    class Presentation(
        var product: Product,
        private val onClickListener: (Product) -> Unit
    ) {
        fun onClick() {
            onClickListener.invoke(product)
        }
    }
}
