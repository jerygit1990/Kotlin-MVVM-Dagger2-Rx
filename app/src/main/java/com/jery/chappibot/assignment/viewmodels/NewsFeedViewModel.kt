package com.jery.chappibot.assignment.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jery.chappibot.assignment.BaseApp
import com.jery.chappibot.assignment.models.res.FeedInfoRes
import com.jery.chappibot.assignment.models.res.FeedRes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    var newsfeed: MutableLiveData<List<FeedInfoRes>> = MutableLiveData()
    var errorMsg: MutableLiveData<String> = MutableLiveData()
    private var api = BaseApp.instance.component.getApi()

    private lateinit var disposible: Disposable

    init {
        loadFeeds()
    }

    fun loadFeeds() {
        disposible = api.getNewsFeed()
            .flatMap {
                Observable.fromIterable(it.feeds)
            }
            .filter {
                it.isAllowedType()
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Log.d("feed", ": item size " + it.size)
                    newsfeed.value = it
                },
                onError = {
                    Log.d("feed", ": itemsize: " + it.cause.toString())
                    errorMsg.value = "Có lỗi xảy ra vui lòng thử lại"
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposible.dispose()
    }
}