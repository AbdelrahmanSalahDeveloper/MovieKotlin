package com.project.imperialcreation.moviekotlin

import android.content.Context
import com.google.gson.JsonObject
import com.project.imperialcreation.ServiceCall
import com.project.imperialcreation.ServiceCall.apiKey
import com.project.imperialcreation.moviekotlin.ModelClass.MovieRetrofit
import com.project.imperialcreation.moviekotlin.ModelClass.MoviesClass
import com.project.imperialcreation.moviekotlin.MovieInterface.MovieInterface
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*
import java.util.logging.Filter

class MoviePresenter (val moviesInterface: MovieInterface , val context :Context) { // the interface actually implemented in the fragement to call it by this presenter

    val service = ServiceCall.retrofit.create<GetMovies>(GetMovies::class.java)

    // lets create fun to get the popular movies
    fun getPopularMovies() {
        service.getPopularMovies(apiKey) // upload
                .enqueue(object : Callback<MovieRetrofit> {
                    override fun onResponse(call: Call<MovieRetrofit>?, response: Response<MovieRetrofit>?) {
                        if (response?.isSuccessful == true) {
                            if (response.body()?.moviesArrayList!!.isNotEmpty()){
                                moviesInterface.getPopularMovies(response.body()?.moviesArrayList!!)
                            } else {
                                moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                            }


                        }
                            else  {
                            moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))


                        }


                    }

                    override fun onFailure(call: Call<MovieRetrofit>?, t: Throwable?) {
                        moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                    }

                })
    }

    fun getTopRatedMovies() {
        service.getTopRatedMovies(apiKey) // upload
                .enqueue(object : Callback<MovieRetrofit> {
                    override fun onResponse(call: Call<MovieRetrofit>?, response: Response<MovieRetrofit>?) {
                        if (response?.isSuccessful == true) {
                            if (response.body()?.moviesArrayList!!.isNotEmpty()){
                                moviesInterface.getTopRatedMovies(response.body()?.moviesArrayList!!)
                            } else {
                                moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                            }


                        }
                        else  {
                            moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))


                        }


                    }

                    override fun onFailure(call: Call<MovieRetrofit>?, t: Throwable?) {
                        moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                    }

                })
    }

    fun getTheNowPlayingMovies() {
        service.getNowPlayingMovies(apiKey) // upload
                .enqueue(object : Callback<MovieRetrofit> {
                    override fun onResponse(call: Call<MovieRetrofit>?, response: Response<MovieRetrofit>?) {
                        if (response?.isSuccessful == true) {
                            if (response.body()?.moviesArrayList!!.isNotEmpty()){
                                moviesInterface.getNowPlayingMovies(response.body()?.moviesArrayList!!)
                            } else {
                                moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                            }


                        }
                        else  {
                            moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))


                        }


                    }

                    override fun onFailure(call: Call<MovieRetrofit>?, t: Throwable?) {
                        moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                    }

                })
    }

    fun getTheUpComingMovies() {
        service.getUpComingMovies(apiKey) // upload
                .enqueue(object : Callback<MovieRetrofit> {
                    override fun onResponse(call: Call<MovieRetrofit>?, response: Response<MovieRetrofit>?) {
                        if (response?.isSuccessful == true) {
                            if (response.body()?.moviesArrayList!!.isNotEmpty()){
                                moviesInterface.getUpComingMovies(response.body()?.moviesArrayList!!)
                            } else {
                                moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                            }


                        }
                        else  {
                            moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))


                        }


                    }

                    override fun onFailure(call: Call<MovieRetrofit>?, t: Throwable?) {
                        moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                    }

                })
    }
    fun getLatestMovies() {
        service.getLatest(apiKey) // upload
                .enqueue(object : Callback<MovieRetrofit> {
                    override fun onResponse(call: Call<MovieRetrofit>?, response: Response<MovieRetrofit>?) {
                        if (response?.isSuccessful == true) {
                            if (response.body()!!.moviesArrayList!!.isNotEmpty()/*response.body().getImagePath response.body()?.getImagePath!!.isNotEmpty()*/){
                               /* val arrayList = ArrayList<MoviesClass>()
                                val lastOne = MoviesClass()
                                lastOne.getImagePath = response.body()!!.getImagePath
                                lastOne.originalTitle = response.body()!!.originalTitle
                                lastOne.releaseDate = response.body()!!.releaseDate
                                lastOne.overViewMovie = response.body()!!.overViewMovie
                                lastOne.userVote = response.body()!!.userVote

                                arrayList.add(lastOne)
                                moviesInterface.getLatestMovies(arrayList)*/
                            } else {
                                moviesInterface.onFailure( context.getResources().getString(R.string.no_any_values))

                            }


                        }
                        else  {
                            moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))


                        }


                    }

                    override fun onFailure(call: Call<MovieRetrofit>?, t: Throwable?) {
                        moviesInterface.onFailure( context.getResources().getString(R.string.no_connection))

                    }

                })
    }



    interface GetMovies {
        @GET("popular")
        fun getPopularMovies(@Query("api_key") apiKey :String): Call<MovieRetrofit>

        @GET("top_rated")
        fun getTopRatedMovies(@Query("api_key") apiKey :String): Call<MovieRetrofit>

        @GET("upcoming")
        fun getUpComingMovies(@Query("api_key") apiKey :String): Call<MovieRetrofit>

        @GET("now_playing")
        fun getNowPlayingMovies(@Query("api_key") apiKey :String): Call<MovieRetrofit>

        @GET("latest")
        fun getLatest(@Query("api_key") apiKey :String): Call<MovieRetrofit>

    }



}