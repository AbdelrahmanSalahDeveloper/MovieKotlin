package com.project.imperialcreation.moviekotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.project.imperialcreation.moviekotlin.DetailsActivity.DetailsActivity
import com.project.imperialcreation.moviekotlin.ModelClass.MoviesClass
import com.project.imperialcreation.moviekotlin.MovieInterface.MovieInterface
import kotlinx.android.synthetic.main.fragment_movies.*
import android.R.attr.name
import com.project.imperialcreation.moviekotlin.DetailsActivity.DetailsActivity.Companion.prefsName
import java.util.*
import kotlin.collections.ArrayList


// Movie interface is the return of api
class MoviesFragment : Fragment(),MovieInterface, RecycleAdaptorForMovies.OnItemClickedRecycleAdaptorInterface {
    override fun onItemClicked(moviesClass: MoviesClass) {
        startActivity( Intent(activity, DetailsActivity::class.java).putExtra(specificPoster,
                moviesClass))

    }

    override fun getLatestMovies(moviesArrayList: ArrayList<MoviesClass>) {
        try {
         getAssendingDescingMoves(moviesArrayList)
        latestRecycleAdaptor =  RecycleAdaptorForMovies (moviesArrayList,squareWidth,activity!!,this)
        recycleLatest.layoutManager = LinearLayoutManager(activity!!, LinearLayout.HORIZONTAL, false)
        recycleLatest.adapter = latestRecycleAdaptor
        }catch (e : Exception){

        }

    }
  var prefs : SharedPreferences ? = null
    private fun getAssendingDescingMoves(moviesArrayList: ArrayList<MoviesClass>) {
        prefs =activity!!.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        if (prefs!!.getString(sortOrder,"").equals("des")){

            Collections.sort(moviesArrayList, object : Comparator<MoviesClass> {
                override fun compare(firstMovie: MoviesClass, secondMovie: MoviesClass): Int {
                    return secondMovie.originalTitle.compareTo(firstMovie.originalTitle)
                }
            })

        }else { // the default is asc
            Collections.sort(moviesArrayList, object : Comparator<MoviesClass> {
                override fun compare(firstMovie: MoviesClass, secondMovie: MoviesClass): Int {
                    return firstMovie.originalTitle.compareTo(secondMovie.originalTitle)
                }
            })
        }

    }

    override fun getUpComingMovies(upComing: ArrayList<MoviesClass>) {
        try {
            getAssendingDescingMoves(upComing)

            upComingRecycleAdaptor = RecycleAdaptorForMovies(upComing, squareWidth, activity!!,this)
            recycleUpComing.layoutManager = LinearLayoutManager(activity!!, LinearLayout.HORIZONTAL, false)
            recycleUpComing.adapter = upComingRecycleAdaptor
        }catch (e : Exception){

        }
    }

    override fun getNowPlayingMovies(nowPlayingMovies: ArrayList<MoviesClass>) {
       try {
           getAssendingDescingMoves(nowPlayingMovies)

           nowPlayingRecycleAdaptor =  RecycleAdaptorForMovies (nowPlayingMovies,squareWidth,activity!!,this)
        recycleNowPlaying.layoutManager = LinearLayoutManager(activity!!, LinearLayout.HORIZONTAL, false)
        recycleNowPlaying.adapter = nowPlayingRecycleAdaptor
    }catch (e : Exception){

    }
    }

    override fun getTopRatedMovies(topRatedList: ArrayList<MoviesClass>) {
     try {
         getAssendingDescingMoves(topRatedList)
         topRatedAdaptor =  RecycleAdaptorForMovies (topRatedList,squareWidth,activity!!,this)
        recycleViewTopRated.layoutManager = LinearLayoutManager(activity!!, LinearLayout.HORIZONTAL, false)
        recycleViewTopRated.adapter = topRatedAdaptor
    }catch (e : Exception){

    }
    }

    override fun getPopularMovies(popularListRating: ArrayList<MoviesClass>) {
        // return top rated
       try {

        getAssendingDescingMoves(popularListRating)

        popularAdaptor =   RecycleAdaptorForMovies (popularListRating,squareWidth,activity!!,this)
        recyclePopularMovie.layoutManager = LinearLayoutManager(activity!!, LinearLayout.HORIZONTAL, false)
        recyclePopularMovie.adapter = popularAdaptor
       }catch (e : Exception){

       }
    }

