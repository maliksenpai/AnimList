package com.e.pokemontraining.ui.watching

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.pokemontraining.R
import com.e.pokemontraining.model.api.response.UserAnime
import com.e.pokemontraining.ui.detail.DetailActivity
import com.e.pokemontraining.utils.Favorite

class WatchingAdapter(val list: MutableList<UserAnime>) :
    RecyclerView.Adapter<WatchingAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.listanimetitle)
        var image = itemView.findViewById<ImageView>(R.id.listanimeimage)
        var score = itemView.findViewById<TextView>(R.id.listscore)
        var episode = itemView.findViewById<TextView>(R.id.listepisode)
        var layout = itemView.findViewById<RelativeLayout>(R.id.listlayout)
        var scoretext = itemView.findViewById<TextView>(R.id.listscoretext)
        var favorite = itemView.findViewById<ImageView>(R.id.listfavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var context = holder.itemView.context
        holder.title.setText(list.get(position).title)
        holder.scoretext.setText("User Score")
        holder.episode.setText(list.get(position).watchedepisode + "/" + list.get(position).totalepisode)
        holder.score.setText(list.get(position).score)
        Glide.with(context).load(list.get(position).image).into(holder.image)
        holder.layout.setOnClickListener {
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", list.get(position).id)
            context.startActivity(intent)
        }
        holder.favorite.setOnClickListener {
            Favorite().checkfavorite(list.get(position).id, context)
        }
    }
}