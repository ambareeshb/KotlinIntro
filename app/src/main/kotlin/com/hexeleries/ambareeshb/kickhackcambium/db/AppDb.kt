package com.hexeleries.ambareeshb.kickhackcambium.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters


/**
 * Created by ambareeshb on 28/10/17.
 */
@Database(entities = arrayOf(Projects::class),version = 1, exportSchema = false)
@TypeConverters(com.hexeleries.ambareeshb.kickhackcambium.db.TypeConverters::class)
abstract class AppDb: RoomDatabase(){
    abstract fun projectsDao() : ProjectsDao
}

