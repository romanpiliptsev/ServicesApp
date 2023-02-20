package com.example.servicesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servicesapp.R
import com.example.servicesapp.data.network.model.ServiceDto
import com.squareup.picasso.Picasso

class ServicesAdapter : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    var services: List<ServiceDto> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.service, parent, false)

        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val serviceItem = services[position]

        holder.name.text = serviceItem.name
        Picasso.get().load(serviceItem.iconUrl).placeholder(R.drawable.ic_launcher_background)
            .into(holder.logo)
    }

    class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.service_name)
        val logo: ImageView = view.findViewById(R.id.service_logo)
    }
}