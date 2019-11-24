package com.project.imperialcreation.moviekotlin.MovieInterface

import com.project.imperialcreation.moviekotlin.ModelClass.MoviesClass

/**
 * Created by abdelrahman on 5/24/2019.
 */
interface MovieInterface {
    fun getTopRatedMovies(topRatedList:ArrayList<MoviesClass>)
    fun getPopularMovies(popularListRating:ArrayList<MoviesClass>)
    fun getUpComingMovies(upComing:ArrayList<MoviesClass>)
    fun getNowPlayingMovies(nowPlayingMovies:ArrayList<MoviesClass>)
    fun onFailure(message : String)
    fun getLatestMovies(moviesArrayList: ArrayList<MoviesClass>)


}