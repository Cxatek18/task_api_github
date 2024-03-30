package com.team.krontesttask.domain.usecase

import com.team.krontesttask.domain.models.UserDetail
import com.team.krontesttask.domain.repository.UserDetailRepository
import io.reactivex.rxjava3.core.Single

class GetUserUseCase(private val repository: UserDetailRepository) {

    fun getUser(login: String, token: String): Single<UserDetail> {
        return repository.getUser(login, token)
    }
}