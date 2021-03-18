package dev.agnaldo.gokinterviewtest.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import dev.agnaldo.gokinterviewtest.R

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    abstract val layoutRes: Int

    abstract val viewModel: V
    lateinit var viewDataBinding: T

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
    }

    private val navController by lazy { navHostFragment?.navController }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel(savedInstanceState)

        DataBindingUtil.setContentView<T>(this, layoutRes).apply {
            viewDataBinding = this
            bindViewModelToBinding()
            lifecycleOwner?.let {
                viewModel.observableEvents.observe(it, observableViewModelEvents)
            }
        }
    }

    open fun initViewModel(savedInstanceState: Bundle?) {}
    abstract fun bindViewModelToBinding()
    abstract val observableViewModelEvents: (event: BaseViewModel.Event) -> Unit

}
