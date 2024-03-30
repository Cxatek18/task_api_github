package com.team.krontesttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.team.krontesttask.domain.usecase.GetListUserUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModelFactory(
    val getListUserUseCase: GetListUserUseCase,
    val compositeDisposable: CompositeDisposable
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            compositeDisposable = compositeDisposable,
            getListUserUseCase = getListUserUseCase
        ) as T
    }
}