package com.hexeleries.ambareeshb.kickhackcambium.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hexeleries.ambareeshb.kickhackcambium.App
import com.hexeleries.ambareeshb.kickhackcambium.R
import com.hexeleries.ambareeshb.kickhackcambium.dagger.components.ActivityComponent
import com.hexeleries.ambareeshb.kickhackcambium.dagger.components.DaggerActivityComponent
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.kickhackcambium.ui.ProjectListFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.custom_snack_bar.*

class HomeActivity : AppCompatActivity(), ProjectListFragment.MenuClick {
    override fun finishUpdate() {
//        hideSnackBar()
    }


    private val LOG_TAG = "MainActivity"

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initActivityComponent()
        loadFragment()
    }

    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(App.appComponent)
                .activityModule(ActivityModule(supportFragmentManager)).build()
    }

    private fun loadFragment() {
        activityComponent.fragmentManager()
                .replace(R.id.fragment_container, (ProjectListFragment.newInstance()))
                .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.search -> Log.d(LOG_TAG, "Search Clicked")
            R.id.sort -> sortClicked()
            R.id.filter -> filterClicked()
        }
        return true
    }

    /**
     * User clicked on sort.
     */
    override fun sortClicked() {
        showSnackbar("Sorting on project title")
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as ProjectListFragment
        fragment.sortClicked()
    }

    /**
     * User clicked on filter.
     */
    override fun filterClicked() {
        showSnackbar("Filtering on backers")
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as ProjectListFragment
        fragment.filterClicked()

    }

    private fun hideSnackBar() {
        snackbar_text.text = ""
        snackBar.visibility = View.GONE
    }

    fun showSnackbar(content: String) {
//        snackbar_text.text = content
//        snackBar.visibility = View.VISIBLE
        Snackbar.make(homeRoot, content, Snackbar.LENGTH_LONG).show()
    }

}
