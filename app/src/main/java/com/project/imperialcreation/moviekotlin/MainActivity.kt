package com.project.imperialcreation.moviekotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.PopupMenu
import android.view.MenuItem
import android.view.View
import com.project.imperialcreation.moviekotlin.DetailsActivity.DetailsActivity.Companion.prefsName
import com.project.imperialcreation.moviekotlin.Setting.SettingFragment
import kotlinx.android.synthetic.main.toolbar_title.*
import java.util.*

class MainActivity : AppCompatActivity(), MoviesFragment.MovieInterfaceFragment, PopupMenu.OnMenuItemClickListener {
    override fun fragmentFunctionInteraction() { // this is implementation for Interface MovieFragment
    }

    var currentFragment : Fragment?= null
    var prefs : SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMovieFragment()
        prefs =getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        setLocalizationToApplication()
        changeLanguages.setOnClickListener({
            showMenu(it)
        })
        setting_button.setOnClickListener({
            showRestMenu(it)
        })

    }


    fun showMenu(v: View) {
        PopupMenu(this, v).apply {
            // MainActivity implements OnMenuItemClickListener
            setOnMenuItemClickListener(this@MainActivity)
            inflate(R.menu.menu_layout_lang)
            show()
        }
    }


    private fun showRestMenu(v: View?) {
        PopupMenu(this, v!!).apply {
            // MainActivity implements OnMenuItemClickListener
            setOnMenuItemClickListener(this@MainActivity)
            inflate(R.menu.rest_menu)
            show()
        }
        }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.arabic -> {
                prefs!!.edit().putString(language,"Arabic").apply()
                reloadActivity()
            //    setLocalizationToApplication()
                true
            }
            R.id.english -> {
                prefs!!.edit().putString(language,"English").apply()
               // setLocalizationToApplication()
                reloadActivity()
                true
            }
            R.id.favourite->{
                setFavouriteFragment()
                true
            }R.id.home->{
                setMovieFragment()
                true
            }R.id.setting->{
                setSettingFragment()
                true
            }
            else -> false
        }
    }

    override fun onBackPressed() { // lets handle this
        if (currentFragment is MoviesFragment)
            super.onBackPressed()
          else
            setMovieFragment()
    }

    private fun setSettingFragment() {
        currentFragment = SettingFragment()
        changeFragment(currentFragment as SettingFragment)    }

    private fun setFavouriteFragment(){
        currentFragment = FavouriteFragment()
        changeFragment(currentFragment as FavouriteFragment)

    }

    private fun reloadActivity() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }


    private fun setLocalizationToApplication() {
        var locale = Locale("ar")

        if (prefs!!.getString(language,"Arabic").equals("English")){
            locale = Locale("en")
        }

        Locale.setDefault(locale)
        // Create a new configuration object
        val config = Configuration()
        // Set the locale of the new configuration
        config.locale = locale
        // Update the configuration of the Accplication context
        resources.updateConfiguration(
                config,
                resources.displayMetrics
        )
        changeLanguages.text = getResources().getString(R.string.lang)
        toolbar_title.text = getResources().getString(R.string.movie)
    }

    private fun setMovieFragment() {
        currentFragment = MoviesFragment()
        changeFragment(currentFragment as MoviesFragment)
    }

    fun changeFragment(targetFragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()


    }
    companion object {
        val language = "lang"
    }
}
