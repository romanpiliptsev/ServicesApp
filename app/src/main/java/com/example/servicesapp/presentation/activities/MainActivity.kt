package com.example.servicesapp.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicesapp.MyApp
import com.example.servicesapp.data.network.model.ServiceDto
import com.example.servicesapp.databinding.ActivityMainBinding
import com.example.servicesapp.presentation.adapters.ServicesAdapter
import com.example.servicesapp.presentation.viewmodels.MainViewModel
import com.example.servicesapp.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val rvServices by lazy {
        binding.rvServices
    }

    private val component by lazy {
        (application as MyApp).component
    }

    private var list: List<ServiceDto> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ServicesAdapter()
        rvServices.adapter = adapter
        rvServices.layoutManager = LinearLayoutManager(this@MainActivity)

        CoroutineScope(Dispatchers.Main).launch {
            vm.getServiceList().observe(this@MainActivity) {
                adapter.services = it
                list = it
            }
        }
    }

    fun launchServiceActivity(view: View) {
        val serviceName = list[rvServices.indexOfChild(view)].name
        val intent = Intent(this, ServiceActivity::class.java)
        intent.putExtra("serviceName", serviceName)
        startActivity(intent)
    }
}