package com.hexeleries.ambareeshb.kickhackcambium

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.hexeleries.ambareeshb.kickhackcambium.db.Projects

/**
 * Created by ambareeshb on 28/10/17.
 */
class ProjectsViewModel() : ViewModel() {
    private val projectRepo: ProjectRepository

    init {
        projectRepo = ProjectRepository(App.appComponent.projectDao(), App.appComponent.retrofitUtils().initRetrofit(ApiInterface::class.java))
    }


    /**
     * Load all projects from [ProjectRepository]
     */
    fun loadProjects(): LiveData<List<Projects>> {

        return projectRepo.loadProjects()
    }
    /**
     * Load Alpabetically sorted list of projects.
     */
    fun sortedProjects(): LiveData<List<Projects>> {

        return projectRepo.sortedProjects()
    }
    /**
     * Load Alpabetically sorted list of projects.
     */
    fun filteredProjects(value:Int): LiveData<List<Projects>> {

        return projectRepo.filteredProjects(value)
    }
}