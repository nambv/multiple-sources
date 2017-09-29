package com.dwarvesf.rxmultiplesources.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.dwarvesf.rxmultiplesources.R
import com.dwarvesf.rxmultiplesources.database.MainPresenter
import com.dwarvesf.rxmultiplesources.database.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mUsers: MutableList<User>
    lateinit var mAdapter: UserAdapter
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter()
        mPresenter.attachView(this)
        setupActivity()
    }

    private fun setupActivity() {

        mUsers = mutableListOf()
        mAdapter = UserAdapter(mUsers)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter

        mPresenter.fetchUsers(10)
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    override fun getContext(): Context = this

    override fun showRefreshing() {
        swipeRefreshingLayout.isRefreshing = true
    }

    override fun hideRefreshing() {
        swipeRefreshingLayout.isRefreshing = false
    }

    override fun onResultReceived(it: List<User>) {

        Log.w("onResultReceived", "size: ${it.size}")

        mUsers.clear()
        mUsers.addAll(it)
        mAdapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}