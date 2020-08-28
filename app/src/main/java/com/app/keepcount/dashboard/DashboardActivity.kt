package com.app.keepcount.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.app.keepcount.R
import com.app.keepcount.home.HomeFragment
import com.app.keepcount.settings.view.SettingsFragment
import com.app.keepcount.statistics.StatisticsFragment
import com.google.android.material.snackbar.Snackbar
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class DashboardActivity : AppCompatActivity(), ChipNavigationBar.OnItemSelectedListener {
    lateinit var fragment: Fragment
    var closeApp: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initializeView()
        initializeToolbar()
    }

    private fun initializeView() {
        bottomMenu.setOnItemSelectedListener(this)
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbarLayout)


    }

    override fun onItemSelected(id: Int) {
        loadFragment(id)
    }


    override fun onBackPressed() {
       // super.onBackPressed()
        if (closeApp)
            finish()
        closeApp = true
        val snackBar = Snackbar.make(
            rootLayout,
            getString(R.string.txt_press_again_to_exit),
            Snackbar.LENGTH_SHORT
        )
        snackBar.show()

        snackBar.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                closeApp = false
            }
        })



    }

    private fun loadFragment(id: Int) {

        when (id) {
            R.id.home -> {
                fragment = HomeFragment()
            }

            R.id.statistics -> {
                fragment = StatisticsFragment()
            }

            R.id.settings -> {
                fragment = SettingsFragment()

            }
        }

        if (::fragment.isInitialized) {
            //    updateLoginHistory()
            supportActionBar?.title = title
            val fragmentTransaction = supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.sliding_in_left, R.anim.sliding_out_right)
                .replace(R.id.container, fragment)


            fragmentTransaction.setCustomAnimations(
                R.anim.sliding_in_left,
                R.anim.sliding_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )

            fragmentTransaction.commit()
        }
    }
}
