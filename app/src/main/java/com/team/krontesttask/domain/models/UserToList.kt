package com.team.krontesttask.domain.models

import com.google.gson.annotations.SerializedName

public data class UserToList(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatar: String,
)
