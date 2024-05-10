package com.yanfiq.sigatto.ui.hobby

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yanfiq.sigatto.R

class ListBookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ListViewHolder(view)
    }
    override fun getItemCount(): Int = listBook.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, img, desc) = listBook[position]
        holder.imgPhoto.setImageResource(img)
        holder.tvName.text = name
        holder.tvDescription.text = desc
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listBook[holder.adapterPosition]) }
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}



