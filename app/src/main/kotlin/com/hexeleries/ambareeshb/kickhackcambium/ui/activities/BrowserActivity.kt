package com.hexeleries.ambareeshb.kickhackcambium.ui.activities

import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.hexeleries.ambareeshb.kickhackcambium.R
import com.hexeleries.ambareeshb.kickhackcambium.ui.WebView
import com.hexeleries.ambareeshb.kickhackcambium.utils.Constants
import kotlinx.android.synthetic.main.activity_browser.*

class BrowserActivity : AppCompatActivity(),WebView.Listener {
    private var url = Constants.HACKER_EARTH_URL
    private var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        getBundleArgs()
        browserView.setListener(this)
        browserView.loadUrl(url)
        //Toolbar settings
        supportActionBar?.title = title //No title for Browser :)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    /**
     * Get arguments from Intent extra.
     */
    private fun getBundleArgs() {
        url += intent.getStringExtra(Constants.BUNDLE_ARG_URL)
        title = intent.getStringExtra(Constants.BUNDLE_ARG_TITLE)
    }

    /**
     * Url load finished[WebView.Listener.urlLoadFinished]
     */
    override fun urlLoadFinished() {
        progressWeb.visibility = View.GONE
        browserView.visibility = View.VISIBLE
    }

    /**
     * Back pressed.
     */

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {NavUtils.navigateUpFromSameTask(this)
            return true}
        }
        return super.onOptionsItemSelected(item)
    }
}
