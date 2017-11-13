package com.hexeleries.ambareeshb.kickhackcambium.dagger.modules

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.hexeleries.ambareeshb.kickhackcambium.App
import com.hexeleries.ambareeshb.kickhackcambium.db.AppDb
import com.hexeleries.ambareeshb.kickhackcambium.db.ProjectsDao
import dagger.Module
import dagger.Provides

/**
 * Created by ambareeshb on 28/10/17.
 */
@Module
class DbModule(private val app: App) {
    @Provides
    fun provideAppDb(): AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, "ProjectsDB").build()
    }

    @Provides
    fun provideDao(db: AppDb): ProjectsDao {
        return db.projectsDao()
    }

}