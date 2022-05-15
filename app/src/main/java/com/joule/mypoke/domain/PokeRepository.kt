package com.joule.mypoke.domain

import android.content.res.Resources
import android.util.Log
import com.joule.mypoke.model.CommonData
import com.joule.mypoke.model.Pokemon
import com.joule.mypoke.model.Resource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

interface PokeRepository {
    suspend fun getAllPokemon(): Resource<ArrayList<CommonData>>
    suspend fun getPokemon(name: String): Resource<Pokemon>
}

class PokeRepositoryImpl(val service: PokeApi) : PokeRepository {

    override suspend fun getAllPokemon(): Resource<ArrayList<CommonData>> {
        return try {
            val result = service.getAllPokemon(10, 10)
            Resource.Success(result.body())
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

    override suspend fun getPokemon(name: String): Resource<Pokemon> {
        return try {
            val result = service.getPokemonByName(name)
            Resource.Success(result.body())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

}