package fr.cdudit.composescanner.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatDate(date: Date, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.FRANCE).format(date.time)
    }
}