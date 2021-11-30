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
import androidx.recyclerview.widget.LinearLayoutManager
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ShoppingCartFragmentBinding
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.view.ui.activities.FacturacionActivity
import com.source.sportivnyy.viewmodel.ShoppingCartViewModel

class   ShoppingCartFragment : Fragment() {

    companion object {
        fun newInstance() = ShoppingCartFragment()
    }
    private lateinit var viewModel: ShoppingCartViewModel
    private  var _binding: ShoppingCartFragmentBinding? = null
    private lateinit var intentFacturacion:Intent
    private lateinit var productosInCarritoAdapter:ShoppingCartItemAdapter
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
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        productosInCarritoAdapter = ShoppingCartItemAdapter(
            //Thanks to :https://kotlinlang.org/docs/functions.html#named-arguments
            onClickView = {adapterOnClick()}, onClickTrash = {adapterTrashOnClick()}
        )
        recyclerView.adapter = productosInCarritoAdapter

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
        viewModel = ViewModelProvider(this,ShoppingCartViewModel.
        ShoppingCartViewModelFactory(requireContext()))
            .get(ShoppingCartViewModel::class.java)
        viewModel.productos_in_carritoLiveData.observe(viewLifecycleOwner,
            {
                it?.let {
                    productosInCarritoAdapter.submitList(it as MutableList<Producto>)

                }
            }
        )

    }
    private fun adapterOnClick(){
        //TODO.Not yet implemented
        Toast.makeText(
            context,
            "Ha hecho click en un producto de su carrito",
            Toast.LENGTH_LONG
        ).show()
    }
    private fun adapterTrashOnClick(){
        //TODO.Not yet implemented
        Toast.makeText(
            context,
            "Ha eliminado un producto de su carrito",
            Toast.LENGTH_LONG
        ).show()
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