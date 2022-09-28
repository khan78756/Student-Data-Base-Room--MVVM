package com.example.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase.data.UserDao
import com.example.roomdatabase.model.User


//A REPOSITORY ABSTRACT ACCESS TO MULTIPLE DATA SOURCES
class UserRepository(private val userDao: UserDao){

    val readAllData: LiveData<List<User>> =userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}