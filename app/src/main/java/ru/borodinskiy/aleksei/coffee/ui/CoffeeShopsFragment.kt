package ru.borodinskiy.aleksei.coffee.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.coffee.databinding.FragmentCoffeeShopsBinding
import ru.borodinskiy.aleksei.coffee.databinding.FragmentOrderBinding

@AndroidEntryPoint
class CoffeeShopsFragment : Fragment() {
    private lateinit var binding: FragmentCoffeeShopsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoffeeShopsBinding.inflate(inflater, container, false)


        return binding.root
    }
}