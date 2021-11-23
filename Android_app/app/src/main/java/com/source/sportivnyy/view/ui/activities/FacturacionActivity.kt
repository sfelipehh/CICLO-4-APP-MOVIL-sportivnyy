package com.source.sportivnyy.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.ui.AppBarConfiguration
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivityFacturacionBinding


class FacturacionActivity : AppCompatActivity() {
    // TODO: Rename and change types of parameters
    private lateinit var binding:ActivityFacturacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setActionBar(binding.toolbarFacturacion)
        binding = ActivityFacturacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttonFinalizarCompra = binding.btFinalizarCompraFacturacion
        buttonFinalizarCompra.setOnClickListener {
            finish()
        }

    }
    private fun facturacion(iva:Float,precioProductos:Set<Float>,){

    }
}