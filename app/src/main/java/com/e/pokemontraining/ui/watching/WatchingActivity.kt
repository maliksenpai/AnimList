package com.e.pokemontraining.ui.watching

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityWatchingBinding
import com.e.pokemontraining.ui.base.BaseActivity
import com.e.pokemontraining.ui.nickname.NicknameActivity
import com.e.pokemontraining.utils.Nickname
import kotlinx.android.synthetic.main.activity_watching.*
import org.koin.android.viewmodel.ext.android.viewModel

class WatchingActivity :
    BaseActivity<ActivityWatchingBinding, WatchingViewModel>(WatchingViewModel::class.java) {

    private val watchingViewModel:WatchingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("gelen", "alo")
        init()
    }

    override fun getLayout(): Int {
        return R.layout.activity_watching
    }

    override fun init() {
        checknickname()
        initdesign()
    }

    override fun initviewmodel() {
        binding.modelView = watchingViewModel
    }

    fun initdesign() {
        supportActionBar?.title = "Watching Animes"
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
            watchingViewModel.listanime(Nickname().getnickname(this)!!, recyclerView)
        }
    }


}