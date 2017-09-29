package com.dwarvesf.rxmultiplesources

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.dwarvesf.rxmultiplesources.database.NetworkService
import com.dwarvesf.rxmultiplesources.database.SourceDatabase

class MultiSourceApp : Application() {

    private val DATABASE_NAME = "source_db"

    var mDatabase: SourceDatabase? = null
    get() {
        if (field == null)
            mDatabase = Room.databaseBuilder(applicationContext, SourceDatabase::class.java, DATABASE_NAME).build()
        return field
    }

    var mNetworkService: NetworkService? = null
    get() {
        if (field == null)
            mNetworkService = NetworkService.Factory.get()
        return field
    }

    var mInstance: MultiSourceApp? = null

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        operator fun get(context: Context) = (context.applicationContext as MultiSourceApp)
    }
}