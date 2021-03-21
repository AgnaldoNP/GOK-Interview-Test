package dev.agnaldo.gokinterviewtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.agnaldo.gokinterviewtest.R
import dev.agnaldo.gokinterviewtest.domian.entity.Spotlight
import dev.agnaldo.gokinterviewtest.ui.base.BaseListAdapter
import dev.agnaldo.gokinterviewtest.ui.main.adapter.viewhoolder.SpotlightViewHolder

class SpotlightsAdapter(
    private val spotlights: List<Spotlight>
) : BaseListAdapter<Spotlight, SpotlightViewHolder>(spotlights) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SpotlightViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.view_holder_spotlight, parent, false
        )
    )

    override fun onBindViewHolder(holder: SpotlightViewHolder, position: Int) {
        holder.bind(spotlights[position])
    }

}
