package com.team.krontesttask.data.repository

import com.team.krontesttask.data.network.UserApiService
import com.team.krontesttask.domain.models.UserDetail
import com.team.krontesttask.domain.repository.UserDetailRepository
import io.reactivex.rxjava3.core.Single

class UserDetailRepositoryImpl(
    val userApiService: UserApiService
) : UserDetailRepository {

    override fun getUser(login: String, token: String): Single<UserDetail> {
        return userApiService.getUser(login, token)
    }
}