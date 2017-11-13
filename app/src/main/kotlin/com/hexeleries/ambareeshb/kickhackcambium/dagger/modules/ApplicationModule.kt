package com.hexeleries.ambareeshb.kickhackcambium.dagger.modules

import com.hexeleries.ambareeshb.kickhackcambium.App
import dagger.Module
import dagger.Provides

/**
 * Created by ambareeshb on 28/10/17.
 */
@Module
class ApplicationModule(private val application: App) {
    @Provides
   fun provideApplication() = application
}
