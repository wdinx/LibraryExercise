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
import com.example.libraryexercise.databinding.ActivityHeroDetailBinding
import com.example.libraryexercise.databinding.ItemRowHeroBinding
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL

class HeroDetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_EXTRA = "key_extra"
    }

    private lateinit var binding: ActivityHeroDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_EXTRA, Hero::class.java)
        } else {
            intent.getParcelableExtra(KEY_EXTRA)
        }
//        findViewById<ImageView>(R.id.imgHero).setImageBitmap(dataHero!!.image)

        val imgHero = intent.getStringExtra(dataHero!!.image)
        if (imgHero != null) {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val bitmap = loadImageFromUrl(imgHero)
                    binding.imgHero.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(
                        this@HeroDetailActivity,
                        "Failed to Load Data",
                        Toast.LENGTH_SHORT
                    ).show()
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