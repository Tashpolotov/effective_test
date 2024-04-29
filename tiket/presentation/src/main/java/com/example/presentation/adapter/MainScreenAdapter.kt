package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core_domain.model.mainscreen.Offer
import com.example.core_domain.model.mainscreen.OfferResponse
import com.example.presentation.R
import com.example.presentation.databinding.ItemTiketMusicBinding

class MainScreenAdapter:ListAdapter<Offer, MainScreenAdapter.MainScreenViewHolder>(MainScreenDiff()) {

    private val photo = listOf(
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3
    )

    inner class MainScreenViewHolder(private val binding:ItemTiketMusicBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Offer) {
                binding.img.setImageResource(photo[position % photo.size])
                binding.tvCity.text = model.town
                binding.tvName.text = model.title
                binding.tvPrice.text = model.price.value.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        return MainScreenViewHolder(ItemTiketMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MainScreenDiff:DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

}
