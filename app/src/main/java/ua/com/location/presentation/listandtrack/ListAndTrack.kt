package ua.com.location.presentation.listandtrack


import android.graphics.*
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list_and_track.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ua.com.location.MainActivity
import ua.com.location.R
import ua.com.location.di.listATrak.DaggerListAndTrackComponent
import ua.com.location.di.listATrak.ListAndTrakPrasentModul
import ua.com.location.models.listandtrakModel.IListAndTrak
import ua.com.location.models.listandtrakModel.ListAndTrakVM
import ua.com.location.repository.room.content.Content
import ua.com.location.presentation.dialog.MyDialog
import ua.com.location.repository.data.LocalStoreVW
import ua.com.location.util.myLocation
import javax.inject.Inject

class ListAndTrack : Fragment(), ListAndTrackView {

    val TAGMAP = "MAP"
    val TAGREG = "RAGISTER"
    val TAGCOL = "COLECTION"
    val TAGLOGIN = "LOGIN"
    private val p = Paint()

    @Inject
    lateinit var listAndTrackPresentInterface: ListAndTrackPresentInterface


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


    private fun addDaggerDepand() {
        DaggerListAndTrackComponent.builder()
            .listAndTrakPrasentModul(ListAndTrakPrasentModul(this))
            .build()
            .inject(this)
    }

    fun addButtnListeners() {
        list_float_button.setOnClickListener { _ ->
            listAndTrackPresentInterface.onGoTo(TAGMAP)
        }


        toolbar_exit.setOnClickListener { _ ->
            listAndTrackPresentInterface.onExit()
        }
    }


    override fun actionMassege(key: String) {
        Toast.makeText(context, key, Toast.LENGTH_LONG).show()
    }

    override fun showRecyclerList(list: List<Content>) {
        val recyclerAdapter =
            RecyclerAdapter(
                items = list,
                callback = object :
                    RecyclerAdapter.Callback {
                    override fun onItemClicked(item: Content) {
                        showDialig(item)
                        listAndTrackPresentInterface.nowUpdat(item)
                    }
                })
        myRecycler.adapter = recyclerAdapter
        myRecycler.layoutManager = LinearLayoutManager(context)

        val simpleItemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    listAndTrackPresentInterface.remove(position)
                }
                if (direction == ItemTouchHelper.RIGHT) {
                    listAndTrackPresentInterface.creatPath(position)
                }
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {

                val icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height /3

                    if (dX > 0) {
                        p.color = Color.parseColor("#c9c94b")
                        val background =
                            RectF(
                                itemView.left.toFloat(),
                                itemView.top.toFloat(),
                                dX,
                                itemView.bottom.toFloat()
                            )
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.sent)
                        val icon_dest = RectF(
                            itemView.left.toFloat() + width,
                            itemView.top.toFloat() + width,
                            itemView.left.toFloat() + 2 * width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, icon_dest, p)
                    } else {
                        p.color = Color.parseColor("#D32F2F")
                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(resources, R.drawable.delete)
                        val icon_dest = RectF(
                            itemView.right.toFloat() - 2 * width,
                            itemView.top.toFloat() + width,
                            itemView.right.toFloat() - width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, icon_dest, p)
                    }
                }
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(myRecycler)


    }


    override fun getLifecycleOwner(): LifecycleOwner = this


    override fun showDialig(item: Content) {
        MyDialog(item.latitude to item.longitude).setItem(item)
            .show(childFragmentManager, "VIEWITEM")
    }

    override fun gotoFragment(key: String) {
        when (key) {
            TAGREG -> NavHostFragment.findNavController(this).navigate(R.id.register)
            TAGCOL -> NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
            TAGMAP -> NavHostFragment.findNavController(this).navigate(R.id.map)
            TAGLOGIN -> NavHostFragment.findNavController(this).navigate(R.id.login)

        }
    }

    override fun getVM(): IListAndTrak = ViewModelProviders.of(this).get(ListAndTrakVM::class.java)

    override fun rout(key: String) {
        when (key) {
            TAGREG -> NavHostFragment.findNavController(this).navigate(R.id.register)
            TAGCOL -> NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
            TAGMAP -> NavHostFragment.findNavController(this).navigate(R.id.map)

        }
    }

    override fun welcome(key: String) {
        toolbar_welcome.setText("Привет $key")
    }


    override fun showRestart() {
        Snackbar.make(requireView(),"Отменить удаление", Snackbar.LENGTH_LONG)
            .setAction("ok"){
                listAndTrackPresentInterface.onReestablishItom()
            }
            .setActionTextColor(Color.YELLOW)
            .show()

    }

}
