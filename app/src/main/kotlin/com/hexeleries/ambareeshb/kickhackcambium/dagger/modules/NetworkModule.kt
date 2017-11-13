package com.hexeleries.ambareeshb.kickhackcambium.dagger.modules

import com.hexeleries.ambareeshb.kickhackcambium.utils.RetrofitUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ambareeshb on 28/10/17.
 */
@Module
class NetworkModule{
    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitUtils = RetrofitUtils()
}