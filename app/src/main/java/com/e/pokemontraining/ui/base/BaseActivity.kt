package com.e.pokemontraining.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.koin.android.viewmodel.ext.android.viewModel

public abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(ViewModel: Class<VM>) :
    AppCompatActivity() {
    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayout()) as VDB
    }
    /*val viewModel by lazy {
        ViewModelProvider(this).get(ViewModel)
    }*/

    val viewModel: BaseViewModel by viewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setuplifecycleowner()
        init()
        initviewmodel()
    }

    @LayoutRes
    protected abstract fun getLayout(): Int

    private fun setuplifecycleowner() {
        binding.lifecycleOwner = this
    }

    protected abstract fun init()

    protected abstract fun initviewmodel()


}