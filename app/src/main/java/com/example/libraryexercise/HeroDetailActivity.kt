package com.example.libraryexercise

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout

class HeroDetailActivity : AppCompatActivity() {
    companion object{
        const val KEY_EXTRA = "key_extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)



        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_EXTRA, Hero::class.java)
        } else {
            intent.getParcelableExtra(KEY_EXTRA)
        }
        findViewById<ImageView>(R.id.imgHero).setImageResource(dataHero!!.image)
        findViewById<TextView>(R.id.tvHeroName).text = dataHero.name
        findViewById<TextView>(R.id.tvHeroDescription).text = dataHero.description
    }
}