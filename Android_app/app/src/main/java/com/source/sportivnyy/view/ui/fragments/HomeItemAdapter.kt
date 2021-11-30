package com.source.sportivnyy.view.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.Producto

class HomeItemAdapter(private val onClickView:(Producto)->Unit):
    ListAdapter<Producto,HomeItemAdapter.ItemInHomeViewHolder>(ItemInHomeDiffCallback){
        class ItemInHomeViewHolder(itemView:View, val onClickView: (Producto) -> Unit):
                RecyclerView.ViewHolder(itemView){
                    private val producto_in_home_ImageView:ImageView = itemView.findViewById(R.id.ivProducto_In_Home)
                    private val producto_in_home_NombreView:TextView = itemView.findViewById(R.id.tvNombre_Producto_In_Home)
                    private val producto_in_home_PrecioView:TextView = itemView.findViewById(R.id.tvPrecio_Producto_In_Home)
                    private var current_producto:Producto? = null
                    init {
                        itemView.setOnClickListener {
                            current_producto?.let{
                                onClickView(it)
                            }
                        }
                    }
                    @SuppressLint("SetTextI18n")
                    fun bind(item:Producto){
                        current_producto=item
                        producto_in_home_NombreView.text=item.name
                        producto_in_home_PrecioView.text="${item.precio.toString()}$"
                        if(item.image !=null){
                            //TODO.Not yet implemented
                            producto_in_home_ImageView.setImageResource(R.drawable.ic_baseline_shopping_bag_48)
                        }else{
                            producto_in_home_ImageView.setImageResource(R.drawable.ic_baseline_shopping_bag_48)
                        }

                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemInHomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_in_home,parent,false)
        return ItemInHomeViewHolder(view,onClickView)
    }

    override fun onBindViewHolder(holder: ItemInHomeViewHolder, position: Int) {
        val producto_in_home = getItem(position)
        holder.bind(producto_in_home)
    }
    }

object ItemInHomeDiffCallback:DiffUtil.ItemCallback<Producto>(){
    override fun areItemsTheSame(oldItem: Producto, newItem: Producto): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Producto, newItem: Producto): Boolean {
        return oldItem.id == newItem.id
    }
}