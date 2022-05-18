package com.joule.mypoke.domain

import com.joule.mypoke.model.CommonData
import com.joule.mypoke.model.Pokemon
import com.joule.mypoke.model.Result
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon?limit=10")
    suspend fun getAllPokemon(@Query("offset") offset: Int): Response<Result<ArrayList<CommonData>>>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String) : Response<Pokemon>
}