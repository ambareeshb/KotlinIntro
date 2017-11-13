package com.hexeleries.ambareeshb.kickhackcambium.db

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by ambareeshb on 28/10/17.
 */
class TypeConverters{
        @TypeConverter
        fun fromTimestamp(value: Long): Date {
            return  Date(value)
        }

        @TypeConverter
        fun dateToTimestamp(date: Date): Long {
            return date.getTime()
        }
    }

