package com.project.imperialcreation.moviekotlin.MovieInterface

import com.project.imperialcreation.moviekotlin.ModelClass.ReviewsModel
import com.project.imperialcreation.moviekotlin.ModelClass.TrailorsModel


interface InterfaceTrailorReviews {
    fun getTrailorsVideos(arrayListTrailors : ArrayList<TrailorsModel>)
    fun getErrorOnFailed(message : String)
    fun getReviews(arrayListReviews : ArrayList<ReviewsModel>)
}