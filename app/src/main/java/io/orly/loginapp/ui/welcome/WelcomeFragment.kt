package io.orly.loginapp.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import io.orly.loginapp.R
import io.orly.loginapp.databinding.FragmentWelcomeBinding
import io.orly.loginapp.di.Injectable

class WelcomeFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.welcome)
        binding.root
        setClickListener()
        return binding.root
    }

    private fun setClickListener() {
        binding.btnLogin.setOnClickListener {
            binding.root.findNavController()
                .navigate(WelcomeFragmentDirections.actionWelcomeToLogin())
        }
        binding.btnRegister.setOnClickListener {
            binding.root.findNavController()
                .navigate(WelcomeFragmentDirections.actionWelcomeToRegister())
        }
    }

}