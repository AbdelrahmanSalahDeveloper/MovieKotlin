package com.project.imperialcreation.moviekotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.imperialcreation.moviekotlin.ModelClass.MoviesClass
import kotlinx.android.synthetic.main.recycle_item_movie.view.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions




class RecycleAdaptorForMovies(val moviesArrayList:ArrayList<MoviesClass>, val itemWidth : Int, val context: Context
                              , val onItemClickedRecycleAdaptorInterface: OnItemClickedRecycleAdaptorInterface //this method is returning the view for each item in the list
) : RecyclerView.Adapter<RecycleAdaptorForMovies.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleAdaptorForMovies.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item_movie, parent, false)
        return ViewHolder(v)

    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecycleAdaptorForMovies.ViewHolder, position: Int) {
        holder.bindItems(moviesArrayList[position],itemWidth,context, moviesArrayList,onItemClickedRecycleAdaptorInterface)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return moviesArrayList.size
    }



    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(currentPosterImage: MoviesClass, itemWidth: Int, context: Context, rewardsImageViews: ArrayList<MoviesClass>, onItemClickedRecycleAdaptorInterface: OnItemClickedRecycleAdaptorInterface) {
            val params = itemView.movieLinear.getLayoutParams()
            // Changes the height and width to the specified *pixels*
            params.height = itemWidth
            params.width = itemWidth
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.loading_image)
            requestOptions.error(R.drawable.errorimage)
            val url = baseUri + currentPosterImage.getImagePath
            Glide.with(context)
                    .load(url)
                    .apply(requestOptions)
                    .into(itemView.movie_poster_image_view)

            itemView.movie_poster_image_view.setOnClickListener({
                // when item clicked lisetener
                // get the item from the rewards image
                // and send it
                onItemClickedRecycleAdaptorInterface.onItemClicked(rewardsImageViews[adapterPosition])// this will get the object you clicked on

            })
          //  itemView.movie_poster_image_view.setImageResource(currentPosterImage.getImagePath)
            //   itemView.reward_imageview.minimumHeight = itemWidth
            //  itemView.reward_imageview.minimumWidth = itemWidth


            //



        }

    }
    companion object {
        val baseUri = "http://image.tmdb.org/t/p/w185//"
    }
    interface OnItemClickedRecycleAdaptorInterface {
       fun onItemClicked(moviesClass: MoviesClass)
    }

}
