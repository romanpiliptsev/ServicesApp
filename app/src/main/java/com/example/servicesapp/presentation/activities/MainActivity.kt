package com.example.servicesapp.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicesapp.ServiceApp
import com.example.servicesapp.databinding.ActivityMainBinding
import com.example.servicesapp.domain.entities.ServiceInfo
import com.example.servicesapp.presentation.adapters.ServicesAdapter
import com.example.servicesapp.presentation.viewmodels.MainViewModel
import com.example.servicesapp.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm: MainViewModel by viewModels {
        viewModelFactory
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val rvServices by lazy {
        binding.rvServices
    }

    private val component by lazy {
        (application as ServiceApp).component
    }

    private var list: List<ServiceInfo> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ServicesAdapter()
        rvServices.adapter = adapter
        rvServices.layoutManager = LinearLayoutManager(this)

        vm.getServiceList()

        vm.getServiceListStateLiveData.observe(this) {
            when (it) {
                is MainViewModel.GetServiceListState.Error -> {
                    with(binding) {
                        rvServices.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        errorGroup.visibility = View.VISIBLE
                    }
                }
                is MainViewModel.GetServiceListState.Loaded -> {
                    with(binding) {
                        rvServices.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        errorGroup.visibility = View.GONE
                    }
                    adapter.submitList(it.serviceList)
                    list = it.serviceList
                }
                is MainViewModel.GetServiceListState.Loading -> {
                    with(binding) {
                        rvServices.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        errorGroup.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun launchServiceActivity(view: View) {
        val serviceName = list[rvServices.indexOfChild(view)].name
        startActivity(ServiceActivity.newIntent(this, serviceName))
    }

    fun retryDownload(view: View) {
        vm.getServiceList()
    }
}