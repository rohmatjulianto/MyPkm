package com.joule.mypoke.modul

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.domain.PokeRepositoryImpl
import com.joule.mypoke.local.PokeDao
import com.joule.mypoke.local.PokeDatabase
import com.joule.mypoke.ui.detail.DetailViewModel
import com.joule.mypoke.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.koinApplication
import org.koin.dsl.module

val repositoryModule = module {
    single { PokeRepositoryImpl(get()) as PokeRepository }
}