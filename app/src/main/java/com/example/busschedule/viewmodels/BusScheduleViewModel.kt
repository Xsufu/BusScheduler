package com.example.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel (private val scheduleDao: ScheduleDao): ViewModel() {
    /**
     * Получение всего расписания
     */
    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    /**
     * Получение расписания у конкретной остановки
     *
     * @param name название остановки
     */
    fun scheduleForStopName(name: String): Flow<List<Schedule>> = scheduleDao.getByStopName(name)
}

class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }

}