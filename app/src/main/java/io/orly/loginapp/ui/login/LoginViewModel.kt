package io.orly.loginapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.orly.loginapp.data.local.preference.AppSharedPreference
import io.orly.loginapp.data.local.room.entity.UserEntity
import io.orly.loginapp.data.repository.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val preference: AppSharedPreference
) : ViewModel() {

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    val user = MutableLiveData<UserEntity>()

    fun login() = userRepository.login(
        email.value ?: "",
        password.value ?: ""
    )

    fun saveId(id: Int) = preference.saveId(id)

    fun saveEmail(email: String) = preference.saveEmail(email)
}