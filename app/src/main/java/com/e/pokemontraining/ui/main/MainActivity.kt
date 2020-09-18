package com.e.pokemontraining.ui.main


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityMainBinding
import com.e.pokemontraining.ui.base.BaseActivity
import com.e.pokemontraining.ui.completed.CompletedActivity
import com.e.pokemontraining.ui.favorites.FavoritesActivity
import com.e.pokemontraining.ui.onhold.OnHoldActivity
import com.e.pokemontraining.ui.watching.WatchingActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


public class MainActivity :
    BaseActivity<ActivityMainBinding,MainViewModel>(MainViewModel::class.java),
    NavigationView.OnNavigationItemSelectedListener {

    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var drawer: DrawerLayout
    val DEFAULT_SEASON = "winter"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        designpage()

        var recyclerView = listanime
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        //viewModel.listanime(DEFAULT_SEASON, recyclerView)
        mainViewModel.listanime(DEFAULT_SEASON,recyclerView)
    }

    override fun initviewmodel() {
        //binding.viewmodel = viewModel
        binding.viewmodel = mainViewModel
        var i = 1
        val seasonobserver = Observer<String> {
            var recyclerView = listanime
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            //viewModel.listanime(it, recyclerView)
            mainViewModel.listanime(it,recyclerView)
        }
        //viewModel.radiobutton.observe(this, seasonobserver)
        mainViewModel.radiobutton.observe(this,seasonobserver)
    }

    fun designpage() {
        var toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.maintoolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById<DrawerLayout>(R.id.mainlayout)
        var drawerToggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_open,
            R.string.navigation_close
        )
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        var navigationview = findViewById<NavigationView>(R.id.mainnavigator)
        navigationview.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuseason -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.menucompleted -> {
                startActivity(Intent(this, CompletedActivity::class.java))
            }
            R.id.menufavorites -> {
                startActivity(Intent(this, FavoritesActivity::class.java))
            }
            R.id.menuonhold -> {
                startActivity(Intent(this, OnHoldActivity::class.java))
            }
            R.id.menuwatching -> {
                startActivity(Intent(this, WatchingActivity::class.java))
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


}
