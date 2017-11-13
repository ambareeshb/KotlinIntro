package com.hexeleries.ambareeshb.kickhackcambium

import com.hexeleries.ambareeshb.kickhackcambium.db.Projects
import com.hexeleries.ambareeshb.kickhackcambium.utils.Constants
import retrofit2.http.GET
import rx.Observable

/**
 * Created by ambareeshb on 28/10/17.
 */

interface ApiInterface {

    @GET(Constants.KICK_STARTER)
    fun getProjects(): Observable<List<Projects>>
}