package com.example.libraryexercise

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListHeroAdapter(private val listHero: ArrayList<Hero>): RecyclerView.Adapter<ListHeroAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
        val tvItemDescription: TextView = itemView.findViewById(R.id.tvItemDescription)
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgHeroes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (name, description, image) = listHero[position]
        holder.tvItemName.text = name
        holder.tvItemDescription.text = description
        holder.imgPhoto.setImageResource(image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, HeroDetailActivity::class.java)
            intent.putExtra(HeroDetailActivity.KEY_EXTRA, listHero[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}