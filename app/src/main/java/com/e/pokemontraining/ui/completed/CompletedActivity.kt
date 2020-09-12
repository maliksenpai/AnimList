package com.e.pokemontraining.ui.completed

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityCompletedBinding
import com.e.pokemontraining.ui.base.BaseActivity
import com.e.pokemontraining.ui.nickname.NicknameActivity
import com.e.pokemontraining.utils.Nickname
import kotlinx.android.synthetic.main.activity_completed.*

class CompletedActivity :
    BaseActivity<ActivityCompletedBinding, CompletedViewModel>(CompletedViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_completed
    }

    override fun init() {
        checknickname()
        initdesign()
    }

    override fun initviewmodel() {
        binding.modelView = viewModel
    }

    fun initdesign() {
        supportActionBar?.title = "Completed Animes"
    }

    fun checknickname() {
        if (Nickname().getnickname(this)?.isEmpty()!!) {
            startActivity(Intent(this, NicknameActivity::class.java))
        } else {
            var recyclerView = listanime
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerView.layoutManager = LinearLayoutManager(this)
            viewModel.listanime(recyclerView, Nickname().getnickname(this)!!)
        }
    }


}