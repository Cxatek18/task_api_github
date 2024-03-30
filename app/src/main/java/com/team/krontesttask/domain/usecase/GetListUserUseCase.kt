package com.team.krontesttask.domain.usecase

import com.team.krontesttask.domain.models.UserToList
import com.team.krontesttask.domain.repository.UserToListRepository
import io.reactivex.rxjava3.core.Single

class GetListUserUseCase(private val repository: UserToListRepository) {

    fun getListUser(count: Int, since: Int, token: String): Single<List<UserToList>> {
        return repository.getUserList(count, since, token)
    }
}