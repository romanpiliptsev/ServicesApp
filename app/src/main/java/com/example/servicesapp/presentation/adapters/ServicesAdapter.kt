package com.example.servicesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.servicesapp.R
import com.example.servicesapp.databinding.ServiceBinding
import com.example.servicesapp.domain.entities.ServiceInfo
import com.squareup.picasso.Picasso

class ServicesAdapter :
    ListAdapter<ServiceInfo, ServicesAdapter.ServiceViewHolder>(DiffCallback()) {

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = ServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ServiceViewHolder(private val binding: ServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(serviceItem: ServiceInfo) = with(binding) {
            serviceName.text = serviceItem.name
            Picasso.get().load(serviceItem.iconUrl).placeholder(R.drawable.ic_launcher_foreground)
                .into(serviceLogo)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ServiceInfo>() {
        override fun areItemsTheSame(oldItem: ServiceInfo, newItem: ServiceInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ServiceInfo, newItem: ServiceInfo): Boolean {
            return oldItem == newItem
        }
    }
}