package ua.com.location.di.calendar

import dagger.Component
import ua.com.location.presentation.calendar.Calendar

@Component(modules = arrayOf(ICalendarPrasentetionModul::class,ICalendarViewProvid::class))
interface CalendarComponent {
        fun inject(calendar: Calendar)
}