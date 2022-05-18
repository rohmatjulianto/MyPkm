package com.joule.mypoke.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joule.mypoke.commons.Extensions.loadWithId
import com.joule.mypoke.databinding.ItemPokemonBinding
import com.joule.mypoke.local.PokeEntity

class PokemonAdapter(val listener: OnPokemonClickListener) : ListAdapter<PokeEntity, PokemonAdapter.PokeViewHolder>(
    POKE_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val pokeItem = getItem(position)
        if (pokeItem != null){
            holder.onBind(getItem(position))
        }
        holder.binding.root.setOnClickListener{
            listener.onClick(pokeItem.name)
        }
    }


    class PokeViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(entity: PokeEntity?) {
            binding.apply {
                namePoke.text = entity?.name
                entity?.id?.let {
                    imgPokemon.loadWithId(it)
                }
            }
        }

    }

    companion object {
        private val POKE_COMPARATOR = object : DiffUtil.ItemCallback<PokeEntity>() {
            override fun areItemsTheSame(oldItem: PokeEntity, newItem: PokeEntity): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PokeEntity, newItem: PokeEntity): Boolean =
                oldItem == newItem
        }
    }
}