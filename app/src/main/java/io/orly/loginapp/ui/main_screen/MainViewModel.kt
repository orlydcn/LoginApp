package io.orly.loginapp.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import io.orly.loginapp.data.local.room.entity.UserEntity
import io.orly.loginapp.data.repository.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {

    val userId = MutableLiveData<Int>()

    val email = MutableLiveData<String>()

    val user: LiveData<UserEntity?> = email.switchMap {
        userRepository.getUserByEmail(it)
    }

}