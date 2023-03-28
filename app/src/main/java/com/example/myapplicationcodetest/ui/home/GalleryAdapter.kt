package com.example.myapplicationcodetest.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.myapplicationcodetest.R
import com.example.myapplicationcodetest.data.model.GalleryModel


class GalleryAdapter(

    private val galleryList: List<GalleryModel>,
    private val context: Context

) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_gallery,
            parent, false
        )
        return GalleryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return galleryList.size
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img_item_gallery)
    }
}