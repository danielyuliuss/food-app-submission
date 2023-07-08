package com.danielys.a08_finalsubmission

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.danielys.a08_finalsubmission.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val FOOD_OBJECT = "food_object"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail"

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val food = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(FOOD_OBJECT, Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(FOOD_OBJECT)
        }

        Log.d("Detail", "onCreate: value intent is $food")


        if (food != null) {
            showData(food)
        }

//        val tvJudul:TextView = findViewById(R.id.tv_judul)
//        tvJudul.text = food!!.name
        binding.btnShare.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,"${food?.name} \n\n ${food?.description}")
            intent.type= "text/plain"
            startActivity(Intent.createChooser(intent,"ShareArtikel"))
        }


    }

    fun showData(food:Food){
        binding.tvJudul.text = food.name
        binding.tvDescription.text = food.description
        Glide.with(this).load(food.photo).into(binding.imgPhoto)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== android.R.id.home){finish()}
        return super.onOptionsItemSelected(item)
    }


}