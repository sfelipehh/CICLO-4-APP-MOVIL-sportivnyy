package com.source.sportivnyy.view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.core.os.bundleOf
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivityDetailProductoBinding

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.source.sportivnyy.model.data.*
import com.source.sportivnyy.model.network.FirebaseServices
import com.source.sportivnyy.view.ui.activities.facturacion.FacturacionActivity
import com.source.sportivnyy.view.ui.fragments.PRODUCT_INFO
import com.source.sportivnyy.view.ui.fragments.shopping_cart.NOMBRES_Y_PRECIOS_KEY
import com.source.sportivnyy.view.ui.fragments.shopping_cart.PRODUCTOS_KEY
import com.squareup.picasso.Picasso

class DetailProductoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductoBinding
    private lateinit var itemComentariosAdapter: ItemComentarioAdapter
    private lateinit var currentProducto : Producto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetailProducto)
        val product_info = intent.extras
        val addToCartButton = findViewById<Button>(R.id.add_to_cart_button)
        addToCartButton.setOnClickListener {
            add_to_cart()
        }
        val backTaskButton = findViewById<ImageButton>(R.id.back_task_detail_producto)
        backTaskButton.setOnClickListener { goBack_Finish() }
        val buyButton = findViewById<Button>(R.id.direct_buy)
        buyButton.setOnClickListener { directFacturacion(product_info) }
        //val viewModel = ViewModelProvider(
            //this,DetailProductoViewModel.DetailProductoViewModelFactory(applicationContext))
            //.get(DetailProductoViewModel::class.java)


        val producto_name = binding.containerProductoDetail.detailProductoName
        val producto_image = binding.containerProductoDetail.detailProductoImage
        val producto_precio = binding.containerProductoDetail.detailProductoPrecio
        val producto_descripcion = binding.containerProductoDetail.detailProductoDescripcion
        val comentariosRecyclerView:RecyclerView = findViewById(R.id.comments_detail_producto)
        comentariosRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        itemComentariosAdapter = ItemComentarioAdapter()
        comentariosRecyclerView.adapter=itemComentariosAdapter
        bind(product_info,producto_name,producto_image,producto_precio,producto_descripcion)

    }
    fun goBack_Finish(){
        finish()
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }*/

    @SuppressLint("SetTextI18n")
    fun bind(bundle: Bundle?,
             producto_name:TextView, producto_image:ImageView, producto_precio:TextView,
             producto_descripcion:TextView){


        if (bundle!= null){
            currentProducto = bundle.getBundle(PRODUCT_INFO)!!.getSerializable(PRODUCT_INFO) as Producto
            currentProducto.let {
                //R.dimen.image_in_detail_size = 300dp / 300px
                title = it.name
                producto_name.text = it.name
                Picasso.get().load(it.imgurl).resize(300,300).into(producto_image)
                producto_precio.text = "${it.precio}$"
                producto_descripcion.text = it.descripcion
                itemComentariosAdapter.updateData(it.comentarios)
            }
        }
    }
    private fun add_to_cart(){
        val userId = FirebaseServices.FirebaseAuthService().firebaseAuth.currentUser!!.uid
        // add this product to carrito
        val data_producto = FirebaseServices.FirestoreService().productoToData(currentProducto)
        val complete = FirebaseServices.FirestoreService().firebaseFirestore.collection("users").document(userId)
            .update("carrito",FieldValue.arrayUnion(data_producto)).isComplete
        if(complete) {
            Toast.makeText(
                applicationContext,
                "Ha añadido el producto al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }else{
            Toast.makeText(
                applicationContext,
                "Puede que este producto ya este en el carrito",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    private fun directFacturacion(bundle: Bundle?){
        val intentFacturacion = Intent(applicationContext,FacturacionActivity::class.java)
        val producto = bundle!!.getBundle(PRODUCT_INFO)!!.getSerializable(PRODUCT_INFO) as Producto
        val bundle_productos = bundleOf(PRODUCTOS_KEY  to listOf(producto))
        val pair_nombre_precio = listOf(Pair(producto.name,producto.precio))
        val producto_nombre_precio = bundleOf(NOMBRES_Y_PRECIOS_KEY to pair_nombre_precio)
        intentFacturacion.putExtra(PRODUCTOS_KEY,bundle_productos)
        intentFacturacion.putExtra(NOMBRES_Y_PRECIOS_KEY,producto_nombre_precio)
        startActivity(intentFacturacion)
    }
}