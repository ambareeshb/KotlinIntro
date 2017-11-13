package com.hexeleries.ambareeshb.kickhackcambium.dagger.components

import com.hexeleries.ambareeshb.kickhackcambium.App
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.ApplicationModule
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.DbModule
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.NetworkModule
import com.hexeleries.ambareeshb.kickhackcambium.db.AppDb
import com.hexeleries.ambareeshb.kickhackcambium.db.ProjectsDao
import com.hexeleries.ambareeshb.kickhackcambium.utils.RetrofitUtils
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ambareeshb on 28/10/17.
 */
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class,DbModule::class))
@Singleton
interface ApplicationComponent{
    fun application(): App
    fun retrofitUtils() : RetrofitUtils
    fun projectDao(): ProjectsDao
}