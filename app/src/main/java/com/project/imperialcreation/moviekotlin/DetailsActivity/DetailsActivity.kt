package com.project.imperialcreation.moviekotlin.DetailsActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.imperialcreation.moviekotlin.DataBaseClass
import com.project.imperialcreation.moviekotlin.ModelClass.MoviesClass
import com.project.imperialcreation.moviekotlin.ModelClass.ReviewsModel
import com.project.imperialcreation.moviekotlin.ModelClass.TrailorsModel
import com.project.imperialcreation.moviekotlin.MovieInterface.InterfaceTrailorReviews
import com.project.imperialcreation.moviekotlin.MoviesFragment.Companion.specificPoster
import com.project.imperialcreation.moviekotlin.R
import com.project.imperialcreation.moviekotlin.RecycleAdaptorForMovies
import com.project.imperialcreation.moviekotlin.TrailorsReviews.PresenterTrailorReview
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.appbar_title.*

class DetailsActivity : AppCompatActivity(), InterfaceTrailorReviews, RecycleAdaptorForTrailors.TrailorItemInterface, DataBaseClass.DataBaseInteractionInterface {
    override fun getMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun getReviews(arrayListReviews: ArrayList<ReviewsModel>) {
         if (arrayListReviews.isNotEmpty()) {
             noReviewsText.visibility = View.GONE
             val reviewAdaptor = RecycleAdaptorForReviews(arrayListReviews, this)
             recycleViewReviews.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
             recycleViewReviews.isNestedScrollingEnabled = false
             recycleViewReviews.adapter = reviewAdaptor
         } else
             noReviewsText.visibility = View.VISIBLE

    }




    override fun onItemClickedTrailor(key : String) {

        try {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+ key))
            startActivity(Intent.createChooser(i, "openwith"))
        } catch (e: Exception) {
            val i = Intent(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + key)))
            startActivity(Intent.createChooser(i, "openwith"))
        }

        // open the intent here with the item
    }

    override fun getTrailorsVideos(arrayListTrailors: ArrayList<TrailorsModel>) {
        Log.v("this",arrayListTrailors.toString())

        val trailorAdaptor = RecycleAdaptorForTrailors(arrayListTrailors,this,this)
        trailorsRecycleView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        trailorsRecycleView.isNestedScrollingEnabled = false
        trailorsRecycleView.adapter = trailorAdaptor

        // lets put the result videos in the adaptor of recycleview


    }

    override fun getErrorOnFailed(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    var prefs : SharedPreferences ? = null
    var movieItem = MoviesClass()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        prefs =getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        movieItem = intent.extras!!.getParcelable<MoviesClass>(specificPoster)
        val presenterTrailor = PresenterTrailorReview(this,this)
        presenterTrailor.getMovieTrailors(movieItem.id)
        presenterTrailor.getReviews(movieItem.id)
        stateOfMovie()
        setMovieData()
        setImageView(movieImage)


    }

    private fun setImageView(movieImage: ImageView) {
        val url = RecycleAdaptorForMovies.baseUri + movieItem.getImagePath
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.loading_image)
        requestOptions.error(R.drawable.errorimage)
        Glide.with(this)
                .load(url)
                .apply(requestOptions)
                .into(movieImage)

    }

    private fun setMovieData() {
        movieTitle.text = movieItem.originalTitle
        setImageView(posterImage)
        releaseDate.text = movieItem.releaseDate
        voteTextView.text = movieItem.userVote + totalVote
        overViewText.text = movieItem.overViewMovie
    }
  val dbPresenter = DataBaseClass(this,this)
    private fun stateOfMovie() {
    if (prefs!!.getString(movieItem.originalTitle,"").equals(movieItem.originalTitle))
        favouriteImageVview.setImageResource(R.drawable.favourite_image)
        else
        favouriteImageVview.setImageResource(R.drawable.un_favourite_image)

        favouriteImageVview.setOnClickListener({
            if (prefs!!.getString(movieItem.originalTitle,"").equals(movieItem.originalTitle)){
                favouriteImageVview.setImageResource(R.drawable.un_favourite_image)
                prefs!!.edit().putString(movieItem.originalTitle,"").apply()
                if (dbPresenter.delete(movieItem.id))
                    getMessage(resources.getString(R.string.remove_success))
                else getMessage(resources.getString(R.string.error_happend))



            }else {
                favouriteImageVview.setImageResource(R.drawable.favourite_image)
                prefs!!.edit().putString(movieItem.originalTitle,movieItem.originalTitle).apply()
                dbPresenter.insertImages(movieItem.getImagePath, movieItem.id, movieItem.releaseDate, movieItem.overViewMovie
                        , movieItem.originalTitle, movieItem.userVote)


            }
        })

    }
    companion object {
        val totalVote = "/10"
        val prefsName = "movie"
    }

}
