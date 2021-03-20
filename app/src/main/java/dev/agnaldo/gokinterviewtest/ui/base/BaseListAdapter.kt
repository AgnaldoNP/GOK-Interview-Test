package dev.agnaldo.gokinterviewtest.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, VH : BaseViewHolder<*>>(
    private val objs: List<T>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount() = objs.size

}
