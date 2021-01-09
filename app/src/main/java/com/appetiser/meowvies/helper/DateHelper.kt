package com.appetiser.meowvies.helper

import java.util.*

/**
 * DateHelper separate all the date helpers and be reusable (for future)
 */
object DateHelper {
    /**
     * @return the current date and time
     */
    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}