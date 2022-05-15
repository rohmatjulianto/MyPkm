package com.joule.mypoke.model

import com.google.gson.annotations.SerializedName

data class Pokemon(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: CommonData,

    @SerializedName("egg_groups")
    val eggGroups: ArrayList<CommonData>,

    @SerializedName("habitat")
    val habitat: CommonData

)

data class CommonData(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
