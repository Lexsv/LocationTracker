package ua.com.location.presentation.listandtrack


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_and_track.*
import ua.com.location.R
import ua.com.location.data.LocatoinTrak
import ua.com.location.di.listATrak.DaggerListAndTrackComponent
import ua.com.location.di.listATrak.ListAndTrakPrasentModul
import javax.inject.Inject

class ListAndTrack : Fragment(), ListAndTrackView {



    @Inject
    lateinit var  listAndTrackPresentInterfas : ListAndTrackPresentInterfas


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addDaggerDepand()
        return inflater.inflate(R.layout.fragment_list_and_track, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listAndTrackPresentInterfas.onStart()
        addButtnListeners()
    }



    fun addButtnListeners(){
        list_float_button.setOnClickListener{_ ->
            NavHostFragment.findNavController(this).navigate(R.id.map)
        }
    }


    override fun showRecyclerList(list: List<LocatoinTrak>) {
        val recyclerAdapter =
            RecyclerAdapter(
                items = list,
                callback = object :
                    RecyclerAdapter.Callback {
                    override fun onItemClicked(item: LocatoinTrak) {

                    }
                })
        myRecycler.adapter = recyclerAdapter
        myRecycler.layoutManager = LinearLayoutManager(context)
    }
    private fun addDaggerDepand(){
        DaggerListAndTrackComponent.builder()
            .listAndTrakPrasentModul(ListAndTrakPrasentModul(this))
            .build()
            .inject(this)
    }

    override fun getLifecycleOwner(): LifecycleOwner = this


}
