package dev.agnaldo.gokinterviewtest.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : ViewDataBinding>(
    private val binding: T
) : RecyclerView.ViewHolder(binding.root)
