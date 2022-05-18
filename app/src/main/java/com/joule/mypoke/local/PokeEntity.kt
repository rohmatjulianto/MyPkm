package com.joule.mypoke.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "pokemon_name") val name: String,
    @ColumnInfo(name = "is_favorite") val isFav: Int = 0 // favorite true = 1

)
