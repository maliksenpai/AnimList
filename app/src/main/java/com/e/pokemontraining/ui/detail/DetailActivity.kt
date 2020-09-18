package com.e.pokemontraining.ui.detail

import android.os.Bundle
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityDetailBinding
import com.e.pokemontraining.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(DetailViewModel::class.java) {
    private val detailViewModel:DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var id = intent.getStringExtra("id")
        detailViewModel.loadpage(detailimage, detailsynopsis, detailtitle, id)
    }


    override fun getLayout(): Int {
        return R.layout.activity_detail
    }

    override fun init() {

    }

    override fun initviewmodel() {
        binding.modelView = detailViewModel
    }
}