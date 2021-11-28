package com.source.sportivnyy.view.ui.fragments.shopping_cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.Producto

class ShoppingCartItemAdapter(private val onClickView:()-> Unit, private val onClickTrash:()-> Unit):
    ListAdapter<Producto,ShoppingCartItemAdapter.ItemInCarritoViewHolder>(ItemInCarritoDiffCallback){

        class ItemInCarritoViewHolder(itemView:View, val onClickView:()->Unit,val onClickTrash:()->Unit):
                RecyclerView.ViewHolder(itemView){
                    private val producto_in_carrito_ImageView:ImageView = itemView.findViewById(R.id.ivProducto_In_Carrito)
                    private val producto_in_carrito_NombreView:TextView = itemView.findViewById(R.id.tvNombre_Producto_In_Carrito)
                    private val producto_in_carrrito_DescripcionView:TextView = itemView.findViewById(R.id.tvDescripcion_Producto_In_Carrito)
                    private val producto_in_carrrito_PrecioView:TextView = itemView.findViewById(R.id.tvPrecio_Producto_In_Carrito)
                    private val product_to_trash_button:ImageButton = itemView.findViewById(R.id.product_to_trash_button)
                    private var current_producto:Producto? = null
                    //añade el evento cuando se toca el item
                    init {
                        itemView.setOnClickListener{
                            onClickView()

                        }
                        product_to_trash_button.setOnClickListener {
                            //Para saber que hacer cuando oprima el boton de eliminar
                                onClickTrash()

                        }
                    }
                    //Colocar informacion en las vistas
                    @SuppressLint("SetTextI18n")
                    fun bind(item:Producto){
                        current_producto=item
                        producto_in_carrito_NombreView.text=item.name
                        producto_in_carrrito_DescripcionView.text=item.descripcion
                        producto_in_carrrito_PrecioView.text="${item.precio.toString()}$"

                        if (item.image != null){
                                //TODO.Not yet implemented
                                producto_in_carrito_ImageView.setImageResource(R.drawable.ic_baseline_shopping_bag_48)
                        }else{
                            producto_in_carrito_ImageView.setImageResource(R.drawable.ic_baseline_shopping_bag_48)
                        }

                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemInCarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_in_cart,parent,false)
        return ItemInCarritoViewHolder(view, onClickView,onClickTrash)
    }

    override fun onBindViewHolder(holder: ItemInCarritoViewHolder, position: Int) {
        val producto_in_carrito = getItem(position)
        holder.bind(producto_in_carrito)
    }

}

object ItemInCarritoDiffCallback:DiffUtil.ItemCallback<Producto>(){
    override fun areItemsTheSame(oldItem: Producto, newItem: Producto): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Producto, newItem: Producto): Boolean {
        return oldItem.id == newItem.id
    }
}