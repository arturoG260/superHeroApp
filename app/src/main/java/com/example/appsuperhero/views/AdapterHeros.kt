package com.example.appsuperhero.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appsuperhero.databinding.ItemHeroBinding
import com.example.appsuperhero.models.HeroModel

class AdapterHeros(arrayList: ArrayList<HeroModel>, val callback: (HeroModel) -> Unit) : RecyclerView.Adapter<AdapterHeros.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    var heroes: List<HeroModel> = arrayList
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        heroes[position].let { heroe ->
            with(holder.binding){
                tvHero.text = heroe.name
                Glide.with(holder.itemView.context)
                    .load(heroe.image.url)
                    .into(ivHero)
                lyHero.setOnClickListener {
                    callback(heroes[position])
                }
            }
        }

    }

    override fun getItemCount(): Int = heroes.size

    class ViewHolder(val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root)
}