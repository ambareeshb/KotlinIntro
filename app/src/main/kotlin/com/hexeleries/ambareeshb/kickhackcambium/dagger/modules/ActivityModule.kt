package com.hexeleries.ambareeshb.kickhackcambium.dagger.modules

import android.support.v4.app.FragmentManager
import com.hexeleries.ambareeshb.kickhackcambium.dagger.ActivityScope
import com.hexeleries.ambareeshb.kickhackcambium.utils.FragmentUtils
import dagger.Module
import dagger.Provides


/**
 * Created by ambareeshb on 28/10/17.
 */
@Module
class ActivityModule(private val fragmentManager: FragmentManager){

    @ActivityScope
    @Provides
    fun provideFragmentUtils(): FragmentUtils = FragmentUtils(fragmentManager)
}