package com.triwaluyo07.androidsubmission2.preference

import android.content.Context
import com.triwaluyo07.androidsubmission2.data.model.Reminder

class ReminderPreference(context: Context) {
    companion object{
        const val PREF_NAME = "reminder_pref"
        private const val REMINDER = "isReminder"
    }

    private val preference = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

    fun setReminder(value: Reminder){
        val editor = preference.edit()
        editor.putBoolean(REMINDER,value.isReminded)
        editor.apply()
    }

    fun getReminder(): Reminder{
        val model = Reminder()
        model.isReminded = preference.getBoolean(REMINDER,false)
        return model
    }
}