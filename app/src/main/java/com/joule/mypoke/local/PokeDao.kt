package com.joule.mypoke.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokeDao {

    @Query("select * from pokemon")
    fun getAllPokemon(): List<PokeEntity>

    @Query("SELECT * FROM pokemon WHERE id LIKE :id LIMIT 1")
    fun getById(id: Int): PokeEntity?

    @Insert
    fun insert(vararg pokemon: PokeEntity)

    @Delete
    fun delete(pokemon: PokeEntity)
}