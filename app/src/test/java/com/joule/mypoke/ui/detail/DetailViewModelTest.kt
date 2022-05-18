package com.joule.mypoke.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeEntity
import com.joule.mypoke.model.Pokemon
import com.joule.mypoke.model.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokeDao: PokeDao

    @Mock
    lateinit var repository: PokeRepository

    @Mock
    private lateinit var observerPokemon: Observer<Resource<Pokemon>>

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var detailViewModel: DetailViewModel
    @Before
    fun setUp() {
        observerPokemon = mock()
        detailViewModel = DetailViewModel("", pokeDao, repository, dispatcher)
    }

    @Test
    fun getDetailPokemon() {
        runTest {
            val name = "balbasaur"
            val dummyPoke = Resource.Success(Pokemon(id = 1, name = name))
            Mockito.`when`(repository.getPokemon(name)).thenReturn(dummyPoke)

            detailViewModel.pokemon.observeForever(observerPokemon)
            detailViewModel.getDetailPokemon(name)
            verify(observerPokemon).onChanged(dummyPoke)
        }
    }
}