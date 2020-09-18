package com.e.pokemontraining.ui.nickname

import android.os.Bundle
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityNicknameBinding
import com.e.pokemontraining.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class NicknameActivity :
    BaseActivity<ActivityNicknameBinding, NicknameViewModel>(NicknameViewModel::class.java) {
    private val nicknameViewModel :NicknameViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_nickname
    }

    override fun init() {

    }

    override fun initviewmodel() {
        binding.viewModel = nicknameViewModel
    }
}