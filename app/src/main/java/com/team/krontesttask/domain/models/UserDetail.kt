package com.team.krontesttask.domain.models

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("following")
    val following: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("created_at")
    val createdAt: String
)
