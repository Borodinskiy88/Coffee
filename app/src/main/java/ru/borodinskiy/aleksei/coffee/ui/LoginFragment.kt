package ru.borodinskiy.aleksei.coffee.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.coffee.R
import ru.borodinskiy.aleksei.coffee.databinding.FragmentCoffeeShopsBinding
import ru.borodinskiy.aleksei.coffee.databinding.FragmentLoginBinding
import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.apply {

            logButton.setOnClickListener {
                val login = login.text.toString()
                val password = password.text.toString()
                viewModel.login(User(login, password))
            }

        }

        viewModel.state.observe(viewLifecycleOwner) { state ->

            binding.logButton.isEnabled = !state.loading

            if (state.successfulEntry) {
                findNavController().navigate(R.id.action_loginFragment_to_coffeeShopsFragment)
            }

            if (state.isBlank) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.login_or_password_is_blank),
                    Snackbar.LENGTH_LONG
                ).show()
            }

            if (state.invalidLoginOrPassword) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.invalid_login_or_password),
                    Snackbar.LENGTH_LONG
                ).show()
            }

            if (state.error) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.something_went_wrong_try_again_later),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        return binding.root
    }
}