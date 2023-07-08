package com.danielys.a08_finalsubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danielys.a08_finalsubmission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var rvFood: RecyclerView
    private val list = ArrayList<Food>()

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Makanan Indonesia"

        binding = ActivityMainBinding.inflate(layoutInflater)

        rvFood = findViewById(R.id.rv_food)
        rvFood.setHasFixedSize(true)

        list.addAll(getFoodList())
        showRecyclerList()

    }

    private fun getFoodList(): ArrayList<Food>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescryption = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices)
        {
            val food = Food(dataName[i],dataDescryption[i],dataPhoto[i])
            listFood.add(food)
        }
        return listFood
    }

    private fun showRecyclerList(){
        rvFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list, onClick = {
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra(DetailActivity.FOOD_OBJECT,it)
            startActivity(intent)
        })
        rvFood.adapter = listFoodAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuitm_about->{
                var intent = Intent(this,AboutActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}