package com.joule.mypoke.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joule.mypoke.commons.Extensions.loadWithId
import com.joule.mypoke.databinding.ItemPokemonBinding
import com.joule.mypoke.local.PokeEntity

class FavoriteAdapter(val listener: OnPokemonClickListener, val items: List<PokeEntity>) :
    RecyclerView.Adapter<FavoriteAdapter.viewHolder>() {
    class viewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(entity: PokeEntity) {
            binding.namePoke.text = entity.name
            binding.imgPokemon.loadWithId(entity.id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.viewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.viewHolder, position: Int) {
        holder.onBind(items.get(position))
        holder.binding.root.setOnClickListener{
            listener.onClick(items.get(position).name)
        }
    }

    override fun getItemCount(): Int = items.size
}