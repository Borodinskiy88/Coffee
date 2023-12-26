package ru.borodinskiy.aleksei.coffee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.borodinskiy.aleksei.coffee.databinding.CardCoffeeShopsBinding
import ru.borodinskiy.aleksei.coffee.dto.Shops

interface OnInteractionListener {
    fun onShowMenu(shops: Shops)
}

class ShopsAdapter(
    private val onInteractionListener: OnInteractionListener
) :
    ListAdapter<Shops, ShopsAdapter.ShopsViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopsViewHolder {


        return ShopsViewHolder(
            CardCoffeeShopsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onInteractionListener
        )
    }

    override fun onBindViewHolder(holder: ShopsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ShopsViewHolder(
        private val binding: CardCoffeeShopsBinding,
        private val onInteractionListener: OnInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shops: Shops) {

            binding.apply {

                name.text = shops.name
                card.setOnClickListener {
                    onInteractionListener.onShowMenu(shops)
                }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Shops>() {
            override fun areItemsTheSame(oldItem: Shops, newItem: Shops): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Shops, newItem: Shops): Boolean {
                return oldItem == newItem
            }
        }
    }

}