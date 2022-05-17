package com.joule.mypoke

import android.app.Application
import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.domain.PokeRepositoryImpl
import com.joule.mypoke.modul.databaseModule
import com.joule.mypoke.modul.networkModule
import com.joule.mypoke.modul.repositoryModule
import com.joule.mypoke.modul.viewModelModule
import com.joule.mypoke.ui.detail.DetailViewModel
import com.joule.mypoke.ui.home.HomeViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(networkModule)
            modules(repositoryModule)
            modules(databaseModule)
            modules(viewModelModule)
        }

    }
}