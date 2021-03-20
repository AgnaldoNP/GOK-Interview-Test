package dev.agnaldo.gokinterviewtest.ui.main.adapter.viewhoolder

import dev.agnaldo.gokinterviewtest.databinding.ViewHolderProductBinding
import dev.agnaldo.gokinterviewtest.domian.entity.Product
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewHolder

class ProductViewHolder(private val binding: ViewHolderProductBinding) :
    BaseViewHolder<ViewHolderProductBinding>(binding) {
    fun bind(product: Product) {
        binding.product = product
        binding.executePendingBindings()
    }
}
