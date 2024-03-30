package com.team.krontesttask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.krontesttask.domain.models.UserDetail
import com.team.krontesttask.domain.usecase.GetUserUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.github.cdimascio.dotenv.dotenv

class UserDetailViewModel(
    val compositeDisposable: CompositeDisposable,
    val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var _user = MutableLiveData<UserDetail>()

    private val dotenv = dotenv {
        directory = "/assets"
        ignoreIfMalformed = true
        ignoreIfMissing = true
        filename = "env"
    }

    val user: LiveData<UserDetail>
        get() = _user

    fun getUserDetail(login: String) {
        val disposable = getUserUseCase.getUser(
            login = login,
            token = String.format("Bearer %s", dotenv["GITHUB_TOKEN"])
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                _user.value = it
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}