package com.team.krontesttask.di

import com.team.krontesttask.domain.repository.UserDetailRepository
import com.team.krontesttask.domain.repository.UserToListRepository
import com.team.krontesttask.domain.usecase.GetListUserUseCase
import com.team.krontesttask.domain.usecase.GetUserUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetUserUseCase(repository: UserDetailRepository): GetUserUseCase {
        return GetUserUseCase(repository)
    }

    @Provides
    fun provideGetListUserUseCase(repository: UserToListRepository): GetListUserUseCase {
        return GetListUserUseCase(repository)
    }
}