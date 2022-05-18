package com.joule.mypoke.domain

import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.model.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class PokeRepositoryImplTest {

    val mockServer = MockWebServer()

    private lateinit var api: PokeApi
    lateinit var repository: PokeRepositoryImpl

    @Mock
    lateinit var pokeDao: PokeDao

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockServer.start(8000)
        api = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)

        repository = PokeRepositoryImpl(pokeDao, api)
    }

    @Test
    fun getAllPokemon() {

    }

    @Test
    fun getPokemon() {
        runTest {
            val inputStrem = javaClass.classLoader!!.getResourceAsStream("pokemon.json")
            mockServer.apply {
                enqueue(
                    MockResponse().setResponseCode(200)
                        .setBody(inputStrem!!.source().buffer().readUtf8())
                )
            }
            val actual = repository.getPokemon("caterpie")
            val expected = Resource.Success(
                Pokemon(
                    10,
                    "caterpie",
                    species = CommonData("name", "url"),
                    sprites = Sprites("", "", "", ""),
                    types = arrayListOf(Type(1, type = CommonData("name", "url"))),
                    29
                )
            )

            assertEquals(expected.data, actual.data)
        }
    }

    @Test
    fun searchAllPokemon() {
    }
}