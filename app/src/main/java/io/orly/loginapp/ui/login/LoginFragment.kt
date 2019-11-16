package io.orly.loginapp.ui.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import io.orly.loginapp.R
import io.orly.loginapp.databinding.FragmentLoginBinding
import io.orly.loginapp.di.Injectable
import io.orly.loginapp.di.factory.ViewModelFactory
import io.orly.loginapp.di.injectViewModel
import io.orly.loginapp.util.CommonUtil.validPassword
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.btnSignIn.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                viewModel.login().observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        viewModel.saveEmail(it.email ?: "")
                        viewModel.saveId(it.id ?: 0)
                        binding.root.findNavController().navigate(
                            LoginFragmentDirections.actionLoginToLanding(
                                it.name,
                                it.email,
                                it.id ?: 0
                            )
                        )
                    } else {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.account_doesnt_exist),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
        binding.tvRegistry.setOnClickListener {
            binding.root.findNavController()
                .navigate(LoginFragmentDirections.actionLoginToRegister())
        }
    }

    private fun validateEmail(): Boolean = with(viewModel.email.value) {
        if (isNullOrEmpty())
            binding.inputEmail.error = getString(R.string.user_email_empty_error)
        else if (!Patterns.EMAIL_ADDRESS.matcher(this ?: "").matches()) {
            binding.inputEmail.error = getString(R.string.user_email_format_error)
        } else {
            binding.inputEmail.error = null
            return true
        }
        return false
    }

    private fun validatePassword(): Boolean = with(viewModel.password.value) {
        if (isNullOrEmpty())
            binding.inputPassword.error = getString(R.string.password_length_error)
        else if (!validPassword(this ?: "")) {
            binding.inputPassword.error = getString(R.string.password_format_error)
        } else {
            binding.inputPassword.error = null
            return true
        }
        return false
    }

}