package com.hexeleries.ambareeshb.kickhackcambium.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import com.hexeleries.ambareeshb.kickhackcambium.ProjectsViewModel
import com.hexeleries.ambareeshb.kickhackcambium.R
import com.hexeleries.ambareeshb.kickhackcambium.db.Projects
import com.hexeleries.ambareeshb.kickhackcambium.ui.activities.BrowserActivity
import com.hexeleries.ambareeshb.kickhackcambium.utils.Constants
import kotlinx.android.synthetic.main.fragment_project_list.*


/**
 * A simple [Fragment] subclass.
 */
class ProjectListFragment : Fragment(), ProjectAdapter.ProjectListener {


    private lateinit var adapter: ProjectAdapter

    companion object {
        fun newInstance(): ProjectListFragment {
            return ProjectListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_project_list, container, false)
    }

    /**
     * We obtain the [ProjectsViewModel] and call [ProjectsViewModel.loadProjects].The live data will keep on changing
     * with database insertion (after network call).
     * So will update List with [ProjectAdapter.setProjectList].
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()

        ViewModelProviders.of(this).get(ProjectsViewModel::class.java).loadProjects()?.observe(this,
                object : Observer<List<Projects>> {
                    override fun onChanged(projects: List<Projects>?) {
                        adapter.setProjectList(projects)
                        if (projects?.isNotEmpty() == true) {//Really !! ('projects' not null
                            // and 'isNotEmpty()' = true )
                            progressBar.visibility = View.GONE
                            recyclerViewProject.visibility = View.VISIBLE
                            //Hide snack bar
                            if (activity is MenuClick) {
                                (activity as MenuClick).finishUpdate()
                            }
                        }
                    }
                })
    }

    /**
     * Initialise [RecyclerView] for project.
     */
    private fun setUpRecycler() {
        adapter = ProjectAdapter()
        adapter.setListener(this)
        recyclerViewProject.adapter = adapter
        recyclerViewProject.layoutManager = LinearLayoutManager(this.activity, VERTICAL, false)
    }

    /**
     * Called when user clicks on go button [ProjectAdapter.ProjectListener.gotoWebPage]
     */
    override fun gotoWebPage(url: String, title: String) {
        val intent = Intent(activity, BrowserActivity::class.java)
        intent.putExtra(Constants.BUNDLE_ARG_URL, url)
        intent.putExtra(Constants.BUNDLE_ARG_TITLE, title)
        startActivity(intent)
    }

    /**
     * Sort clicked
     */
    fun sortClicked() {
        ViewModelProviders.of(this).get(ProjectsViewModel::class.java).sortedProjects()
    }

    /**
     * Filter Clicked
     */
    fun filterClicked() {
        ViewModelProviders.of(this).get(ProjectsViewModel::class.java).filteredProjects(90000)
    }

    interface MenuClick {
        fun sortClicked()
        fun filterClicked()
        fun finishUpdate()
    }
}
