package com.e.pokemontraining.ui.nickname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityNicknameBinding
import com.e.pokemontraining.ui.base.BaseActivity

class NicknameActivity : BaseActivity<ActivityNicknameBinding,NicknameViewModel>(NicknameViewModel::class.java) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_nickname
    }

    override fun init() {

    }

    override fun initviewmodel() {
        binding.viewModel=viewModel
    }
}