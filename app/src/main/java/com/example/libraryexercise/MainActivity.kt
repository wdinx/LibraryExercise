package com.example.libraryexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView
    private val list: ArrayList<Hero> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rvHeroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showReciclerList()

    }

    private fun showReciclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroManager = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroManager
    }

    private fun getListHeroes(): Collection<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listHero = ArrayList<Hero>()
        for (i in dataName.indices){
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero
    }
}