package io.orly.loginapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.orly.loginapp.data.local.room.dao.UserDao
import io.orly.loginapp.data.local.room.entity.UserEntity
import io.orly.loginapp.util.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val userDao: UserDao
) {

    fun insertUser(user: UserEntity) = appExecutors.diskIO().execute {
        userDao.insert(user)
    }

    fun deleteUser(user: UserEntity) = userDao.deleteUser(user)

    fun login(email: String, password: String) = userDao.getUser(email, password)

    fun getUser(id: Int): LiveData<UserEntity?> = userDao.getUserById(id)

    fun getUserByEmail(email: String): LiveData<UserEntity?> = userDao.getUserByEmail(email)

    fun getAllUser(): LiveData<List<UserEntity>> = userDao.getAllUser()

}