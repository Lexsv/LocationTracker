package ua.com.location.presentation.listandtrack


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_and_track.*
import ua.com.location.MainActivity
import ua.com.location.R
import ua.com.location.di.listATrak.DaggerListAndTrackComponent
import ua.com.location.di.listATrak.ListAndTrakPrasentModul
import ua.com.location.models.listandtrakModel.IListAndTrak
import ua.com.location.models.listandtrakModel.ListAndTrakVM
import ua.com.location.repository.room.contant.Content
import ua.com.location.presentation.dialog.MyDialog
import javax.inject.Inject

class ListAndTrack : Fragment(), ListAndTrackView {

    val TAGMAP = "MAP"
    val TAGREG = "RAGISTER"
    val TAGCOL = "COLECTION"
    val TAGLOGIN = "LOGIN"

    @Inject
    lateinit var  listAndTrackPresentInterface : ListAndTrackPresentInterface


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MainActivity.MAINTAG = "COLECTION"
        addDaggerDepand()
        return inflater.inflate(R.layout.fragment_list_and_track, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listAndTrackPresentInterface.onStart()
        addButtnListeners()
    }



    fun addButtnListeners(){
        list_float_button.setOnClickListener{_ ->
            listAndTrackPresentInterface.onGoTo(TAGMAP)
        }

        toolbar_exit.setOnClickListener{_ ->
            listAndTrackPresentInterface.onExit()
        }
    }


    override fun showRecyclerList(list: List<Content>) {
        val recyclerAdapter =
            RecyclerAdapter(
                items = list,
                callback = object :
                    RecyclerAdapter.Callback {
                    override fun onItemClicked(item: Content) {
                        showDialig(item)
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


    override fun showDialig(item: Content) {
        MyDialog(item.latitude to item.longitude).setItem(item).show(childFragmentManager,"VIEWITEM")
    }

    override fun gotoFragment(key: String) {
        when(key) {
            TAGREG -> NavHostFragment.findNavController(this).navigate(R.id.register)
            TAGCOL -> NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
            TAGMAP -> NavHostFragment.findNavController(this).navigate(R.id.map)
            TAGLOGIN -> NavHostFragment.findNavController(this).navigate(R.id.login)

        }
    }

    override fun getVM(): IListAndTrak = ViewModelProviders.of(this).get(ListAndTrakVM::class.java)

    override fun rout(key: String) {
        when(key) {
            TAGREG -> NavHostFragment.findNavController(this).navigate(R.id.register)
            TAGCOL -> NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
            TAGMAP -> NavHostFragment.findNavController(this).navigate(R.id.map)

        }
    }

}
