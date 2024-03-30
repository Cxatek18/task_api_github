package com.team.krontesttask.domain.repository

import com.team.krontesttask.domain.models.UserDetail
import io.reactivex.rxjava3.core.Single

interface UserDetailRepository {

    fun getUser(login: String, token: String): Single<UserDetail>
}