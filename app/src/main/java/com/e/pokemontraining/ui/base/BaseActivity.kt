package com.e.pokemontraining.ui.base

import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(private val ViewModel:Class<VM>) : AppCompatActivity() {
    //protected lateinit var binding:BM
    //protected lateinit var viewModel: VM
    val binding by lazy {
        DataBindingUtil.setContentView(this,getLayout()) as VDB
    }
    val viewModel by lazy {
        ViewModelProvider(this).get(ViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setuplifecycleowner()
        init()
        initviewmodel()
    }

    @LayoutRes
    protected abstract fun getLayout():Int

    private fun setuplifecycleowner(){
        binding.lifecycleOwner=this
    }

    protected abstract fun init()

    protected abstract fun initviewmodel()



}