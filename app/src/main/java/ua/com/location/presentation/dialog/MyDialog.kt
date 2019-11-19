package ua.com.location.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.dialog_fragment_description.*
import ua.com.location.R
import ua.com.location.di.dialogFragment.DaggerMyDialogComponent
import ua.com.location.di.dialogFragment.MyDialogPresenterIntefasModul
import javax.inject.Inject

class MyDialog (var pairLocation: Pair<Double,Double>): DialogFragment(),MyDialogView {

    @Inject
    lateinit var myDialogPresentIntefas: MyDialogPresentIntefas


    override fun onCreateView(inflater: LayoutInflater,  container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addDaggerDepand()
        return inflater.inflate(R.layout.dialog_fragment_description, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addClickLisan()


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
            dismiss()
        }

        dialog_fragment_bt_cancel.setOnClickListener{
            dismiss()
        }
    }


    override fun getLifecycleOwner(): LifecycleOwner = this
}