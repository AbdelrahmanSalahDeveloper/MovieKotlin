package com.project.imperialcreation.moviekotlin.TrailorsReviews

import android.content.Context
import com.project.imperialcreation.ServiceCall
import com.project.imperialcreation.ServiceCall.apiKey
import com.project.imperialcreation.moviekotlin.ModelClass.ReviewsModel
import com.project.imperialcreation.moviekotlin.ModelClass.TrailorRetrofit
import com.project.imperialcreation.moviekotlin.MovieInterface.InterfaceTrailorReviews
import com.project.imperialcreation.moviekotlin.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class PresenterTrailorReview(val trailorReviewsInterface: InterfaceTrailorReviews, val context: Context){
    // this is the interaction with api and the detaied activity

    fun getMovieTrailors (id : String){
        val service = ServiceCall.retrofit.create<GetTrailorsReviews>(GetTrailorsReviews::class.java)
        service.getTrailors(id,apiKey) // upload
                .enqueue(object : Callback<TrailorRetrofit> {
                    override fun onResponse(call: Call<TrailorRetrofit>?, response: Response<TrailorRetrofit>?) {
                        if (response?.isSuccessful == true) {
                            if (response.body()?.trailorsArrayList!!.isNotEmpty()){
                                trailorReviewsInterface.getTrailorsVideos(response.body()?.trailorsArrayList!!)
                            } else {
                                trailorReviewsInterface.getErrorOnFailed( context.getResources().getString(R.string.no_connection))

                            }


                        }
                        else  {
                            trailorReviewsInterface.getErrorOnFailed( context.getResources().getString(R.string.no_connection))


                        }


                    }

                    override fun onFailure(call: Call<TrailorRetrofit>?, t: Throwable?) {
                        trailorReviewsInterface.getErrorOnFailed( context.getResources().getString(R.string.no_connection))

                    }

                })

    }
    fun getReviews (id : String){
        val service = ServiceCall.retrofit.create<GetTrailorsReviews>(GetTrailorsReviews::class.java)
        service.getReviews(id,apiKey) // upload
                .enqueue(object : Callback<ReviewsModel.ReviewsRetrofit> {
                    override fun onResponse(call: Call<ReviewsModel.ReviewsRetrofit>?, response: Response<ReviewsModel.ReviewsRetrofit>?) {
                        if (response?.isSuccessful == true) {
                            if (response.body()?.reviewsArrayList!!.isNotEmpty()){
                                trailorReviewsInterface.getReviews(response.body()?.reviewsArrayList!!)
                            } else {
                                trailorReviewsInterface.getReviews(response.body()?.reviewsArrayList!!)

                            }


                        }
                        else  {
                            trailorReviewsInterface.getErrorOnFailed( context.getResources().getString(R.string.no_connection))


                        }


                    }

                    override fun onFailure(call: Call<ReviewsModel.ReviewsRetrofit>?, t: Throwable?) {
                        trailorReviewsInterface.getErrorOnFailed( context.getResources().getString(R.string.no_connection))

                    }

                })

    }

    interface GetTrailorsReviews {
        @GET("{movie_id}/videos")
        fun getTrailors(@Path("movie_id") movieId :String,@Query("api_key") apiKey :String): Call<TrailorRetrofit>

        @GET("{movie_id}/reviews")
        fun getReviews(@Path("movie_id") movieId :String,@Query("api_key") apiKey :String): Call<ReviewsModel.ReviewsRetrofit>

    }


}