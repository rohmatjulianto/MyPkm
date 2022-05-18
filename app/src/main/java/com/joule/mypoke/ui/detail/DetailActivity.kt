package com.joule.mypoke.ui.detail


import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.joule.mypoke.R
import com.joule.mypoke.commons.BaseActivity
import com.joule.mypoke.commons.Extensions.loadWithUrl
import com.joule.mypoke.commons.Extensions.setState
import com.joule.mypoke.commons.Extensions.showToast
import com.joule.mypoke.databinding.ActivityDetailBinding
import com.joule.mypoke.databinding.LayoutImageBinding
import com.joule.mypoke.model.Pokemon
import com.joule.mypoke.model.Resource
import com.joule.mypoke.ui.favorite.FavoriteState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : BaseActivity<ActivityDetailBinding>({ ActivityDetailBinding.inflate(it) }) {

    private val viewModel: DetailViewModel by viewModel { parametersOf(args.name) }
    private val args: DetailActivityArgs by navArgs()

    var id: Int? = 0
    var name: String? = null
    private var menu: Menu? = null

    override fun init() {
        title = "Detail of ${args.name}"
        setupToolbar()
        observeDetailPokemon()
        observeStateFavorite()
    }

    private fun observeDetailPokemon() {
        viewModel.pokemon.observe(this) { result ->
            when (result) {
                is Resource.Success -> {
                    bindPokemon(result.data!!)
                    viewModel.validateFromDb(result.data.id)
                }
                is Resource.Error -> {
                    showToast(result.msg)
                }
            }

        }

    }

    private fun bindPokemon(pokemon: Pokemon){
        id = pokemon.id
        name = pokemon.name
        with(binding) {
            pokemon.sprites?.let { sprites ->
                bindImage(imgBackDefault, "Back Default", sprites.back)
                bindImage(imgFrontDefault, "Front Default", sprites.front)
                bindImage(imgBackShiny, "Back Shiny", sprites.backShiny)
                bindImage(imgFrontShiny, "Front Shiny", sprites.frontShiny)
            }

            tvId.text = pokemon.id.toString()
            tvSpecies.text = pokemon.species?.name

            val types = pokemon.types?.map { it.type.name }
            tvTypes.text = types.toString()
        }
    }

    private fun bindImage(binding: LayoutImageBinding, title: String, url: String) {
        with(binding) {
            tvName.text = title
            imgPokemon.loadWithUrl(url)
        }
    }

    private fun observeStateFavorite() {
        lifecycleScope.launchWhenCreated {
            viewModel.favoriteState.collectLatest {
                when (it) {
                    FavoriteState.OnSaved -> {
                        menu?.setState(true)
                    }
                    FavoriteState.OnDeleted -> {
                        menu?.setState(false)
                    }
                    else -> {}
                }

            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.action_favorite -> {
                id.let {
                    viewModel.saveOrDeleteFromFavorite(it!!, name!!)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}