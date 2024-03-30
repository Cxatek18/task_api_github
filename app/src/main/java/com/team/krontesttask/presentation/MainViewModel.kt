package com.team.krontesttask.presentation

import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.krontesttask.domain.models.UserToList
import com.team.krontesttask.domain.usecase.GetListUserUseCase
import io.github.cdimascio.dotenv.dotenv
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.github.cdimascio.dotenv.dotenv
import java.nio.file.Paths

class MainViewModel(
    val compositeDisposable: CompositeDisposable,
    val getListUserUseCase: GetListUserUseCase
) : ViewModel() {

    private val _listUser = MutableLiveData<List<UserToList>>()
    val listUser: LiveData<List<UserToList>>
        get() = _listUser

    private val dotenv = dotenv {
        directory = "/assets"
        ignoreIfMalformed = true
        ignoreIfMissing = true
        filename = "env"
    }

    fun getListUser(count: Int, since: Int) {
        val disposable = getListUserUseCase.getListUser(
            count = count, since = since, token = String.format("" +
                    "Bearer: %s", dotenv["GITHUB_TOKEN"]
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                if (_listUser.value?.isEmpty() == true) {
                    _listUser.value = it
                } else {
                    val listItems = ArrayList<UserToList>()
                    _listUser.value?.let { it1 -> listItems.addAll(it1) }
                    listItems.addAll(it)
                    _listUser.value = listItems
                }

            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}