package com.joule.mypoke.modul

import com.joule.mypoke.domain.PokeRepository
import com.joule.mypoke.domain.PokeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PokeRepository>{ PokeRepositoryImpl( get(),get())}
}