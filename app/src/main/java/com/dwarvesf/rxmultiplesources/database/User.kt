package com.dwarvesf.rxmultiplesources.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Entity
@Generated("com.robohorse.robopojogenerator")
data class User(

        @PrimaryKey(autoGenerate = true)
        var id: Long,

        @ColumnInfo(name = "name")
        @field:SerializedName("name")
        val name: String,

        @ColumnInfo(name = "email")
        @field:SerializedName("email")
        val email: String,

        @ColumnInfo(name = "picture")
        @field:SerializedName("picture")
        val picture: String,

        @ColumnInfo(name = "type")
        var type: String
)