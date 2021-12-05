package com.source.sportivnyy.view.ui.fragments.shopping_cart

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FieldValue
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ShoppingCartFragmentBinding
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.network.FirebaseServices
import com.source.sportivnyy.view.ui.activities.DetailProductoActivity
import com.source.sportivnyy.view.ui.activities.facturacion.FacturacionActivity
import com.source.sportivnyy.view.ui.fragments.PRODUCT_INFO
import com.source.sportivnyy.viewmodel.ShoppingCartViewModel

const val PRODUCTOS_KEY = "productos"
const val NOMBRES_Y_PRECIOS_KEY = "nombres y precios"
class   ShoppingCartFragment : Fragment() {

    companion object {
        fun newInstance() = ShoppingCartFragment()
    }
    private lateinit var viewModel: ShoppingCartViewModel
    private var _binding: ShoppingCartFragmentBinding? = null
    private lateinit var intentFacturacion:Intent
    private lateinit var productosInCarritoAdapter:ShoppingCartItemAdapter
    private lateinit var subtotalAdapter: ShoppingCartSubtotalAdapter
    private var carritoVacio = true
    private var subtotal = 0.0
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        intentFacturacion = Intent(activity, FacturacionActivity::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShoppingCartFragmentBinding.inflate(inflater, container, false)
        val root : View =binding.root
        val recyclerView = binding.rvShoppingCart
        val resume_recyclerView = binding.rvResumePrecios
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        resume_recyclerView.layoutManager = LinearLayoutManager(root.context)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val continueBuyButton = binding.btContinueBuy

        continueBuyButton.setOnClickListener{
            requireActivity().startActivity(intentFacturacion)
        }


    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(ShoppingCartViewModel::class.java)
        viewModel.refresh()
        productosInCarritoAdapter = ShoppingCartItemAdapter(
            //Thanks to :https://kotlinlang.org/docs/functions.html#named-arguments
            onClickView = {producto -> adapterOnClick(producto)},
            onClickTrash = {producto-> adapterTrashOnClick(producto)}
        )
        subtotalAdapter = ShoppingCartSubtotalAdapter()
        binding.rvShoppingCart.apply { adapter=productosInCarritoAdapter }
        binding.rvResumePrecios.apply { adapter = subtotalAdapter }
        viewModel.listProductos_Carrito.observe(viewLifecycleOwner,
        Observer<List<Producto>>{
            carrito -> productosInCarritoAdapter.updateData(carrito)
            if(carrito.size>1)carritoVacio=false
            binding.btContinueBuy.isEnabled = !carritoVacio
            val bundle = bundleOf(PRODUCTOS_KEY  to carrito)
            intentFacturacion.putExtra(PRODUCTOS_KEY,bundle)
        })
        viewModel.resumeForSubtotal.observe(viewLifecycleOwner,
        Observer<List<Pair<String,Long>>> {
            productos_y_precios -> subtotalAdapter.updateData(productos_y_precios)
            val bundle = bundleOf(NOMBRES_Y_PRECIOS_KEY  to productos_y_precios)
            intentFacturacion.putExtra(NOMBRES_Y_PRECIOS_KEY,bundle)
            subtotal = 0.0
            for (pair in productos_y_precios) subtotal += pair.second
            binding.subtotal.text = String.format(getString(R.string.subtotal_iva),subtotal)
        })

    }
    private fun adapterOnClick(producto: Producto){
        val goToProductIntent = Intent(activity, DetailProductoActivity::class.java)
        //"Ha accedido a la vista detallada de un producto"
        val bundle = bundleOf(PRODUCT_INFO to producto)
        goToProductIntent.putExtra(PRODUCT_INFO,bundle)
        startActivity(goToProductIntent)
        viewModel.refresh()
    }
    private fun adapterTrashOnClick(producto: Producto){
        val data_producto = FirebaseServices.FirestoreService().productoToData(producto)
        FirebaseServices.FirestoreService().firebaseFirestore
            .collection("users").document(viewModel.userId)
            .update("carrito", FieldValue.arrayRemove(data_producto)).isComplete
        Toast.makeText(
                context,
                "Ha eliminado el producto al carrito",
                Toast.LENGTH_LONG
            ).show()
        viewModel.listProductos_Carrito.observe(viewLifecycleOwner,
            Observer<List<Producto>>{
                    carrito -> productosInCarritoAdapter.updateData(carrito)
            })
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItems = setOf(R.id.to_shopping_cart,R.id.to_account)
        for (item in menuItems){
            menu.findItem(item).isVisible=false
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}