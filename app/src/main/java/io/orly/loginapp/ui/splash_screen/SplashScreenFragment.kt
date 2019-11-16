package io.orly.loginapp.ui.splash_screen

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import io.orly.loginapp.R
import io.orly.loginapp.databinding.FragmentSplashBinding
import io.orly.loginapp.di.Injectable

class SplashScreenFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        setUI()
        return binding.root
    }

    private fun setUI() {
        binding.lottieAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                binding.root.findNavController()
                    .navigate(
                        SplashScreenFragmentDirections.actionSplashToWelcomeFragment(),
                        NavOptions
                            .Builder()
                            .setPopUpTo(R.id.splashFragment, true)
                            .build()
                    )
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        binding.lottieAnimation.playAnimation()
    }
}