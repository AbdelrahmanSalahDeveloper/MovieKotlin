package com.project.imperialcreation.moviekotlin.ModelClass

import com.google.gson.annotations.SerializedName


class TrailorsModel {

    @SerializedName("id")
    var idTrailor: String = ""
    @SerializedName("iso_639_1")
    var iso: String = ""
    @SerializedName("key")
    var key: String = "0"
    @SerializedName("name")
    var name: String = ""

}
class TrailorRetrofit { // this class is region class for call back
    @SerializedName("results")
    var trailorsArrayList: ArrayList<TrailorsModel>? =ArrayList<TrailorsModel>()




}