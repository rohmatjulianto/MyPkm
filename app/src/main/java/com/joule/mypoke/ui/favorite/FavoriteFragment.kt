package com.joule.mypoke.ui.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joule.mypoke.R
import com.joule.mypoke.commons.BaseFragment
import com.joule.mypoke.databinding.FavoriteFragmentBinding
import org.koin.android.ext.android.inject

class FavoriteFragment : BaseFragment<FavoriteFragmentBinding>(R.layout.favorite_fragment, { FavoriteFragmentBinding.bind(it)}), OnPokemonClickListener {

    val viewModel: FavoriteViewModel by inject()
    override fun init() {
        binding.rvPokemon.layoutManager = LinearLayoutManager(context)
        viewModel.pokemon.observe(viewLifecycleOwner){
            binding.rvPokemon.adapter = FavoriteAdapter(this, it)
        }
    }

    override fun onClick(name: String) {
        val action = FavoriteFragmentDirections.actionNavigationFavoriteToDetailActivity(name)
        findNavController().navigate(action)
    }
}