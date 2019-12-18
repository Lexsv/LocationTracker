package ua.com.location.presentation.dialog

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.dialog_fragment_description.*
import ua.com.location.R
import ua.com.location.di.dialogFragment.DaggerMyDialogComponent
import ua.com.location.di.dialogFragment.MyDialogPresenterIntefasModul
import ua.com.location.models.dialogModel.DialogVM
import ua.com.location.models.dialogModel.IDialogVM
import ua.com.location.repository.room.content.Content
import javax.inject.Inject

class MyDialog (var pairLocation: Pair<Double,Double>): DialogFragment(),MyDialogView {

    @Inject
    lateinit var myDialogPresentIntefas: MyDialogPresentIntefas
     private lateinit var item: Content
    private var isEdit: Boolean = false

    fun setItem(item: Content)= apply {this.item = item  }


    override fun onCreateView(inflater: LayoutInflater,  container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addDaggerDepand()
        return inflater.inflate(R.layout.dialog_fragment_description, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addClickLisan()
        myDialogPresentIntefas.onStart(tag!!)

    }

    private fun addDaggerDepand() {
        DaggerMyDialogComponent.builder()
            .myDialogPresenterIntefasModul(MyDialogPresenterIntefasModul(this))
            .build()
            .inject(this)
    }


    fun addClickLisan(){
        dialog_fragment_bt_ok.setOnClickListener{_->
           when(tag){
               "MAP" ->{
                   myDialogPresentIntefas.onSaveDiscription(title = dialog_fragment_title.text.toString(),
                       discription = dialog_fragment_description.text.toString(),
                       pair = pairLocation)
                   NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
                   dismiss()
               }
               "VIEWITEM" ->{
                   if (isEdit){
                       myDialogPresentIntefas.upDataItom(title = dialog_fragment_title.text.toString(),
                           discription = dialog_fragment_description.text.toString())

                   }else{
                       myDialogPresentIntefas.onEdit()
                   }
               }
           }
        }

        dialog_fragment_bt_cancel.setOnClickListener{
            when(tag){
                "MAP" -> dismiss()
                "VIEWITEM" ->{
                    NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
                    dismiss()
                }

            }

        }
    }


    override fun getLifecycleOwner(): LifecycleOwner = this
    override fun showAddDialog() {


    }
    override fun getVM(): IDialogVM = ViewModelProviders.of(this).get(DialogVM::class.java)


    override fun showDascritionDialog() {
        dialog_fragment_description.setBackgroundResource(R.drawable.bg_edit)
        dialog_fragment_title.setBackgroundResource(R.drawable.bg_edit)
        dialog_fragment_description.setText(item.descript)
        dialog_fragment_description.isEnabled = false
        dialog_fragment_title.setText (item.title)
        dialog_fragment_title.isEnabled = false
        dialog_fragment_bt_ok.setImageResource(R.drawable.create_black_24dp)

    }

    override fun editContant(){
        dialog_fragment_description.setBackgroundResource(R.drawable.bg_racycle_view)
        dialog_fragment_title.setBackgroundResource(R.drawable.bg_racycle_view)
        isEdit = true
        dialog_fragment_bt_ok.setImageResource(R.drawable.save_black_24dp)
        dialog_fragment_description.isEnabled = true
        dialog_fragment_title.isEnabled = true
    }

    override fun savedContant(){
        dialog_fragment_description.setBackgroundResource(R.drawable.bg_edit)
        dialog_fragment_title.setBackgroundResource(R.drawable.bg_edit)
        isEdit = false
        dialog_fragment_bt_ok.setImageResource(R.drawable.create_black_24dp)
        dialog_fragment_description.isEnabled = false
        dialog_fragment_title.isEnabled = false
    }

}