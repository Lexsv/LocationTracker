package ua.com.location.presentation.dialog

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
import ua.com.location.data.LocatoinTrak
import ua.com.location.di.dialogFragment.DaggerMyDialogComponent
import ua.com.location.di.dialogFragment.MyDialogPresenterIntefasModul
import ua.com.location.models.DistributorDataVM
import ua.com.location.models.IDistributorData
import ua.com.location.models.repository.room.contant.Content
import javax.inject.Inject

class MyDialog (var pairLocation: Pair<Double,Double>): DialogFragment(),MyDialogView {

    @Inject
    lateinit var myDialogPresentIntefas: MyDialogPresentIntefas
     private lateinit var item: Content

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
            myDialogPresentIntefas.onSaveDiscription(title = dialog_fragment_title.text.toString(),
                                                     discription = dialog_fragment_description.text.toString(),
                                                     pair = pairLocation)
            NavHostFragment.findNavController(this).navigate(R.id.listAndTrack)
            dismiss()
        }

        dialog_fragment_bt_cancel.setOnClickListener{
            dismiss()
        }
    }


    override fun getLifecycleOwner(): LifecycleOwner = this
    override fun showAddDialog() {

    }
    override fun getVM(): IDistributorData = ViewModelProviders.of(this).get(DistributorDataVM::class.java)


    override fun showDascritionDialog() {
        dialog_fragment_description.setText(item.descript)
        dialog_fragment_description.isEnabled = false
        dialog_fragment_title.setText (item.title)
        dialog_fragment_title.isEnabled = false
        dialog_fragment_bt_ok.visibility = View.GONE

    }

}