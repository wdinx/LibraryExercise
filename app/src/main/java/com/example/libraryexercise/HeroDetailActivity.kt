package com.example.libraryexercise

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL

class HeroDetailActivity : AppCompatActivity() {

    private lateinit var imgViewHero: ImageView
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
//        findViewById<ImageView>(R.id.imgHero).setImageBitmap(dataHero!!.image)

        imgViewHero = findViewById(R.id.imgHero)
        val imgHero = intent.getStringExtra(dataHero!!.image)
        if (imgHero != null){
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val bitmap = loadImageFromUrl(imgHero)
                    imgViewHero.setImageBitmap(bitmap)
                }catch (e: Exception){
                    Toast.makeText(this@HeroDetailActivity, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                }
            }
        }
        findViewById<TextView>(R.id.tvHeroName).text = dataHero.name
        findViewById<TextView>(R.id.tvHeroDescription).text = dataHero.description
    }

    private fun loadImageFromUrl(url: String): Bitmap {
        val inputStream: InputStream = URL(url).openStream()
        return BitmapFactory.decodeStream(inputStream)
    }
}