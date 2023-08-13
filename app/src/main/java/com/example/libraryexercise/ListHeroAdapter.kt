package com.example.libraryexercise

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.libraryexercise.databinding.ItemRowHeroBinding

class ListHeroAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<ListHeroAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root) {
//        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
//        val tvItemDescription: TextView = itemView.findViewById(R.id.tvItemDescription)
//        val imgPhoto: ImageView = itemView.findViewById(R.id.imgHeroes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (name, description, image) = listHero[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.binding.imgHeroes)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, HeroDetailActivity::class.java)
            intent.putExtra(HeroDetailActivity.KEY_EXTRA, listHero[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}