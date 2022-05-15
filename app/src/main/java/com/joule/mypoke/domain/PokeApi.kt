package com.joule.mypoke.domain

import com.joule.mypoke.model.CommonData
import com.joule.mypoke.model.Pokemon
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<ArrayList<CommonData>>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String) : Response<Pokemon>
}