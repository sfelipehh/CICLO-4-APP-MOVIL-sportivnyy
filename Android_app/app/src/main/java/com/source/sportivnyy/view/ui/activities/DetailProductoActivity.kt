package com.source.sportivnyy.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivityDetailProductoBinding
import com.source.sportivnyy.view.ui.fragments.PRODUCT_ID
import com.source.sportivnyy.viewmodel.DetailProductoViewModel

import androidx.lifecycle.ViewModelProvider

class DetailProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetailProducto)
        val addToCartButton = findViewById<Button>(R.id.add_to_cart_button)
        addToCartButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Ha añadido el producto al carrito",
                Toast.LENGTH_LONG
            ).show()
        }
        val viewModel = ViewModelProvider(
            this,DetailProductoViewModel.DetailProductoViewModelFactory(applicationContext))
            .get(DetailProductoViewModel::class.java)

        var currentProductoId: Long? = null
        val bundle: Bundle? = intent.extras
        if (bundle!= null){
            currentProductoId = bundle.getLong(PRODUCT_ID)
        }
        currentProductoId?.let {
            val currentProducto = viewModel.getProductoForId(it)
            title = currentProducto?.name
        }


    }
}