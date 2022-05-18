package com.joule.mypoke.commons

import android.content.Context
import android.media.Image
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.joule.mypoke.R

object Extensions {
    fun ImageView.loadWithId(id: Int) {
        Glide.with(this.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png")
            .into(this)
    }

    fun ImageView.loadWithUrl(url: String) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }

    fun Menu.setState(state: Boolean) {
        if (state) {
            getItem(0)?.setIcon(R.drawable.ic_favorite_24)
        } else {
            getItem(0).setIcon(R.drawable.ic_favorite_border_24)
        }
    }

    fun String.getIdFromUrl(): Int {
        val id = this.removePrefix("https://pokeapi.co/api/v2/pokemon/")
        return id.replace("/", "").toInt()
    }

    fun Context.showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }


}