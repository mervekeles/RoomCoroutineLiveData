package com.example.roomcoroutinelivedata

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.roomcoroutinelivedata.database.User
import com.example.roomcoroutinelivedata.database.UserDao
import com.example.roomcoroutinelivedata.database.UserDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class UserDBViewModel(application: Application) : AndroidViewModel(application) {

    val app = application
    private var userDao: UserDao


    init{
        userDao = UserDatabase.getInstance(application).userDao()
    }

    fun fetchUsersFromDB(): LiveData<List<User>> {
        return userDao.getAll()
    }

    fun populateData(){
        viewModelScope.launch{
            userDao.deleteAll()
            prePopulateData()
        }
    }

        private suspend fun prePopulateData() {

            val jsonString = app.resources.openRawResource(R.raw.users).bufferedReader().use {
                it.readText()
            }
            val typeToken = object : TypeToken<List<User>>() {}.type
            val users = Gson().fromJson<List<User>>(jsonString, typeToken)
            Log.v("VM", "${users[0]}")
            userDao.insertAll(users)

        }

    fun onUserClicked(id: Long) {

    }
}

class UserDBViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDBViewModel::class.java)) {
            return UserDBViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}