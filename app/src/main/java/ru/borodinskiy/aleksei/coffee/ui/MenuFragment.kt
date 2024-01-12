package ru.borodinskiy.aleksei.coffee.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.borodinskiy.aleksei.coffee.adapter.MenuAdapter
import ru.borodinskiy.aleksei.coffee.databinding.FragmentMenuBinding
import ru.borodinskiy.aleksei.coffee.viewmodel.MenuViewModel

@AndroidEntryPoint
class MenuFragment : Fragment() {
    companion object {
        const val ID = "id"
    }

    private lateinit var binding: FragmentMenuBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MenuAdapter
    private val viewModel: MenuViewModel by activityViewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = MenuAdapter()

        recyclerView.adapter = adapter

        val id = arguments?.getInt(ID)

        if (id != null) {
            viewModel.getMenu(id).observe(viewLifecycleOwner) {
                adapter.submitList(listOf(it))
            }
        }

        return binding.root
    }
}