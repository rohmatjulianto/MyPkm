package com.joule.mypoke.modul

import android.app.Application
import androidx.room.Room
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { providePokeDao(get()) }
}

fun provideDatabase(application: Application): PokeDatabase {
    return Room.databaseBuilder(
        application, PokeDatabase::class.java, "poke_database"
    ).fallbackToDestructiveMigration()
        .build()
}

fun providePokeDao(db: PokeDatabase): PokeDao = db.pokeDao()