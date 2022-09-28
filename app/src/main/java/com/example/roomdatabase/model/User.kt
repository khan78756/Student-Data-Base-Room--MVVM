package com.example.roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


//REPRESENT A TABLE WITHIN A DATABASE
@Parcelize
@Entity(tableName="User_Database")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstName:String,
    val secondName:String,
    val age: String,
): Parcelable