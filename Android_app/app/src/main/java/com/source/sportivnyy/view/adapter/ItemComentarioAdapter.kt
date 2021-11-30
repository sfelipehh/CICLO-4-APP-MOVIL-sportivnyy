package com.source.sportivnyy.view.ui.activities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.source.sportivnyy.R
import com.source.sportivnyy.model.data.Comentario

class ItemComentarioAdapter():
    ListAdapter<Comentario,ItemComentarioAdapter.ItemComentarioViewHolder>(ItemComentarioDiifCallback){

        class ItemComentarioViewHolder(itemView:View):
                RecyclerView.ViewHolder(itemView){
                    private val author_image:ImageView = itemView.findViewById(R.id.comment_author_image)
                    private val author_name:TextView = itemView.findViewById(R.id.comment_author_name)
                    private val rate_bar:RatingBar = itemView.findViewById(R.id.comment_rate_bar)
                    private val comment_date:TextView = itemView.findViewById(R.id.comment_date)
                    private val comment_text:TextView = itemView.findViewById(R.id.comment_text)
                    private var current_comment:Comentario? =null

                    fun bind(item:Comentario){
                        current_comment=item
                        author_name.text=item.author
                        rate_bar.numStars = item.rate
                        comment_date.text = item.date
                        comment_text.text = item.comment_text
                        if(item.author_photo !=null){
                            //TODO.Not yet implemented
                            author_image.setImageResource(R.drawable.ic_baseline_account_circle_24_black)
                        }else{
                            author_image.setImageResource(R.drawable.ic_baseline_account_circle_24_black)
                        }
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemComentarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comentario,parent,false)
        return ItemComentarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemComentarioViewHolder, position: Int) {
        val comentario = getItem(position)
        holder.bind(comentario)
    }
}

object ItemComentarioDiifCallback:DiffUtil.ItemCallback<Comentario>(){
    override fun areItemsTheSame(oldItem: Comentario, newItem: Comentario): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Comentario, newItem: Comentario): Boolean {
        return oldItem.id==newItem.id
    }
}