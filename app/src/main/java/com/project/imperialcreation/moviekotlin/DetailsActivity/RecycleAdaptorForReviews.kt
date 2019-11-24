package com.project.imperialcreation.moviekotlin.DetailsActivity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.imperialcreation.moviekotlin.ModelClass.ReviewsModel
import com.project.imperialcreation.moviekotlin.R
import kotlinx.android.synthetic.main.recycle_item_trailor.view.*

class RecycleAdaptorForReviews(val trailorsArrayList:ArrayList<ReviewsModel>, val context: Context
                                //this method is returning the view for each item in the list
) : RecyclerView.Adapter<RecycleAdaptorForReviews.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdaptorForReviews.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item_trailor, parent, false)
        return ViewHolder(v)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecycleAdaptorForReviews.ViewHolder, position: Int) {
        holder.bindItems(trailorsArrayList[position],context,trailorsArrayList)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return trailorsArrayList.size
    }



    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(currentReview: ReviewsModel, context: Context, trailorsArrayList: ArrayList<ReviewsModel>) {


/*            val link = "http://www.youtube.com/watch?v=" + currentPosterImage.key
           // val mc = MediaController(this)
           // mc.setAnchorView(itemView.videoTrailors)
           // mc.setMediaPlayer(itemView.videoTrailors)
            val video = Uri.parse(link)
         //   itemView.videoTrailors.setMediaController(mc)
            itemView.videoTrailors.setVideoURI(video)*/
           // videoView.start()
            //

            itemView.videoTrailors.text = currentReview.author +" Said That : "+currentReview.saidThat


        }

    }

}