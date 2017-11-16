package com.hexeleries.ambareeshb.kickhackcambium.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.hexeleries.ambareeshb.kickhackcambium.R
import com.hexeleries.ambareeshb.kickhackcambium.db.Projects
import java.util.*


/**
 * Created by ambareeshb on 28/10/17.
 * Adapter class for project list
 */
class ProjectAdapter() : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
    private var projectList: List<Projects>? = null
    private lateinit var listener: ProjectListener


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(projectList?.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.project_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projectList?.size ?: 0
    }

    /**
     * On successful API update adapter data
     */
    fun setProjectList(list: List<Projects>?) {
        projectList = list
        notifyDataSetChanged()
    }

    /**
     * Set the onclick listener
     */
    fun setListener(listener: ProjectListener) {
        this@ProjectAdapter.listener = listener
    }

    /**
     * View holder class.
     */
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val pledge: TextView
        val days: TextView
        val by: TextView
        val goto: ImageButton

        init {
            title = view.findViewById<TextView>(R.id.projectTitle)
            pledge = view.findViewById<TextView>(R.id.projectPledge)
            days = view.findViewById(R.id.projectDays)
            by = view.findViewById(R.id.projectBy)
            goto = view.findViewById(R.id.gotoWebPage)

        }

        /**
         * Bind View with Corresponding [Projects]
         */
        fun bind(project: Projects?) {
            days.text = getDays(project?.endTime!!)
            title.text = project.title
            pledge.text = getCurrency() + project.amt
            by.text = project.by
            goto.setOnClickListener(newListener(project.url, project.title))
        }


        /**
         * Find number of days from Project end date
         */
        private fun getDays(toDate: Date): String {
            val days = toDate howManyDaysTo Date()
            return "$days Days to go"
        }


        private fun getCurrency(): String {
            return " $ "
        }


        /**
         * Find number of days from a date to given date.
         * Note: The api is old so dates are reversed in the UI for sensible display.
         */
        private infix fun Date.howManyDaysTo(date: Date): Long {
            val diff = date.time - this.time
            val daysInMillis = 1000 * 60 * 60 * 24 // 1 sec = 1000 milli;  1 min = 60 sec .... 1 day = 24 hour
            return diff / daysInMillis

        }

        /**
         * Returns a new [View.OnClickListener] that go to [Projects.url] onClick()
         */
        private fun newListener(url: String, title: String): View.OnClickListener?
                = View.OnClickListener {
            listener.gotoWebPage(url, title)
        }


    }

    interface ProjectListener {
        fun gotoWebPage(url: String, title: String)
    }

}