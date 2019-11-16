package io.orly.loginapp.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.orly.loginapp.data.local.room.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): LiveData<UserEntity?>

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): LiveData<UserEntity?>

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): LiveData<UserEntity?>

    @Delete
    fun deleteUser(user: UserEntity)
}