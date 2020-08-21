package com.e.pokemontraining.ui.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.pokemontraining.R
import com.e.pokemontraining.databinding.ActivityMainBinding
import com.e.pokemontraining.model.api.AnimeApi
import com.e.pokemontraining.model.api.response.Anime
import com.e.pokemontraining.model.api.response.Year
import com.e.pokemontraining.ui.base.BaseActivity
import com.e.pokemontraining.ui.completed.CompletedActivity
import com.e.pokemontraining.ui.favorites.FavoritesActivity
import com.e.pokemontraining.ui.onhold.OnHoldActivity
import com.e.pokemontraining.ui.watching.WatchingActivity
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(MainViewModel::class.java),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer:DrawerLayout
    val DEFAULT_SEASON = "winter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init(){
        designpage()
        listanime(DEFAULT_SEASON)
    }

    override fun initviewmodel(){
        binding.viewmodel = viewModel
        var i=1
        val seasonobserver = Observer<String> {
            listanime(it)
        }
        viewModel.radiobutton.observe(this,seasonobserver)
    }

    fun designpage(){
        var toolbar:androidx.appcompat.widget.Toolbar = findViewById(R.id.maintoolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById<DrawerLayout>(R.id.mainlayout)
        var drawerToggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_open,R.string.navigation_close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        var navigationview =findViewById<NavigationView>(R.id.mainnavigator)
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

    fun listanime(season:String){
        var postservice = AnimeApi().build()?.create(AnimeApi.AnimeApiInterface::class.java)
        var list:MutableList<Anime> = emptyList<Anime>().toMutableList()
        var recyclerView = findViewById<RecyclerView>(R.id.listanime)
        recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = MainAdapter(list)
        var listpost = postservice?.getlist(season)
        listpost?.enqueue(object : Callback<Year>{
            override fun onFailure(call: Call<Year>, t: Throwable) {
                Log.d("gelenhata",t.message)
            }

            override fun onResponse(call: Call<Year>, response: Response<Year>) {
                var animes = response.body()?.listanime
                if (animes != null) {
                    for(anime in animes){
                        list.add(anime)
                    }
                    recyclerView.adapter = MainAdapter(list)
                }
            }
        })
    }
    

}
