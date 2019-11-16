package io.orly.loginapp.ui.register

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import io.orly.loginapp.R
import io.orly.loginapp.databinding.FragmentRegisterBinding
import io.orly.loginapp.di.Injectable
import io.orly.loginapp.di.factory.ViewModelFactory
import io.orly.loginapp.di.injectViewModel
import java.lang.Exception
import javax.inject.Inject

class RegisterFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            if (validateName() && validateEmail() && validatePassword()) {
                viewModel.user.value?.let { user ->
                    try {
                        viewModel.saveUser()
                        viewModel.saveEmail(user.email ?: "")
                        viewModel.saveId(user.id ?: 0)
                        binding.root.findNavController()
                            .navigate(
                                RegisterFragmentDirections.actionRegisterToLanding(
                                    user.name,
                                    user.email,
                                    user.id ?: 0
                                )
                            )
                    } catch (emailExist: SQLiteConstraintException) {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.email_already_exist),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.error),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        binding.tvLogin.setOnClickListener {
            binding.root.findNavController()
                .navigate(RegisterFragmentDirections.actionRegisterToLogin())
        }
    }

    private fun validateEmail(): Boolean = with(viewModel.user.value?.email) {
        if (isNullOrEmpty())
            binding.inputEmail.error = getString(io.orly.loginapp.R.string.user_email_empty_error)
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(this ?: "").matches()) {
            binding.inputEmail.error = getString(io.orly.loginapp.R.string.user_email_format_error)
        } else {
            binding.inputEmail.error = null
            return true
        }
        return false
    }

    private fun validatePassword(): Boolean = with(viewModel.user.value?.password) {
        if (isNullOrEmpty())
            binding.inputPassword.error = getString(io.orly.loginapp.R.string.password_length_error)
        else if (!io.orly.loginapp.util.CommonUtil.validPassword(this ?: "")) {
            binding.inputPassword.error = getString(io.orly.loginapp.R.string.password_format_error)
        } else {
            binding.inputPassword.error = null
            return true
        }
        return false
    }

    private fun validateName(): Boolean = with(viewModel.user.value?.name) {
        if (isNullOrEmpty())
            binding.inputName.error = getString(io.orly.loginapp.R.string.user_name_empty_error)
        else {
            binding.inputEmail.error = null
            return true
        }
        return false
    }
}