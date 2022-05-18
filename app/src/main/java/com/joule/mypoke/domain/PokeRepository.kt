package com.joule.mypoke.domain


import android.util.Log
import com.joule.mypoke.commons.Extensions.getIdFromUrl
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeEntity
import com.joule.mypoke.model.CommonData
import com.joule.mypoke.model.Pokemon
import com.joule.mypoke.model.Resource


interface PokeRepository {
    suspend fun getAllPokemon(offset: Int): Resource<List<PokeEntity>>
    suspend fun getPokemon(name: String): Resource<Pokemon>
    suspend fun searchAllPokemon(value: String): Resource<List<PokeEntity>>
}

class PokeRepositoryImpl(val pokeDao: PokeDao, val service: PokeApi) : PokeRepository {

    override suspend fun getAllPokemon(offset: Int): Resource<List<PokeEntity>> {
        return try {
            val result = service.getAllPokemon(offset*10)

//            inserting datas to db
            result.body()?.data?.let { data ->
                for (i in 0 until data.size){
                    pokeDao.insert(PokeEntity(name = data.get(i).name, id = data.get(i).url.getIdFromUrl(), isFav = 0))
                }
            }

//          get from room db with pagination using limit
            if (offset <= 0){
                Resource.Success(pokeDao.getAllPokemon(10))
            }else{
                Resource.Success(pokeDao.getAllPokemon(offset*10))
            }

        } catch (e: Exception) {
            Resource.Error(e.message.toString())
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

    override suspend fun searchAllPokemon(value: String): Resource<List<PokeEntity>> {
//        search data only from table pokemon
        val result = pokeDao.searchPokemon(value)
        return if (result.isNotEmpty()){
            Resource.Success(result)
        }else{
            Resource.Error("$value Not Found")
        }
    }

}