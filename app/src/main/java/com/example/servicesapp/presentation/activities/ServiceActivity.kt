package com.example.servicesapp.presentation.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.servicesapp.MyApp
import com.example.servicesapp.R
import com.example.servicesapp.databinding.ActivityServiceBinding
import com.example.servicesapp.presentation.viewmodels.MainViewModel
import com.example.servicesapp.presentation.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ServiceActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val binding by lazy {
        ActivityServiceBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as MyApp).component
    }

    private lateinit var serviceName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        serviceName = intent.getStringExtra("serviceName") ?: throw Exception()

        CoroutineScope(Dispatchers.Main).launch {
            val service = vm.getService(serviceName)

            with(binding) {
                name.text = service.name
                description.text = service.description
                link.text = service.serviceUrl
                Picasso.get().load(service.iconUrl).placeholder(R.drawable.ic_launcher_background)
                    .into(logo)
            }
        }

        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = serviceName
    }

    fun followLink(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse((view as TextView).text.toString()))
        startActivity(intent)
    }
}