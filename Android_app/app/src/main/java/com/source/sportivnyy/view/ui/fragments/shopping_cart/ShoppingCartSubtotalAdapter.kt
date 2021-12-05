package com.source.sportivnyy.view.ui.fragments.shopping_cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.R

class ShoppingCartSubtotalAdapter:RecyclerView.Adapter<ShoppingCartSubtotalAdapter.ItemSubtotalViewHolder>() {
    var info_about_productos_and_prices = ArrayList<Pair<String,Long>>()
    class ItemSubtotalViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        private val nombre_producto : TextView = itemView.findViewById(R.id.nombre_producto_subtotal)
        private val precio_producto : TextView = itemView.findViewById(R.id.precio_producto_subtotal)
        private var current_pair : Pair<String,Long>? = null

        @SuppressLint("SetTextI18n")
        fun bind(pair: Pair<String,Long>){
            current_pair = pair
            nombre_producto.text = pair.first
            precio_producto.text = "${pair.second}$"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSubtotalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resume_precios_in_cart,parent,false)
        return ItemSubtotalViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemSubtotalViewHolder, position: Int) {
        val pair = info_about_productos_and_prices[position]
        holder.bind(pair)
    }

    override fun getItemCount(): Int {
        return info_about_productos_and_prices.size
    }
    fun updateData(data:List<Pair<String,Long>>){
        info_about_productos_and_prices.clear()
        info_about_productos_and_prices.addAll(data)
        notifyDataSetChanged()
    }
}