package com.dwarvesf.rxmultiplesources.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * from user")
    fun getAll(): Flowable<List<User>>

    @Insert
    fun insertAll(users: List<User>)

    @Update
    fun updateUser(user: User)
}