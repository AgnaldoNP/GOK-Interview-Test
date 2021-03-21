package dev.agnaldo.gokinterviewtest.ui.main.adapter.viewholder

import dev.agnaldo.gokinterviewtest.databinding.ViewHolderSpotlightBinding
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewHolder

class SpotlightViewHolder(private val binding: ViewHolderSpotlightBinding) :
    BaseViewHolder<ViewHolderSpotlightBinding>(binding) {

    fun bind(spotlight: Spotlight, onClickListener: (Spotlight) -> Unit) {
        binding.presentation = Presentation(spotlight, onClickListener)
        binding.executePendingBindings()
    }

    class Presentation(
        var spotlight: Spotlight,
        private val onClickListener: (Spotlight) -> Unit
    ) {
        fun onClick() {
            onClickListener.invoke(spotlight)
        }
    }
}
