package ru.borodinskiy.aleksei.coffee.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.coffee.R
import ru.borodinskiy.aleksei.coffee.databinding.FragmentMenuBinding
import ru.borodinskiy.aleksei.coffee.databinding.FragmentRegistrationBinding
import ru.borodinskiy.aleksei.coffee.dto.User
import ru.borodinskiy.aleksei.coffee.utils.AndroidUtils
import ru.borodinskiy.aleksei.coffee.viewmodel.RegisterViewModel

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.apply {

            regButton.setOnClickListener {
                val login = login.text.toString()
                val password = password.text.toString()
                val confirmPassword = repeatPassword.text.toString()

                if (password == confirmPassword) {
                    viewModel.register(User(login, password))
                    AndroidUtils.hideKeyboard(requireView())
                } else {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.passwords_must_match),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

        }

        viewModel.state.observe(viewLifecycleOwner) { state ->

            binding.regButton.isEnabled = !state.loading

            if (state.successfulEntry) {
//                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                findNavController().navigateUp()
            }

            if (state.isBlank) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.it_is_necessary_to_fill_in_all_fields),
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