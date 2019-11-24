package com.project.imperialcreation.moviekotlin.Setting

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.imperialcreation.moviekotlin.DetailsActivity.DetailsActivity.Companion.prefsName
import com.project.imperialcreation.moviekotlin.MoviesFragment
import com.project.imperialcreation.moviekotlin.MoviesFragment.Companion.sortOrder

import com.project.imperialcreation.moviekotlin.R
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

     var prefs : SharedPreferences? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prefs =activity!!.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        if (prefs!!.getString(sortOrder,"").equals("asc")){
            ascendingImageView.setImageResource(R.drawable.checked_sort)
            decendingImageView.setImageDrawable(null)

        }else if (prefs!!.getString(sortOrder,"").equals("des")) {
            decendingImageView.setImageResource(R.drawable.checked_sort)
            ascendingImageView.setImageDrawable(null)

        }
        ascendingLinear.setOnClickListener({
            prefs!!.edit().putString(sortOrder,"asc").apply()
            ascendingImageView.setImageResource(R.drawable.checked_sort)
            decendingImageView.setImageDrawable(null)

        })
        descendingLinear.setOnClickListener({
            prefs!!.edit().putString(sortOrder,"des").apply()
            decendingImageView.setImageResource(R.drawable.checked_sort)
            ascendingImageView.setImageDrawable(null)


        })

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
          //  throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


}// Required empty public constructor
