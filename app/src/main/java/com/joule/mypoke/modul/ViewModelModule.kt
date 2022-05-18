package com.joule.mypoke.modul

import com.joule.mypoke.ui.detail.DetailViewModel
import com.joule.mypoke.ui.favorite.FavoriteViewModel
import com.joule.mypoke.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { param -> DetailViewModel(param.get(), get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}