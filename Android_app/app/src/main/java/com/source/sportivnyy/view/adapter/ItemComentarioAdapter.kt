package com.source.sportivnyy.view.ui.activities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.R
import com.squareup.picasso.Picasso

class ItemComentarioAdapter():
    RecyclerView.Adapter<ItemComentarioAdapter.ItemComentarioViewHolder>(){
    var listComentarios = ArrayList<Map<String,Any>>()
        class ItemComentarioViewHolder(itemView:View):
                RecyclerView.ViewHolder(itemView){
                    private val author_image:ImageView = itemView.findViewById(R.id.comment_author_image)
                    private val author_name:TextView = itemView.findViewById(R.id.comment_author_name)
                    private val rate_bar:RatingBar = itemView.findViewById(R.id.comment_rate_bar)
                    private val comment_date:TextView = itemView.findViewById(R.id.comment_date)
                    private val comment_text:TextView = itemView.findViewById(R.id.comment_text)
                    private var current_comment:Map<String,Any>? =null

                    fun bind(item:Map<String,Any>){
                        /*keys for comentarios:
                    * author_comment -> string
                    * image_author -> string
                    * date ->string
                    * rate ->number
                    * text_comment -> string*/
                        current_comment=item
                        author_name.text=item["author_comment"].toString()
                        rate_bar.numStars = item["rate"].toString().toInt()
                        comment_date.text = item["date"].toString()
                        comment_text.text = item["text_comment"].toString()
                        if((item["image_author"] != "ninguna" )|| (item["image_author"] != "juan")){
                            Picasso.get().load(item["image_author"].toString()).resize(80,80).into(author_image)
                        }else{
                            if(item["image_author"] == "juan"){
                                author_image.setImageResource(R.mipmap.ic_photo_juan)
                            }else{
                                author_image.setImageResource(R.drawable.ic_baseline_account_circle_24_black)}

                        }
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemComentarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comentario,parent,false)
        return ItemComentarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemComentarioViewHolder, position: Int) {
        val comentario = listComentarios[position]
        holder.bind(comentario)
    }

    override fun getItemCount(): Int {
        return listComentarios.size
    }
    fun updateData(data:List<Map<String,Any>>){
        listComentarios.clear()
        listComentarios.addAll(data)
        notifyDataSetChanged()
    }
}
