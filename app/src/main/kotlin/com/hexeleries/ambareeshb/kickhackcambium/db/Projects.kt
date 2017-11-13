package com.hexeleries.ambareeshb.kickhackcambium.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Created by ambareeshb on 28/10/17.
 */
@Entity(tableName = "Projects")
class Projects(@JsonProperty("s.no") @PrimaryKey  val no:Long,
               @JsonProperty("amt.pledged")  val amt :Long,
               @JsonProperty("blurb")  val blurb:String,
               @JsonProperty("by")  val by:String,
               @JsonProperty("country")  val country:String,
               @JsonProperty("end.time")  val endTime: Date?,
               @JsonProperty("location")  val location:String,
               @JsonProperty("percentage.funded")  val percentageFund:String,
               @JsonProperty("num.backers")  val backersCount:String,
               @JsonProperty("state")  val state:String,
               @JsonProperty("title")  val title:String,
               @JsonProperty("type")  val type:String,
               @JsonProperty("url")  val url:String,
               @JsonProperty("currency")  val currency:String
)