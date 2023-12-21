package ru.borodinskiy.aleksei.coffee.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.coffee.databinding.FragmentLoginBinding
import ru.borodinskiy.aleksei.coffee.databinding.FragmentMapBinding

@AndroidEntryPoint
class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMapBinding.inflate(inflater, container, false)


        return binding.root
    }
}