package com.joule.mypoke.ui.favorite

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joule.mypoke.R
import com.joule.mypoke.commons.BaseFragment
import com.joule.mypoke.databinding.FavoriteFragmentBinding
import com.joule.mypoke.ui.adapter.OnPokemonClickListener
import com.joule.mypoke.ui.adapter.PokemonAdapter
import org.koin.android.ext.android.inject

class FavoriteFragment : BaseFragment<FavoriteFragmentBinding>(R.layout.favorite_fragment, { FavoriteFragmentBinding.bind(it)}),
    OnPokemonClickListener {

    val viewModel: FavoriteViewModel by inject()

    override fun init() {
        observePokemon()
    }

    override fun onClick(name: String) {
        val action = FavoriteFragmentDirections.actionNavigationFavoriteToDetailActivity(name)
        findNavController().navigate(action)
    }


    fun observePokemon(){
        val pokeAdapter = PokemonAdapter(this)
        binding.rvPokemon.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokeAdapter
        }
        viewModel.pokemon.observe(viewLifecycleOwner){
            pokeAdapter.submitList(it)
        }
    }

}