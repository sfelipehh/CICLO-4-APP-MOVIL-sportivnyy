package com.source.sportivnyy.view.ui.fragments.shopping_cart

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ShoppingCartFragmentBinding
import com.source.sportivnyy.view.ui.activities.FacturacionActivity

class   ShoppingCartFragment : Fragment() {

    companion object {
        fun newInstance() = ShoppingCartFragment()
    }
    private lateinit var viewModel: ShoppingCartViewModel
    private  var _binding: ShoppingCartFragmentBinding? = null
    private lateinit var intentFacturacion:Intent
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
        val continueBuyButton = binding.btContinueBuy

        continueBuyButton.setOnClickListener{
            requireActivity().startActivity(intentFacturacion)
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppingCartViewModel::class.java)
        // TODO: Use the ViewModel
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