    override fun onFailure(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

    private var mListener: MovieInterfaceFragment? = null
    var moviePresenter : MoviePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    lateinit var  recycleViewTopRated : RecyclerView
    lateinit var  recyclePopularMovie : RecyclerView
    lateinit var  recycleUpComing : RecyclerView
    lateinit var  recycleLatest : RecyclerView
    lateinit var  recycleNowPlaying : RecyclerView
     var  squareWidth  =0
    var topRatedAdaptor : RecycleAdaptorForMovies? = null
    var popularAdaptor  : RecycleAdaptorForMovies ? = null
    var upComingRecycleAdaptor  : RecycleAdaptorForMovies ? = null
    var nowPlayingRecycleAdaptor  : RecycleAdaptorForMovies ? = null
    var latestRecycleAdaptor :   RecycleAdaptorForMovies ? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        moviePresenter = MoviePresenter(this,activity!!)
        val rootView = inflater.inflate(R.layout.fragment_movies, container, false)
        recycleViewTopRated = rootView.findViewById<RecyclerView>(R.id.topRatedRecycle)
        recyclePopularMovie = rootView.findViewById<RecyclerView>(R.id.popularRecycle)
        recycleNowPlaying = rootView.findViewById<RecyclerView>(R.id.nowPlayingRecycleView)
        recycleUpComing  = rootView.findViewById<RecyclerView>(R.id.upComingRecycleView)
        recycleLatest = rootView.findViewById<RecyclerView>(R.id.latestRecycleView)

        val display = activity!!.windowManager.defaultDisplay
        val width = display.width
         squareWidth = (width - 5 - ((2)*10))/2
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      popularLinear.setOnClickListener({
          if (popularExpand){
              popularImageViewArrow.setImageResource(R.drawable.arrow_close) // if it is open
              recyclePopularMovie.visibility = View.GONE
              popularExpand = false
              }

             else {
              popularImageViewArrow.setImageResource(R.drawable.arrow_expand)
              setValueForRecycleView(recyclePopularMovie,"popular")
              popularExpand = true
          }
      })
        topRatedLinear.setOnClickListener({
            if (topRatedExpand){
                topRatedImageView.setImageResource(R.drawable.arrow_close) // if it is open
                recycleViewTopRated.visibility = View.GONE
                topRatedExpand = false
            }

            else {
                topRatedImageView.setImageResource(R.drawable.arrow_expand)
                setValueForRecycleView(recycleViewTopRated,"top_rated")
                topRatedExpand = true
            }
        })

        upComingLinear.setOnClickListener({
            if (upcomingExpand){
                upComingImageView.setImageResource(R.drawable.arrow_close) // if it is open
                upComingRecycleView.visibility = View.GONE
                upcomingExpand = false
            }

            else {
                upComingImageView.setImageResource(R.drawable.arrow_expand)
                setValueForRecycleView(upComingRecycleView,"up_coming")
                upcomingExpand = true
            }
        })
        latestLinear.setOnClickListener({
            if (latestExpand){
                latestImageView.setImageResource(R.drawable.arrow_close) // if it is open
                latestRecycleView.visibility = View.GONE
                latestExpand = false
            }

            else {
                latestImageView.setImageResource(R.drawable.arrow_expand)
                setValueForRecycleView(latestRecycleView,"latest")
                latestExpand = true
            }
        })

        nowPlayingLinear.setOnClickListener({
            if (nowPlayingExpand){
                nowPlayingImageView.setImageResource(R.drawable.arrow_close) // if it is open
                nowPlayingRecycleView.visibility = View.GONE
                nowPlayingExpand = false
            }

            else {
                nowPlayingImageView.setImageResource(R.drawable.arrow_expand)
                setValueForRecycleView(nowPlayingRecycleView,"now_playing")
                nowPlayingExpand = true
            }
        })




    }

    // for horizontal mode


    private fun setValueForRecycleView(recycleView: RecyclerView, filter: String) {
        recycleView.visibility = View.VISIBLE
        // lets create the recycle adaptor now
        when(filter) {
            "popular"->moviePresenter!!.getPopularMovies()
            "top_rated"->moviePresenter!!.getTopRatedMovies()
            "up_coming"->moviePresenter!!.getTheUpComingMovies()
            "now_playing"->moviePresenter!!.getTheNowPlayingMovies()
            "latest"->moviePresenter!!.getLatestMovies()


        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MovieInterfaceFragment) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement MovieInterfaceFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface MovieInterfaceFragment {
        // TODO: Update argument type and name
        fun fragmentFunctionInteraction()
    }

var popularExpand  = false
 var nowPlayingExpand  = false
var topRatedExpand  = false
 var upcomingExpand = false
var latestExpand = false
    companion object {
        val specificPoster = "specific_poster"
         val  sortOrder = "asc"
    }


}// Required empty public constructor
