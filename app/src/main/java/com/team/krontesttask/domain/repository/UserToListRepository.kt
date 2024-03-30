package com.team.krontesttask.domain.repository

import com.team.krontesttask.domain.models.UserToList
import io.reactivex.rxjava3.core.Single

interface UserToListRepository {
    fun getUserList(count: Int, since: Int, token: String): Single<List<UserToList>>
}