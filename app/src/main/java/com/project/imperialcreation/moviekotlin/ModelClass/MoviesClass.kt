package com.project.imperialcreation.moviekotlin.ModelClass

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class MoviesClass() : Parcelable {


    @SerializedName("original_title")
    var originalTitle: String = ""
    @SerializedName("vote_average")
    var userVote: String = "0.0"
    @SerializedName("id")
    var id: String = "0"
    @SerializedName("release_date")
    var releaseDate: String = ""
    @SerializedName("poster_path")
    var getImagePath: String = ""
    @SerializedName("overview")
    var overViewMovie: String = ""

    constructor(parcel: Parcel) : this() {
        originalTitle = parcel.readSerializable() as String
        overViewMovie = parcel.readSerializable() as String
        releaseDate =  parcel.readSerializable() as String
        getImagePath =  parcel.readSerializable() as String
        userVote = parcel.readSerializable() as String//(Double::class.java.classLoader) as? Double
        id =  parcel.readSerializable() as String//readValue(Int::class.java.classLoader) as? Int
    }

    companion object CREATOR : Parcelable.Creator<MoviesClass> {
        override fun createFromParcel(parcel: Parcel): MoviesClass {
            return MoviesClass(parcel)
        }

        override fun newArray(size: Int): Array<MoviesClass?> {
            return arrayOfNulls(size)
        }
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(originalTitle)
        parcel.writeSerializable(overViewMovie)
        parcel.writeSerializable(releaseDate)
        parcel.writeSerializable(getImagePath)
        parcel.writeSerializable(userVote?:0.0)
        parcel.writeSerializable(id?:0)

    }

    override fun describeContents(): Int {
        return 0
    }

}

class MovieRetrofit { // this class is region class for call back
    @SerializedName("status")
    var status: String? = null
    // arraylist of objects from areas class
    @SerializedName("results") /*areas*/
    var moviesArrayList: ArrayList<MoviesClass>? =ArrayList<MoviesClass>()




}