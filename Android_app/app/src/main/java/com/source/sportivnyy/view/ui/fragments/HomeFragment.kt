package com.source.sportivnyy.view.ui.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.databinding.HomeFragmentBinding
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.view.ui.activities.DetailProductoActivity
import com.source.sportivnyy.viewmodel.HomeViewModel
const val PRODUCT_ID = "product id"
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
        productosInHomeAdapter = HomeItemAdapter (
            onClickView = {producto -> adapterOnClick(producto) }
                )
        recyclerView.adapter=productosInHomeAdapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,HomeViewModel.HomeViewModelFactory(requireContext()))
            .get(HomeViewModel::class.java)
        viewModel.productos_in_homeLiveData.observe(viewLifecycleOwner,
            {
                it?.let{
                    productosInHomeAdapter.submitList(it as MutableList<Producto>)
            }
            }
        )
    }

    private fun adapterOnClick(producto:Producto){
        val goToProductIntent = Intent(activity, DetailProductoActivity::class.java)
        /*Toast.makeText(
            context,
            "Ha accedido a la vista detallada de un producto",
            Toast.LENGTH_LONG
        ).show()*/
        goToProductIntent.putExtra(PRODUCT_ID,producto.id)
        requireActivity().startActivity(goToProductIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}