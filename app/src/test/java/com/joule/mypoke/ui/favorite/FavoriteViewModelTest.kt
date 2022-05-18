package com.joule.mypoke.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

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
class FavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokeDao: PokeDao

    @Mock
    private lateinit var observerEntity: Observer<List<PokeEntity>>

    @ExperimentalCoroutinesApi
    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var favoriteViewModel: FavoriteViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        observerEntity = mock()
        favoriteViewModel = FavoriteViewModel(pokeDao, dispatcher)
    }

    @After
    fun tearDown() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given favoriteViewModel when call getAllPokemon from pokeDao()`() {
        runTest {
            val dummyPoke = listOf(PokeEntity(1, "balboas", 0))
            Mockito.`when`(pokeDao.getAllPokemonFavorite()).thenReturn(dummyPoke)

            favoriteViewModel.pokemon.observeForever(observerEntity)
            favoriteViewModel.getAllPokemon()
            verify(observerEntity).onChanged(dummyPoke)
        }

    }
}