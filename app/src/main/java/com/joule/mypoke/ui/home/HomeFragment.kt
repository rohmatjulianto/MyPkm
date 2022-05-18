package com.joule.mypoke.ui.home

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joule.mypoke.R
import com.joule.mypoke.commons.BaseFragment
import com.joule.mypoke.commons.Extensions.showToast
import com.joule.mypoke.databinding.FragmentHomeBinding
import com.joule.mypoke.model.Resource
import com.joule.mypoke.ui.adapter.PokemonAdapter
import com.joule.mypoke.ui.adapter.OnPokemonClickListener
import org.koin.android.ext.android.inject

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home, { FragmentHomeBinding.bind(it) }),
    OnPokemonClickListener {

    var state: Boolean = false
    var pageOffset: Int = 0
    val viewModel: HomeViewModel by inject()

    override fun init() {
        viewModel.getAllPokemon(1)

        val pokeAdapter = PokemonAdapter(this)
        binding.rvPokemon.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokeAdapter
        }

        configureScroll()
        configureSearchView()

        viewModel.pokemon.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    pokeAdapter.submitList(result.data)
                }
                is Resource.Error -> {
                    requireActivity().showToast(result.msg)
                }
            }
            binding.progresBar.visibility = View.GONE
            state = true
        }

    }

    fun configureSearchView() {
        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    viewModel.searchAllPokemon(newText.toString())
                } else {
                    state = false
                    pageOffset = 0
                    viewModel.getAllPokemon(pageOffset)
                }
                return true
            }
        })
    }

    fun configureScroll() {
        binding.rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == 0) {
                    if (state) {
                        state = false
                        binding.progresBar.visibility = View.VISIBLE
                        viewModel.getAllPokemon(pageOffset++)
                    }
                }
            }

        })
    }



    override fun onClick(name: String) {
        val action = HomeFragmentDirections.actionNavigationHomeToDetailActivity(name)
        findNavController().navigate(action)
    }

}