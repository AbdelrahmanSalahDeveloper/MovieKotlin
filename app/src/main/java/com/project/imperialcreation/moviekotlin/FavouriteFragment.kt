package com.project.imperialcreation.moviekotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.imperialcreation.moviekotlin.DetailsActivity.DetailsActivity
import com.project.imperialcreation.moviekotlin.DetailsActivity.DetailsActivity.Companion.prefsName
import com.project.imperialcreation.moviekotlin.ModelClass.MoviesClass
import kotlinx.android.synthetic.main.fragment_favourite.*
import java.util.*

class FavouriteFragment : Fragment(), RecycleAdaptorForMovies.OnItemClickedRecycleAdaptorInterface, DataBaseClass.DataBaseInteractionInterface {
    override fun getMessage(message: String) {

    }

    override fun onItemClicked(moviesClass: MoviesClass) {
        startActivity( Intent(activity, DetailsActivity::class.java).putExtra(MoviesFragment.specificPoster,
                moviesClass))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val display = activity!!.windowManager.defaultDisplay
        val width = display.width
        val squareWidth = (width - 5*2 - /*((2)*12)*/0)/2
        val arrayList = presenterDataBase!!.getFavouriteMovies() // get movies
        getAssendingDescingMoves(arrayList)
        val recycleAdaptor =  RecycleAdaptorForMovies(arrayList, squareWidth, activity!!,this)
        recycleView.layoutManager = GridLayoutManager(activity, 2)
        val spanCount = 3 // 3 columns
        val spacing = 0 // 50px
        val includeEdge = false

        recycleView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
        recycleView.adapter = recycleAdaptor


    }
    var prefs : SharedPreferences? = null
    private fun getAssendingDescingMoves(moviesArrayList: ArrayList<MoviesClass>) {
        if (prefs!!.getString(MoviesFragment.sortOrder,"").equals("des")){

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


    var presenterDataBase :DataBaseClass? = null
    lateinit var recycleView : RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        prefs =activity!!.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
      presenterDataBase = DataBaseClass (activity!!,this)
        val rootView =  inflater.inflate(R.layout.fragment_favourite, container, false)
        recycleView = rootView.findViewById<RecyclerView>(R.id.favouriteRecycleView)
        // Inflate the layout for this fragment و
        return rootView
    }


     var mListener: FavouriteFragmentInterface? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FavouriteFragmentInterface) {
            mListener = context
        } else {
//            throw RuntimeException(context!!.toString() + " must implement FavouriteFragmentInterface")
        }
    }

    var squareWidth: Int= 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface FavouriteFragmentInterface {
        fun onFragmentInteraction()
    }


}// Required empty public constructor
