package com.example.roomcoroutinelivedata.userdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomcoroutinelivedata.UserDBViewModel
import com.example.roomcoroutinelivedata.database.User
import com.example.roomcoroutinelivedata.database.UserDao
import com.example.roomcoroutinelivedata.database.UserDatabase

class UserDetailViewModel (userKey: Int, application: Application) : AndroidViewModel(application) {//(private val userKey: Int = 0,
    //we extend from AndroidViewModel instead of ViewModel because it's the version that can reference the application context to instantiate the database in a lifecycle-aware way.


    val app = application
    private var userDao: UserDao

    private val user: LiveData<User>

    fun getUser() = user

    init {
        userDao = UserDatabase.getInstance(application).userDao()
        user=userDao.getUserWithId(userKey)
    }
}

class UserDetailViewModelFactory(private val userKey: Int, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(userKey, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}