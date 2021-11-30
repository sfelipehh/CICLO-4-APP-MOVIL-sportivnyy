package com.source.sportivnyy.view.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivityDetailProductoBinding
import com.source.sportivnyy.view.ui.fragments.PRODUCT_ID
import com.source.sportivnyy.viewmodel.DetailProductoViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.model.data.Comentario

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
        val backTaskButton = findViewById<ImageButton>(R.id.back_task_detail_producto)

        backTaskButton.setOnClickListener { goBack_Finish() }

        val viewModel = ViewModelProvider(
            this,DetailProductoViewModel.DetailProductoViewModelFactory(applicationContext))
            .get(DetailProductoViewModel::class.java)

        val bundle: Bundle? = intent.extras
        val producto_name = binding.containerProductoDetail.detailProductoName
        val producto_image = binding.containerProductoDetail.detailProductoImage
        val producto_precio = binding.containerProductoDetail.detailProductoPrecio
        val producto_descripcion = binding.containerProductoDetail.detailProductoDescripcion
        bind(viewModel,bundle,producto_name,producto_image,producto_precio,producto_descripcion)
        val comentariosRecyclerView:RecyclerView = findViewById(R.id.comments_detail_producto)
        comentariosRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val itemComentariosAdapter = ItemComentarioAdapter()
        comentariosRecyclerView.adapter=itemComentariosAdapter

        viewModel.comentariosLiveData.observe(this,{
            it?.let {
                itemComentariosAdapter.submitList(it as MutableList<Comentario>)
            }
        })

    }
    fun goBack_Finish(){
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    fun bind(viewModel: DetailProductoViewModel, bundle: Bundle?,
             producto_name:TextView, producto_image:ImageView, producto_precio:TextView,
             producto_descripcion:TextView){
        var currentProductoId: Long? = null

        if (bundle!= null){
            currentProductoId = bundle.getLong(PRODUCT_ID)
        }
        currentProductoId?.let {
            val currentProducto = viewModel.getProductoForId(it)
            title = currentProducto?.name
            producto_name.text = currentProducto?.name
            //producto_image.setImageResource(currentProducto?.image)
            producto_precio.text = "${currentProducto?.precio}$"
            producto_descripcion.text = currentProducto?.descripcion
        }
    }
}