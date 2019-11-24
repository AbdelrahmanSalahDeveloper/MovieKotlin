package com.project.imperialcreation.moviekotlin.DetailsActivity

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.imperialcreation.moviekotlin.ModelClass.TrailorsModel
import com.project.imperialcreation.moviekotlin.R
import kotlinx.android.synthetic.main.recycle_item_trailor.view.*
import android.support.v4.media.session.MediaControllerCompat.setMediaController
import android.widget.VideoView



class RecycleAdaptorForTrailors(val trailorsArrayList:ArrayList<TrailorsModel>, val context: Context
                                , val onItemClickedRecycleAdaptorInterface: TrailorItemInterface //this method is returning the view for each item in the list
) : RecyclerView.Adapter<RecycleAdaptorForTrailors.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdaptorForTrailors.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item_trailor, parent, false)
        return ViewHolder(v)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecycleAdaptorForTrailors.ViewHolder, position: Int) {
        holder.bindItems(trailorsArrayList[position],context,trailorsArrayList,onItemClickedRecycleAdaptorInterface)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return trailorsArrayList.size
    }



    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(currentTrailor: TrailorsModel, context: Context, trailorsArrayList: ArrayList<TrailorsModel>, onItemClickedRecycleAdaptorInterface: TrailorItemInterface) {


/*            val link = "http://www.youtube.com/watch?v=" + currentPosterImage.key
           // val mc = MediaController(this)
           // mc.setAnchorView(itemView.videoTrailors)
           // mc.setMediaPlayer(itemView.videoTrailors)
            val video = Uri.parse(link)
         //   itemView.videoTrailors.setMediaController(mc)
            itemView.videoTrailors.setVideoURI(video)*/
           // videoView.start()
            //

            itemView.videoTrailors.text = currentTrailor.name
            itemView.videoTrailors.setOnClickListener({
                onItemClickedRecycleAdaptorInterface.onItemClickedTrailor(trailorsArrayList[adapterPosition].key)

            })


        }

    }
    companion object {
        val baseUri = "http://image.tmdb.org/t/p/w185//"
    }
    interface TrailorItemInterface {
       fun onItemClickedTrailor(key:String)
    }

}