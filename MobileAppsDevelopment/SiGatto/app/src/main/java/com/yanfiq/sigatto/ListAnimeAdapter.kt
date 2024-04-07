package com.yanfiq.sigatto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAnimeAdapter(private val listAnime: ArrayList<Anime>) : RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_anime, parent, false)
        return ListViewHolder(view)
    }
    override fun getItemCount(): Int = listAnime.size
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, img, desc) = listAnime[position]
        holder.imgPhoto.setImageResource(img)
        holder.tvName.text = name
        holder.tvDescription.text = desc
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listAnime[holder.adapterPosition]) }
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Anime)
    }
}