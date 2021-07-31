package com.example.task.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.task.base.viewmodel.BaseViewModel

abstract class FragmentBaseMVVM<ViewModel : BaseViewModel, ViewBind : ViewBinding> : Fragment() {
    protected abstract val viewModel: ViewModel
    protected abstract val binding: ViewBind
    private lateinit var navController: NavController
    private val navOptions = NavOptions.Builder()
        .setLaunchSingleTop(false)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this) {
            navigateBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        retainInstance = true

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observes()
        initView(savedInstanceState)
        initViewClickListeners()
    }

    protected fun <T> observe(liveData: LiveData<T>, action: (T) -> Unit) = view?.run {
        if (!this@FragmentBaseMVVM.isAdded) return@run
        liveData.observe(viewLifecycleOwner, Observer { action(it ?: return@Observer) })
    }

    protected abstract fun initView(savedInstanceState: Bundle?)
    protected open fun initViewClickListeners() {}
    protected open fun observes() {}

    protected open fun navigateBackStack() {
        navController.popBackStack()
    }

    protected fun navigateFragment(
        destinationId: Int,
        bundle: Bundle? = null
    ) {
        navController.navigate(
            destinationId,
            bundle,
            navOptions,
        )
    }

}