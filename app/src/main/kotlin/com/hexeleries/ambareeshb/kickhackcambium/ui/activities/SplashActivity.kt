package com.hexeleries.ambareeshb.kickhackcambium.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.hexeleries.ambareeshb.kickhackcambium.R

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler()
        runnable = Runnable { val intent = Intent(this, HomeActivity::class.java); startActivity(intent);finish() }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)

    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
