package com.team.krontesttask.data.network

import com.team.krontesttask.domain.models.UserDetail
import com.team.krontesttask.domain.models.UserToList
import io.github.cdimascio.dotenv.dotenv
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @Headers(
        "Accept: application/vnd.github+json",
        "X-GitHub-Api-Version: 2022-11-28",
    )
    @GET("users")
    fun getListUser(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int,
        @Header("Authorization") token: String
    ): Single<List<UserToList>>

    @Headers(
        "Accept: application/vnd.github+json",
        "X-GitHub-Api-Version: 2022-11-28",
    )
    @GET("users/{login}")
    fun getUser(
        @Path("login") login: String,
        @Header("Authorization") token: String
    ): Single<UserDetail>
}