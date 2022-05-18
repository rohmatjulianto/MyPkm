package com.joule.mypoke.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokeDao {

    @Query("select * from pokemon LIMIT :limit")
    fun getAllPokemon(limit : Int): List<PokeEntity>

    @Query("select * from pokemon WHERE is_favorite LIKE 1")
    fun getAllPokemonFavorite(): List<PokeEntity>

    @Query("select * from pokemon WHERE pokemon_name LIKE :value || '%'")
    fun searchPokemon(value: String): List<PokeEntity>

    @Query("SELECT * FROM pokemon WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): PokeEntity?

    @Update
    fun updateFav(vararg obj: PokeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg pokemon: PokeEntity)

    @Delete
    fun delete(pokemon: PokeEntity)
}