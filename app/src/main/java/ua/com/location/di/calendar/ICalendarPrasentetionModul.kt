package ua.com.location.di.calendar

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.calendar.CalendarPresentation
import ua.com.location.presentation.calendar.ICalendarView
import ua.com.location.presentation.calendar.ICalerdarPresentation

@Module
class ICalendarPrasentetionModul(var iCalendarView: ICalendarView) {
    @Provides
    fun getICalendarPresetetion(): ICalerdarPresentation = CalendarPresentation(iCalendarView)
}