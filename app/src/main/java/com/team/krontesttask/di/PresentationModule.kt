package com.team.krontesttask.di

import com.team.krontesttask.domain.usecase.GetListUserUseCase
import com.team.krontesttask.domain.usecase.GetUserUseCase
import com.team.krontesttask.presentation.MainViewModelFactory
import com.team.krontesttask.presentation.UserDetailViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable

@Module
class PresentationModule {

    @Provides
    fun provideUserDetailCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideUserDetailViewModelFactory(
        getUserUseCase: GetUserUseCase,
        compositeDisposable: CompositeDisposable
    ): UserDetailViewModelFactory {
        return UserDetailViewModelFactory(
            getUserUseCase = getUserUseCase,
            compositeDisposable = compositeDisposable
        )
    }

    @Provides
    fun provideMainViewModelFactory(
        getListUserUseCase: GetListUserUseCase,
        compositeDisposable: CompositeDisposable
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getListUserUseCase = getListUserUseCase,
            compositeDisposable = compositeDisposable,
        )
    }
}