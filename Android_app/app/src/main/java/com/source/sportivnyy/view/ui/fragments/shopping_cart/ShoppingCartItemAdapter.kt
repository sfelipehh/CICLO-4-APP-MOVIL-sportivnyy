package com.source.sportivnyy.view.ui.fragments.shopping_cart

import android.annotation.SuppressLint
import android.util.Log
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
import com.source.sportivnyy.model.data.Carrito
import com.source.sportivnyy.model.data.Producto
import com.source.sportivnyy.model.network.CallbackCarrito
import com.source.sportivnyy.model.network.FirebaseServices
import com.source.sportivnyy.model.network.PRODUCTOS_COLLECTION_NAME
import com.squareup.picasso.Picasso
import java.lang.Exception

class ShoppingCartItemAdapter(private val onClickView:(Producto)-> Unit, private val onClickTrash:(Producto)-> Unit):
    RecyclerView.Adapter<ShoppingCartItemAdapter.ItemInCarritoViewHolder>(){
        var carrito = ArrayList<Producto>()
        class ItemInCarritoViewHolder(itemView:View, val onClickView:(Producto)->Unit,val onClickTrash:(Producto)->Unit):
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
                            current_producto?.let { onClickView(it) }


                        }
                        product_to_trash_button.setOnClickListener {
                            //Para saber que hacer cuando oprima el boton de eliminar
                                current_producto?.let{
                                    onClickTrash(it)
                                }

                        }
                    }
                    //Colocar informacion en las vistas
                    @SuppressLint("SetTextI18n")
                    fun bind(item:Producto){
                        current_producto=item
                        producto_in_carrito_NombreView.text=item.name
                        producto_in_carrrito_DescripcionView.text=item.resume_descripcion
                        producto_in_carrrito_PrecioView.text="${item.precio.toString()}$"
                        //R.dimen.image_in_carrito_size=100dp // 100px
                        if (item.imgurl != "ninguna"){
                                //TODO.Not yet implemented
                                Picasso.get().load(item.imgurl).resize(100,100)
                                    .into(producto_in_carrito_ImageView)
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
        val producto_in_carrito = carrito.get(position+1)
        holder.bind(producto_in_carrito!!)
    }

    override fun getItemCount(): Int {
        return carrito.size - 1
    }
    fun updateData(data:List<Producto>){
        carrito.clear()
        carrito.addAll(data)
        notifyDataSetChanged()
    }
}
