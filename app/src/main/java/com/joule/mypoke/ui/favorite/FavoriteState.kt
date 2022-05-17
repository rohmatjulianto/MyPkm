package com.joule.mypoke.ui.favorite

sealed class FavoriteState {
    object OnNull : FavoriteState()
    object OnSaved : FavoriteState()
    object OnDeleted : FavoriteState()
}