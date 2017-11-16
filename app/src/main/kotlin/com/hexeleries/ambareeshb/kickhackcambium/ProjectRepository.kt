package com.hexeleries.ambareeshb.kickhackcambium

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask
import android.util.Log
import com.hexeleries.ambareeshb.kickhackcambium.db.Projects
import com.hexeleries.ambareeshb.kickhackcambium.db.ProjectsDao
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ambareeshb on 28/10/17.
 * From where data comes from.
 */
class ProjectRepository(private val projectDao: ProjectsDao, private val apiInterface: ApiInterface) {
    val LOG_TAG = "PROJECT_REPO"
    private var projectList: MediatorLiveData<List<Projects>>? = null
    private var shouldLoad = true

    enum class STATE {
        NORMAL, FILTER, SORT
    }

    private var state = STATE.NORMAL
    /**
     * Whether to make network request.
     */
    fun loadProjects(): MediatorLiveData<List<Projects>>? {
        if (shouldLoad) fetchFromRemote()
        return fetchFromDB()
    }

    /**
     * Fetch paged list of 20 projects from DB
     */
    private fun fetchFromDB(): MediatorLiveData<List<Projects>>? {
        projectList = (projectList ?: MediatorLiveData()).apply {
            addSource(projectDao.getPagedNews(), {
                value = when (state) {
                    STATE.NORMAL -> it
                    STATE.FILTER -> it?.filter { it.amt > 900000 }
                    STATE.SORT -> it?.sortedBy { it.title }
                }
            })
        }
        return projectList
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
    internal inner class AsyncDbInsert : AsyncTask<List<Projects>, Unit, Unit>() {
        override fun doInBackground(vararg projects: List<Projects>?) {
            projectDao.insertProjects(projects = projects[0]!!)
        }
    }

    /**
     * Sorted based on [Projects.title] Ascending
     */
    fun sortedProjects() {
        shouldLoad = true
        state = STATE.SORT
        fetchFromRemote()
    }

    /**
     * Filtered based on [Projects.backersCount] > [param.benchMark]
     */
    fun filteredProjects(benchMark: Int) {
        shouldLoad = true
        state = STATE.FILTER
        fetchFromRemote()
    }


}