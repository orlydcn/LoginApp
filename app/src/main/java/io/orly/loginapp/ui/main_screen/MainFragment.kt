package io.orly.loginapp.ui.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.orly.loginapp.databinding.FragmentMainViewBinding
import io.orly.loginapp.di.Injectable
import io.orly.loginapp.di.factory.ViewModelFactory
import io.orly.loginapp.di.injectViewModel
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainViewBinding
    private val args: MainFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentMainViewBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.email.postValue(args.email)
        return binding.root
    }
}