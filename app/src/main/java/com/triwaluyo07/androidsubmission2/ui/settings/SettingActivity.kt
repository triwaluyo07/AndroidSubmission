package com.triwaluyo07.androidsubmission2.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.triwaluyo07.androidsubmission2.R
import com.triwaluyo07.androidsubmission2.data.model.Reminder
import com.triwaluyo07.androidsubmission2.databinding.ActivitySettingBinding
import com.triwaluyo07.androidsubmission2.preference.ReminderPreference
import com.triwaluyo07.androidsubmission2.receiver.AlarmReceiver

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        val reminderPreference = ReminderPreference(this)
        binding.switchAlarm.isChecked = reminderPreference.getReminder().isReminded
        
        alarmReceiver = AlarmReceiver()
        
        binding.switchAlarm.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(this,"RepeatingAlarm,","09:00","Github Reminder")
            }
            else
            {
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()

        reminder.isReminded = state
        reminderPreference.setReminder(reminder)

    }
}