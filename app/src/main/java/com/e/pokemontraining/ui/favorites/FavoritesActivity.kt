package com.e.pokemontraining.ui.favorites

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityFavoritesBinding
import com.e.pokemontraining.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_favorites.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoritesActivity :
    BaseActivity<ActivityFavoritesBinding, FavoritesViewModel>(FavoritesViewModel::class.java) {
    private val favoritesViewModel:FavoritesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_favorites
    }

    override fun init() {
        //var list = Favorite().listanimes(this)
        initdesign()
    }

    override fun initviewmodel() {
        binding.modelView = favoritesViewModel
        var recyclerView = listanime
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        favoritesViewModel.listanime(recyclerView)
    }

    fun initdesign() {
        supportActionBar?.title = "Favorite Animes"
    }


}