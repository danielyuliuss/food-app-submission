package com.danielys.a08_finalsubmission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListFoodAdapter(private val listFood: ArrayList<Food>, private val onClick: (Food) -> Unit) : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(food: Food){
            itemView.setOnClickListener {
                onClick(food)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_food,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listFood[position]

        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.bind(listFood[position])
        holder.tvName.text = name
        holder.tvDescription.text = description
    }
}