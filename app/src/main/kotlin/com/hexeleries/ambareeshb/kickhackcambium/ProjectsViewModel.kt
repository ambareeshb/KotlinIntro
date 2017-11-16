package com.hexeleries.ambareeshb.kickhackcambium

import android.arch.lifecycle.MediatorLiveData
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
    fun loadProjects(): MediatorLiveData<List<Projects>>? {
        return projectRepo.loadProjects()
    }
    /**
     * Load Alphabetically sorted list of projects.
     */
    fun sortedProjects() {
         projectRepo.sortedProjects()
    }
    /**
     * Filter projects.
     */
    fun filteredProjects(value:Int) {
         projectRepo.filteredProjects(value)
    }
}