package ru.borodinskiy.aleksei.coffee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.coffee.R
import ru.borodinskiy.aleksei.coffee.adapter.OnInteractionListener
import ru.borodinskiy.aleksei.coffee.adapter.ShopsAdapter
import ru.borodinskiy.aleksei.coffee.databinding.FragmentCoffeeShopsBinding
import ru.borodinskiy.aleksei.coffee.dto.Shops
import ru.borodinskiy.aleksei.coffee.viewmodel.ShopsViewModel

@AndroidEntryPoint
class CoffeeShopsFragment : Fragment() {
    private lateinit var binding: FragmentCoffeeShopsBinding
    private val viewModel: ShopsViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShopsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCoffeeShopsBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ShopsAdapter(object : OnInteractionListener {

            override fun onShowMenu(shops: Shops) {
                val bundle = bundleOf(
                    Pair("id", shops.id)
                )
                findNavController().navigate(R.id.action_coffeeShopsFragment_to_menuFragment, bundle)
            }

        })

        recyclerView.adapter = adapter

        viewModel.getShops().observe(viewLifecycleOwner) {
            adapter.submitList(listOf(it))
        }

        return binding.root
    }
}