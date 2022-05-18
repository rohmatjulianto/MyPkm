package com.joule.mypoke.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [PokeEntity::class], version = 6, exportSchema = false)
abstract class PokeDatabase : RoomDatabase() {
    abstract fun pokeDao(): PokeDao
}