package com.joule.mypoke.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeEntity
import com.joule.mypoke.model.Resource
import com.joule.mypoke.ui.favorite.FavoriteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
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
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: PokeRepository

    @Mock
    private lateinit var observerEntity: Observer<Resource<List<PokeEntity>>>

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var homeViewModel: HomeViewModel

    val dummyPoke = Resource.Success(listOf(PokeEntity(1, "balbasaur", 0)))


    @Before
    fun setUp() {
        observerEntity = mock()
        homeViewModel = HomeViewModel(repository, dispatcher)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `given homeviewmodel getallPokemon from pokeDao`() {
        runTest {
            val pageOffset = 1
            Mockito.`when`(repository.getAllPokemon(pageOffset)).thenReturn(dummyPoke)
            homeViewModel.pokemon.observeForever(observerEntity)
            homeViewModel.getAllPokemon(pageOffset)
            verify(observerEntity).onChanged(dummyPoke)
        }
    }

    @Test
    fun searchAllPokemon() {
        runTest {
            val value = "balbasaur"
            Mockito.`when`(repository.searchAllPokemon(value)).thenReturn(dummyPoke)
            homeViewModel.pokemon.observeForever(observerEntity)
            homeViewModel.searchAllPokemon(value)
            verify(observerEntity).onChanged(dummyPoke)
        }
    }
}