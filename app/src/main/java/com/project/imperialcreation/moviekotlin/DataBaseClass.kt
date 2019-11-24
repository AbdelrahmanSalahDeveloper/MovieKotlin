package com.project.imperialcreation.moviekotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.project.imperialcreation.moviekotlin.ModelClass.MoviesClass

class DataBaseClass (val context: Context,val databaseInterface : DataBaseInteractionInterface) : SQLiteOpenHelper(context, dataBaseName, null, version) {

   override fun onCreate(db: SQLiteDatabase) {

            db.execSQL(
                    "create table "+ tableName + "(id text primary key,date text,title text, Image text,voting text,Overview text)")

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

            db.execSQL("DROP TABLE IF EXISTS "+ tableName)
            onCreate(db)

        }

        fun insertImages(Image: String, m_id: String, date: String, overview: String, title: String, user_voting: String): Boolean {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(movieImage, Image)
            contentValues.put(movieDate, date)
            contentValues.put(movieTitle, title)
            contentValues.put(userVoting, user_voting)
            contentValues.put(movieOverView, overview)
            contentValues.put(movieId, m_id)
            val id = db.insert(tableName, null, contentValues)
            databaseInterface.getMessage(context.resources.getString(R.string.loadSuccess))
            return true
        }

        fun getFavouriteMovies(): ArrayList<MoviesClass> {
            val db = this.readableDatabase
           val cursor  = db.rawQuery("SELECT * FROM "+tableName, null)
            cursor.moveToFirst()
            val movieArray :ArrayList<MoviesClass>? = ArrayList<MoviesClass>()
           while (!cursor.isAfterLast) {

               val movieClassObject = MoviesClass()
               movieClassObject.id = cursor.getString(cursor.getColumnIndex(movieId))
               movieClassObject.releaseDate =  cursor.getString(cursor.getColumnIndex(movieDate))
               movieClassObject.getImagePath = cursor.getString(cursor.getColumnIndex(movieImage))
               movieClassObject.originalTitle = cursor.getString(cursor.getColumnIndex(movieTitle))
               movieClassObject.overViewMovie = cursor.getString(cursor.getColumnIndex(movieOverView))
               movieClassObject.userVote =  cursor.getString(cursor.getColumnIndex(userVoting))

               movieArray!!.add(movieClassObject) // lets add the movie to our arraylist




               cursor.moveToNext()
           }
            cursor.close()
            return movieArray?: ArrayList<MoviesClass>()
        }


        fun delete(id: String): Boolean {
            val db = this.writableDatabase
            return db.delete(tableName, movieId + "=" + id, null) > 0
        }

        companion object {
            val dataBaseName = "Movie_DB"
            val tableName = "Movie_Table"
            val movieId = "id"
            val movieTitle = "title"
            val movieImage = "Image"
            val movieDate = "date"
            val userVoting = "voting"
            val movieOverView = "Overview"
            val version = 2

        }

    interface DataBaseInteractionInterface {
        fun getMessage(message: String)
    }


}