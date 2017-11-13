package com.hexeleries.ambareeshb.kickhackcambium

import android.app.Application
import com.hexeleries.ambareeshb.kickhackcambium.dagger.components.ApplicationComponent
import com.hexeleries.ambareeshb.kickhackcambium.dagger.components.DaggerApplicationComponent
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.ApplicationModule
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.DbModule

/**
 * Created by ambareeshb on 28/10/17.
 */
class App : Application() {
    companion object {
         lateinit var appComponent: ApplicationComponent

    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dbModule(DbModule(this))
                .build()
    }
}