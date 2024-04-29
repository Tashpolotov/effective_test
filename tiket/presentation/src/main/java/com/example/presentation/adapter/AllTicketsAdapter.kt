package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core_domain.model.allteickets.AllTickets
import com.example.core_domain.model.allteickets.Ticket
import com.example.presentation.databinding.ItemAllTicketBinding
import com.example.presentation.viewmodel.AllTicketsViewModel
import java.time.Duration
import java.time.LocalDateTime

class AllTicketsAdapter:ListAdapter<Ticket, AllTicketsAdapter.AllTicketsViewHolder>(AllTicketsDiff()) {

    inner class AllTicketsViewHolder(private val binding:ItemAllTicketBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Ticket) {
            if(model.badge != null) {
                binding.cardUdobnyi.visibility = View.VISIBLE
                binding.tvUdob.text = model.badge
            }
            val departureTime = model.departure.date.substring(11, 16)
            val arrivalTime = model.arrival.date.substring(11, 16)

            binding.tvTime.text = "$departureTime - $arrivalTime"
            binding.tvPrice.text = model.price.value.toString() + " \u20BD"
            binding.tvAer.text = model.arrival.airport
            binding.tvVko.text = model.departure.airport

            val departureDateTime = LocalDateTime.parse(model.departure.date)
            val arrivalDateTime = LocalDateTime.parse(model.arrival.date)
            val flightDuration = Duration.between(departureDateTime, arrivalDateTime)
            val hours = flightDuration.toHours()
            val minutes = flightDuration.minusHours(hours).toMinutes()

            val flightTime = String.format("%02d:%02dч в пути", hours, minutes)
            val transferText = if (model.has_transfer) "" else "Без пересадок"
            val flightInfo = if (model.has_transfer) flightTime else "$flightTime / $transferText"
            binding.tvArrive.text = flightInfo

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllTicketsViewHolder {
        return AllTicketsViewHolder(ItemAllTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AllTicketsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AllTicketsDiff:DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }

}
