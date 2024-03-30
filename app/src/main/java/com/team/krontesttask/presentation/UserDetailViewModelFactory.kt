package com.team.krontesttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.team.krontesttask.domain.usecase.GetUserUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UserDetailViewModelFactory(
    val getUserUseCase: GetUserUseCase,
    val compositeDisposable: CompositeDisposable
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailViewModel(
            getUserUseCase = getUserUseCase,
            compositeDisposable = compositeDisposable
        ) as T
    }
}