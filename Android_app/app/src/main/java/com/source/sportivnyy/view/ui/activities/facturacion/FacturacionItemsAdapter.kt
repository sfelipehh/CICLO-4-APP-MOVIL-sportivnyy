package com.source.sportivnyy.view.ui.activities.facturacion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.R
class FacturacionItemsAdapter{
        var info_nombres_y_precios = ArrayList<Pair<String,Long>>()
        class NombresFacturacionAdapter:
            RecyclerView.Adapter<NombresFacturacionAdapter.NombreItemViewHolder>(){
                var info_nombres = ArrayList<String>()
                class NombreItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                    private val nombre_producto: TextView = itemView.findViewById(R.id.nombre_producto_facturacion)
                    private var current_nombre : String? = null
                    fun bind(nombre:String){
                        current_nombre = nombre
                        nombre_producto.text=nombre
                    }
                }

            override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): NombreItemViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_nombre_producto_facturacion,parent,false)
                return NombreItemViewHolder(view)
            }

            override fun onBindViewHolder(holder: NombreItemViewHolder, position: Int) {
                val nombre = info_nombres[position]
                holder.bind(nombre)
            }

            override fun getItemCount(): Int {
                return info_nombres.size
            }
            fun updateData(data:List<String>){
                info_nombres.clear()
                info_nombres.addAll(data)
                notifyDataSetChanged()
            }
            }

        class PreciosFacturacionAdapter:
            RecyclerView.Adapter<PreciosFacturacionAdapter.PreciosItemViewHolder>(){
                var info_precios = ArrayList<Long>()
                class PreciosItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                    private val precio_producto:TextView = itemView.findViewById(R.id.precio_producto_facturacion)
                    private var current_precio : Long? = null
                    fun bind(precio:Long){
                        current_precio = precio
                        precio_producto.text = String.format("%d $",precio)
                    }
                }

            override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):PreciosItemViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_precio_producto_facturacion,parent,false)
                return PreciosItemViewHolder(view)
            }

            override fun onBindViewHolder(holder: PreciosItemViewHolder, position: Int) {
                val precio = info_precios[position]
                holder.bind(precio)
            }

            override fun getItemCount(): Int {
                return info_precios.size
            }
            fun updateData(data:List<Long>){
                info_precios.clear()
                info_precios.addAll(data)
                notifyDataSetChanged()
            }
            }

    fun actualizar(data: List<Pair<String,Long>>,adapterNombres:NombresFacturacionAdapter,adapterPrecios:PreciosFacturacionAdapter){
        info_nombres_y_precios.clear()
        info_nombres_y_precios.addAll(data)
        var nombres = ArrayList<String>()
        var precios = ArrayList<Long>()
        for (pair in data){
            nombres.add(pair.first)
            precios.add(pair.second)
        }
        adapterNombres.updateData(nombres)
        adapterPrecios.updateData(precios)
    }
}