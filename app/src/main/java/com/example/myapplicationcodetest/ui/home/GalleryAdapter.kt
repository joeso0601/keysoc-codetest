package com.example.myapplicationcodetest.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplicationcodetest.R
import com.example.myapplicationcodetest.data.model.GalleryModel
import java.util.concurrent.Executors


class GalleryAdapter(

    private val galleryList: List<GalleryModel>,
    private val context: Context,
    var isShowBookMark : Boolean = false,

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

    fun toggleBookMark():Boolean{
        isShowBookMark = !isShowBookMark
        notifyDataSetChanged()
        return isShowBookMark
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val item = if(isShowBookMark) galleryList.filter { it.bookmark }[position] else galleryList[position]

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null

        executor.execute {

            val imageURL = item.artworkUrl100

            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)

                handler.post {
                    holder.img.setImageBitmap(image)
                }
            }

            catch (e: Exception) {
                e.printStackTrace()
            }
        }

        holder.tvName.text = item.collectionName
        if(item.bookmark){
            holder.itemView.setBackgroundColor(Color.YELLOW)

        }else{
            holder.itemView.setBackgroundColor(Color.WHITE)
        }


        holder.itemView.setOnClickListener{
            item.bookmark = !item.bookmark
            if(item.bookmark){
                holder.itemView.setBackgroundColor(Color.YELLOW)

            }else{
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
        }


    }

    override fun getItemCount(): Int {
        return if(isShowBookMark) galleryList.filter { it.bookmark }.size  else  galleryList.size
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img_item_gallery)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
    }
}