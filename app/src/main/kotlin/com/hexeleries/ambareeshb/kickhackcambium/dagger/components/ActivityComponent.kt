package com.hexeleries.ambareeshb.kickhackcambium.dagger.components

import com.hexeleries.ambareeshb.kickhackcambium.dagger.ActivityScope
import com.hexeleries.ambareeshb.kickhackcambium.dagger.modules.ActivityModule
import com.hexeleries.ambareeshb.kickhackcambium.utils.FragmentUtils
import dagger.Component

/**
 * Created by ambareeshb on 28/10/17.
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun fragmentManager(): FragmentUtils
}