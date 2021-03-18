package dev.agnaldo.gokinterviewtest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    abstract val layoutRes: Int

    abstract val viewModel: V
    lateinit var viewDataBinding: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel(savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<T>(
        inflater, layoutRes, container, false
    ).apply {
        viewDataBinding = this
        bindViewModelToBinding()
        viewModel.observableEvents.observe(viewLifecycleOwner, observableViewModelEvents)
    }.root

    open fun onBackPressed() {
        findNavController().popBackStack()
    }

    open fun initViewModel(savedInstanceState: Bundle?) {}
    abstract fun bindViewModelToBinding()
    abstract val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit

}
