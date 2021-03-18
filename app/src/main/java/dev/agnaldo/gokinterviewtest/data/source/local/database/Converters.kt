package dev.agnaldo.gokinterviewtest.data.source.local.database

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

private const val DATABASE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSZZZZ"

class Converters {
    @TypeConverter
    fun fromString(value: String?): Date? =
        value?.let { SimpleDateFormat(DATABASE_DATE_FORMAT, Locale.US).parse(value) }

    @TypeConverter
    fun dateToString(date: Date?): String? {
        return date?.let { SimpleDateFormat(DATABASE_DATE_FORMAT, Locale.US).format(it) }
    }

}
