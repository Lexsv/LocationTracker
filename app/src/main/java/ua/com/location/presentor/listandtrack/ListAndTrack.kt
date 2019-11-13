package ua.com.location.presentor.listandtrack


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_and_track.*
import ua.com.location.R
import ua.com.location.data.LocatoinTrak
import javax.inject.Inject

class ListAndTrack : Fragment(), ListAndTrackView {

    @Inject
    lateinit var  listAndTrackPresentInterfas : ListAndTrackPresentInterfas
    val items = listAndTrackPresentInterfas.getListLocation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_and_track, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addButtnListeners()
        addRecyclerList()

    }



    fun addButtnListeners(){
        list_float_button.setOnClickListener{_ ->
            NavHostFragment.findNavController(this).navigate(R.id.map)
        }
    }

    fun addRecyclerList(){
        val recyclerAdapter =
            RecyclerAdapter(
                items = items,
                callback = object :
                    RecyclerAdapter.Callback {
                    override fun onItemClicked(item: LocatoinTrak) {

                    }
                })
        myRecycler.adapter = recyclerAdapter
        myRecycler.layoutManager = LinearLayoutManager(context)

    }


}
