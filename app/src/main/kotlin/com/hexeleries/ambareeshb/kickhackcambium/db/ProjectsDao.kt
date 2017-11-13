package com.hexeleries.ambareeshb.kickhackcambium.db

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListProvider
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by ambareeshb on 28/10/17.
 */
@Dao
interface ProjectsDao {
    @Query("Select * from Projects")
    fun getAllProjects(): List<Projects>

    @Query("Select * from Projects WHERE backersCount > :benchMark")
    fun getFilteredBackers(benchMark:Int): LiveData<List<Projects>>

    @Query("Select * from Projects ORDER BY title ASC")
    fun getSortedProjects(): LiveData<List<Projects>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProjects(projects: List<Projects>)

    @Query("SELECT * from Projects")
    fun getPagedNews():LiveData<List<Projects>>

}