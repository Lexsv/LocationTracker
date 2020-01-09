package ua.com.location.presentation.calendar


import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.calandar.*
import org.qap.ctimelineview.TimelineRow
import org.qap.ctimelineview.TimelineViewAdapter
import ua.com.location.R
import ua.com.location.di.calendar.DaggerCalendarComponent
import ua.com.location.di.calendar.ICalendarPrasentetionModul
import ua.com.location.presentation.BaseFragment
import ua.com.location.repository.data.LocalStoreVW
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class Calendar: BaseFragment(),ICalendarView {



    @Inject
    lateinit var presentation: ICalerdarPresentation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addDaggerDepand()
        LocalStoreVW.nowFragment = TAGCALENDAR
        return  inflater.inflate(R.layout.calandar, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val timelineRowsList: ArrayList<TimelineRow> = ArrayList()

// Create new timeline row (Row Id)
        // Create new timeline row (Row Id)
        val myRow = TimelineRow(0)

// To set the row Date (optional)
        // To set the row Date (optional)
        myRow.date = Date()
// To set the row Title (optional)
        // To set the row Title (optional)
        myRow.title = "Title"
// To set the row Description (optional)
        // To set the row Description (optional)
        myRow.description = "Description"
// To set the row bitmap image (optional)
        // To set the row bitmap image (optional)
        myRow.image = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
// To set row Below Line Color (optional)
        // To set row Below Line Color (optional)
        myRow.bellowLineColor = Color.argb(255, 0, 0, 0)
// To set row Below Line Size in dp (optional)
        // To set row Below Line Size in dp (optional)
        myRow.bellowLineSize = 6
// To set row Image Size in dp (optional)
        // To set row Image Size in dp (optional)
        myRow.imageSize = 40
// To set background color of the row image (optional)
        // To set background color of the row image (optional)
        myRow.backgroundColor = Color.argb(255, 0, 0, 0)
// To set the Background Size of the row image in dp (optional)
        // To set the Background Size of the row image in dp (optional)
        myRow.backgroundSize = 60
// To set row Date text color (optional)
        // To set row Date text color (optional)
        myRow.dateColor = Color.argb(255, 0, 0, 0)
// To set row Title text color (optional)
        // To set row Title text color (optional)
        myRow.titleColor = Color.argb(255, 0, 0, 0)
// To set row Description text color (optional)
        // To set row Description text color (optional)
        myRow.descriptionColor = Color.argb(255, 0, 0, 0)

// Add the new row to the list
        // Add the new row to the list
        timelineRowsList.add(myRow)
        timelineRowsList.add(myRow)
        timelineRowsList.add(myRow)
        timelineRowsList.add(myRow)
        timelineRowsList.add(myRow)
        timelineRowsList.add(myRow)
        timelineRowsList.add(myRow)


// Create the Timeline Adapter
        // Create the Timeline Adapter
        val myAdapter: ArrayAdapter<TimelineRow> = TimelineViewAdapter(
            context, 0, timelineRowsList,  //if true, list will be sorted by date
            false
        )

// Get the ListView and Bind it with the Timeline Adapter
        // Get the ListView and Bind it with the Timeline Adapter
   timeline_listView.setAdapter(myAdapter)

    }
    private fun addDaggerDepand() {
        DaggerCalendarComponent.builder()
            .iCalendarPrasentetionModul(ICalendarPrasentetionModul(this))
            .build()
            .inject(this)
    }
}