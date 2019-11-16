package io.orly.loginapp.util

import android.util.Patterns
import java.util.regex.Pattern

object CommonUtil {

    private const val passwordPattern = "^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[@#$%^&+=])(?=\\S+$).{8}\$"
    fun validPassword(password: String) =
        Pattern.compile(passwordPattern).matcher(password).matches()

    fun validEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}