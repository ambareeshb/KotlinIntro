package com.hexeleries.ambareeshb.kickhackcambium.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.hexeleries.ambareeshb.kickhackcambium.App
import com.hexeleries.ambareeshb.kickhackcambium.R
import com.hexeleries.ambareeshb.kickhackcambium.dagger.components.ActivityComponent
import com.hexeleries.ambareeshb.kickhackcambium.dagger.components.DaggerActivityComponent
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.kickhackcambium.ui.ProjectListFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(),ProjectListFragment.MenuClick {


    private  val LOG_TAG = "MainActivity"

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
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
                R.id.search -> Log.d(LOG_TAG,"Search Clicked")
            R.id.sort -> sortClicked()
            R.id.filter -> filterClicked()

        }
        return true

    }
    /**
     * User clicked on sort.
     */
    override fun sortClicked() {
        Snackbar.make(homeRoot,"Sorting on project title",Snackbar.LENGTH_SHORT).show()
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as ProjectListFragment
        fragment.sortClicked()
    }

    /**
     * User clicked on filter.
     */
    override fun filterClicked() {
        Snackbar.make(homeRoot,"Filtering backers more than 10",Snackbar.LENGTH_SHORT).show()
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as ProjectListFragment
        fragment.filterClicked()

    }

}
