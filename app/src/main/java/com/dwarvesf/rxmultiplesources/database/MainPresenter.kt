package com.dwarvesf.rxmultiplesources.database

import android.util.Log
import com.dwarvesf.rxmultiplesources.MultiSourceApp
import com.dwarvesf.rxmultiplesources.view.MainView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter {

    lateinit var mNetworkService: NetworkService
    lateinit var mDatabase: SourceDatabase
    lateinit var mView: MainView
    lateinit var mDisposable: CompositeDisposable

    fun attachView(view: MainView) {
        mView = view
        mDisposable = CompositeDisposable()
        mDatabase = MultiSourceApp[view.getContext()].mDatabase as SourceDatabase
        mNetworkService = MultiSourceApp[view.getContext()].mNetworkService as NetworkService
    }

    fun fetchUsers(limit: Int) {

        mView.showRefreshing()
        mDisposable.add(Flowable.merge(
                mDatabase.userDao().getAll()
                        .doOnNext {
                            it.forEach { it.type = "Local" }
                            Log.w("TAG", "size: ${it.size}")
                        }
                        .subscribeOn(Schedulers.io()),
                mNetworkService.getUsers(limit)
                        .doOnNext {
                            Log.w("TAG", "Do on next")
                            it.forEach { it.type = "Network" }
                            mDatabase.userDao().insertAll(it)
                        }
                        .subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.w("TAG", "On next")
                            mView.hideRefreshing()
                            mView.onResultReceived(it)
                        },
                        { throwable ->
                            Log.w("TAG", "On Error: ${throwable.message}")
                            throwable.message?.let {
                                mView.showMessage(it)
                                mView.hideRefreshing()
                            }
                        }))

    }

    fun detachView() {
        mDisposable.clear()
        mDisposable.dispose()
    }
}