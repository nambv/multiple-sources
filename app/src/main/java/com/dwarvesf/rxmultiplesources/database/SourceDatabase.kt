package com.dwarvesf.rxmultiplesources.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class SourceDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}