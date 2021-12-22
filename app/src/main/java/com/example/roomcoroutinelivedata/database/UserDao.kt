package com.example.roomcoroutinelivedata.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM user")
    fun getAll():LiveData<List<User>>

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * from user WHERE id = :key")
    fun getUserWithId(key: Int): LiveData<User>
}