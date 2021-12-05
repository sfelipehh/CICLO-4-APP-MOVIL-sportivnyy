package com.source.sportivnyy.view.ui.activities.facturacion

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FieldValue
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivityFacturacionBinding
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.network.FirebaseServices
import com.source.sportivnyy.view.ui.fragments.shopping_cart.NOMBRES_Y_PRECIOS_KEY
import com.source.sportivnyy.view.ui.fragments.shopping_cart.PRODUCTOS_KEY

const val IVA = 0.16
class FacturacionActivity : AppCompatActivity() {


    private lateinit var binding:ActivityFacturacionBinding
    private lateinit var nombres_adapter : FacturacionItemsAdapter.NombresFacturacionAdapter
    private lateinit var precios_adapter : FacturacionItemsAdapter.PreciosFacturacionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setActionBar(binding.toolbarFacturacion)
        binding = ActivityFacturacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val info = intent.extras
        nombres_adapter = FacturacionItemsAdapter.NombresFacturacionAdapter()
        precios_adapter = FacturacionItemsAdapter.PreciosFacturacionAdapter()
        bindAndFacturacion(info!!)
        val buttonFinalizarCompra = binding.btFinalizarCompraFacturacion
        buttonFinalizarCompra.setOnClickListener {
            clearCarrito(info)
            finish()
        }

    }
    private fun bindAndFacturacion(productos_y_precios:Bundle){
        val actual_info = productos_y_precios.getBundle(NOMBRES_Y_PRECIOS_KEY)!!.getSerializable(
            NOMBRES_Y_PRECIOS_KEY) as List<Pair<String,Long>>
        binding.rvNombreProductoFacturacion.apply {
            layoutManager=LinearLayoutManager(context)
            adapter = nombres_adapter
        }
        binding.rvPrecioProductoFacturacion.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=precios_adapter
        }
        FacturacionItemsAdapter().actualizar(actual_info,nombres_adapter,precios_adapter)
        val subtotal_view = binding.subtotalFacturacion
        val valor_iva_view = binding.valorIVAFacturacion
        val total_view = binding.valorTotalFacturacion
        val descuento_view = binding.valorDescuentoFacturacion
        val precios = ArrayList<Float>()
        var subtotal = 0.0
        var valor_IVA = 0.0
        var total = 0.0
        var porcentajeDescuento = 0.0
        var descuento = 0.0
        for (pair in actual_info){
            precios.add(pair.second.toFloat())
        }
        for (precio in precios){
            valor_IVA += (precio-(precio/1.16))
            subtotal += (precio/1.16)
        }
        total=subtotal+valor_IVA

        when(total){
            50000.0->porcentajeDescuento=0.1
            100000.0->porcentajeDescuento=0.25
            200000.0->porcentajeDescuento=0.45
        }
        if (total>500000.0)porcentajeDescuento = 0.5
        descuento = total*porcentajeDescuento
        total = total - descuento
        subtotal_view.text = String.format(getString(R.string.test_subtotal),subtotal)
        valor_iva_view.text = String.format(getString(R.string.valor_iva),valor_IVA)
        descuento_view.text = String.format(getString(R.string.descuento),descuento,(porcentajeDescuento*100).toString()+'%')
        total_view.text = String.format(getString(R.string.subtotal_mas_iva),total)


    }
    private fun clearCarrito(productos_y_precios:Bundle){
        val productos = productos_y_precios.getBundle(PRODUCTOS_KEY)!!.getSerializable(PRODUCTOS_KEY) as List<Producto>
        val firestoreService = FirebaseServices.FirestoreService()
        val userId = FirebaseServices.FirebaseAuthService().firebaseAuth.currentUser!!.uid
        val carrito = firestoreService.firebaseFirestore.collection("users").document(userId)
        if (productos.size > 1) {
            for (producto in productos) {
                if (producto!=null){
                val producto_data = firestoreService.productoToData(producto)
                carrito.update("carrito", FieldValue.arrayRemove(producto_data))}
            }

        }
    }
}