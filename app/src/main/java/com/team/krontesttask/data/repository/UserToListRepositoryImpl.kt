package com.team.krontesttask.data.repository

import com.team.krontesttask.data.network.UserApiService
import com.team.krontesttask.domain.models.UserToList
import com.team.krontesttask.domain.repository.UserToListRepository
import io.reactivex.rxjava3.core.Single

class UserToListRepositoryImpl(
    val userApiService: UserApiService
) : UserToListRepository {

    override fun getUserList(count: Int, since: Int, token: String): Single<List<UserToList>> {
        return userApiService.getListUser(count, since, token)
    }
}