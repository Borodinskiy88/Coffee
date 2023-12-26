package ru.borodinskiy.aleksei.coffee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.coffee.databinding.CardMenuBinding
import ru.borodinskiy.aleksei.coffee.dto.Menu
import ru.borodinskiy.aleksei.coffee.utils.LoadImage.load

class MenuAdapter() :
    ListAdapter<Menu, MenuAdapter.MenuViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {


        return MenuViewHolder(
            CardMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MenuViewHolder(
        private val binding: CardMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu) {

            binding.apply {

                menu.imageURL.let { image.load(it) }
                name.text = menu.name
                price.text = menu.price.toString()

            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem == newItem
            }
        }
    }

}