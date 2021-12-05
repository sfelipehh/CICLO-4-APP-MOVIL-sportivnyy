package com.source.sportivnyy.view.ui.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference
import com.source.sportivnyy.databinding.HomeFragmentBinding
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.view.ui.activities.DetailProductoActivity
import com.source.sportivnyy.viewmodel.HomeViewModel
const val PRODUCT_INFO = "producto_info"

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private  var _binding: HomeFragmentBinding? = null

    private lateinit var productosInHomeAdapter:HomeItemAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container,false)
        val root:View = binding.root
        val recyclerView = binding.rvHome
        recyclerView.layoutManager = GridLayoutManager(root.context,2)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)
            .get(HomeViewModel::class.java)
        viewModel.refresh()
        productosInHomeAdapter = HomeItemAdapter (
            onClickView = {producto -> adapterOnClick(producto) }
        )
        binding.rvHome.apply { adapter=productosInHomeAdapter }
        viewModel.listProducts.observe(viewLifecycleOwner,
            Observer<List<Producto>>{
                productos -> productosInHomeAdapter.updateData(productos)
            }
        )

    }

    private fun adapterOnClick(producto:Producto){
        val goToProductIntent = Intent(activity, DetailProductoActivity::class.java)
            //"Ha accedido a la vista detallada de un producto"
        val bundle = bundleOf(PRODUCT_INFO to producto)
        goToProductIntent.putExtra(PRODUCT_INFO,bundle)
        startActivity(goToProductIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}