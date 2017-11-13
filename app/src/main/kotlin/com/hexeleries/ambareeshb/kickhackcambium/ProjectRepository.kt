package com.hexeleries.ambareeshb.kickhackcambium

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import com.hexeleries.ambareeshb.kickhackcambium.db.Projects
import com.hexeleries.ambareeshb.kickhackcambium.db.ProjectsDao
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ambareeshb on 28/10/17.
 */
class ProjectRepository(private val projectDao: ProjectsDao, private val apiInterface: ApiInterface) {
    val LOG_TAG = "PROJECT_REPO"
    private lateinit var projectList: LiveData<List<Projects>>
    private var shouldLoad = true



    /**
     * Whether to make network request.
     */
      fun loadProjects():LiveData<List<Projects>> {
        if (shouldLoad) fetchFromRemote()
        return fetchFromDB()
    }

    /**
     * Fetch paged list of 20 projects from DB
     */
    private fun fetchFromDB(): LiveData<List<Projects>> {
            return projectDao.getPagedNews()
    }

    /**
     * Fetch From API.
     */
    private fun fetchFromRemote() {
        shouldLoad = false
        apiInterface.getProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<List<Projects>>() {
                    override fun onError(e: Throwable?) {
                        Log.d(LOG_TAG, e.toString())
                        shouldLoad = true
                    }

                    override fun onCompleted() {
                        Log.d(LOG_TAG, "On completed network request")
                    }

                    override fun onNext(projects: List<Projects>?) {
                        Log.d(LOG_TAG, "On Next network request")
                        AsyncDbInsert().execute(projects)

                    }
                })

    }

    /**
     * Asyn process to insert Projects data to DB.
     */
    internal inner class AsyncDbInsert() : AsyncTask<List<Projects>, Unit, Unit>() {
        override fun doInBackground(vararg projects: List<Projects>?) {
            projectDao.insertProjects(projects = projects[0]!!)
        }
    }

    /**
     * Sorted based on [Projects.title] Ascending
     */
    fun sortedProjects(): LiveData<List<Projects>> {
        shouldLoad = true
        fetchFromRemote()
        return projectDao.getSortedProjects()
    }

    /**
     * Filtered based on [Projects.backersCount] > [param.benchMark]
     */
    fun filteredProjects( benchMark:Int): LiveData<List<Projects>> {
        shouldLoad = true
        fetchFromRemote()
        return projectDao.getFilteredBackers(benchMark)
    }


}