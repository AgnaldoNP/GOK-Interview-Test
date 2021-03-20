package dev.agnaldo.gokinterviewtest.ui.main.adapter.viewhoolder

import dev.agnaldo.gokinterviewtest.databinding.ViewHolderSpotlightBinding
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight
import dev.agnaldo.gokinterviewtest.ui.base.BaseViewHolder

class SpotlightViewHolder(private val binding: ViewHolderSpotlightBinding) :
    BaseViewHolder<ViewHolderSpotlightBinding>(binding) {
    fun bind(spotlight: Spotlight) {
        binding.spotlight = spotlight
        binding.executePendingBindings()
    }
}
