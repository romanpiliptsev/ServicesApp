package com.example.servicesapp.presentation.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.servicesapp.R
import com.example.servicesapp.ServiceApp
import com.example.servicesapp.databinding.ActivityServiceBinding
import com.example.servicesapp.presentation.viewmodels.ServiceViewModel
import com.example.servicesapp.presentation.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ServiceActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm: ServiceViewModel by viewModels {
        viewModelFactory
    }

    private val binding by lazy {
        ActivityServiceBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as ServiceApp).component
    }

    private lateinit var serviceName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        serviceName = intent.getStringExtra(EXTRA_SERVICE_NAME) ?: ""

        vm.getService(serviceName)

        vm.getServiceStateLiveData.observe(this) {
            when (it) {
                is ServiceViewModel.GetServiceState.Error -> {
                    with(binding) {
                        okGroup.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        errorGroup.visibility = View.VISIBLE
                    }
                }
                is ServiceViewModel.GetServiceState.Loaded -> {
                    with(binding) {
                        okGroup.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        errorGroup.visibility = View.GONE

                        val service = it.service
                        name.text = service.name
                        description.text = service.description
                        link.text = service.serviceUrl
                        Picasso.get().load(service.iconUrl)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(logo)
                    }

                }
                is ServiceViewModel.GetServiceState.Loading -> {
                    with(binding) {
                        okGroup.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        errorGroup.visibility = View.GONE
                    }

                }
            }
        }
        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = serviceName
    }

    fun retryDownload(view: View) {
        vm.getService(serviceName)
    }

    companion object {
        private const val EXTRA_SERVICE_NAME = "serviceName"

        fun newIntent(context: Context, serviceName: String?): Intent {
            val intent = Intent(context, ServiceActivity::class.java)
            intent.putExtra(EXTRA_SERVICE_NAME, serviceName)
            return intent
        }
    }
}