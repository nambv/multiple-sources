package com.dwarvesf.rxmultiplesources.view

import android.content.Context
import com.dwarvesf.rxmultiplesources.database.User

interface MainView {
    fun showRefreshing()
    fun hideRefreshing()
    fun onResultReceived(it: List<User>)
    fun showMessage(message: String)
    fun getContext(): Context
}