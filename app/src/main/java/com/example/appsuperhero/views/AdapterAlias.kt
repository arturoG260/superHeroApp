package com.example.appsuperhero.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperhero.databinding.ItemAliasBinding

class AdapterAlias(private val aliases: ArrayList<String>) :
    RecyclerView.Adapter<AdapterAlias.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemAliasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvHeroAlias.text = aliases[position]
    }

    override fun getItemCount(): Int {
        return aliases.size
    }

    class ViewHolder(val binding: ItemAliasBinding) : RecyclerView.ViewHolder(binding.root)

}