package io.orly.loginapp.ui.register

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.orly.loginapp.data.local.preference.AppSharedPreference
import io.orly.loginapp.data.local.room.entity.UserEntity
import io.orly.loginapp.data.repository.UserRepository
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val preference: AppSharedPreference
) : ViewModel() {
    val user = MutableLiveData<UserEntity>(UserEntity())

    fun saveUser() = userRepository.insertUser(
        user.value!!
    )

    fun saveId(id: Int) = preference.saveId(id)

    fun saveEmail(email: String) = preference.saveEmail(email)
}