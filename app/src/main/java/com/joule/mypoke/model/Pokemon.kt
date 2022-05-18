package com.joule.mypoke.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("species")
    val species: CommonData? = null,

    @SerializedName("sprites")
    val sprites: Sprites? = null,

    @SerializedName("types")
    val types: ArrayList<Type>? = null,

    @SerializedName("weight")
    val weight: Int? = null

)

data class Result<T>(
    @SerializedName("results")
    val data: T?
)
data class CommonData(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class Type(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: CommonData
)

data class Sprites(
    @SerializedName("back_default")
    val back: String,

    @SerializedName("back_shiny")
    val backShiny: String,

    @SerializedName("front_default")
    val front: String,

    @SerializedName("front_shiny")
    val frontShiny: String,

)
