package com.project.imperialcreation.moviekotlin.ModelClass

import com.google.gson.annotations.SerializedName

class ReviewsModel {

        @SerializedName("author")
        var author: String = ""
        @SerializedName("content")
        var saidThat: String = ""


    class ReviewsRetrofit { // this class is region class for call back
        @SerializedName("results")
        var reviewsArrayList: ArrayList<ReviewsModel>? = ArrayList<ReviewsModel>()


    }
}