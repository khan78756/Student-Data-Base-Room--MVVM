package com.example.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabase.model.User


//CONTAINING THE METHODS USED FOR ACCESSING DATABASE
@Dao
interface UserDao{


    //FOR ADDING DATA INTO ROOM DATABASE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user_database")
    suspend fun deleteAllUser()

    //FOR READING ALL DATA FROM ROOM DATABASE
    @Query("SELECT * FROM user_database ORDER BY id ASC")
     fun readAllData(): LiveData<List<User>>

}