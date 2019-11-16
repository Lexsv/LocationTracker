package ua.com.location.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_description.*
import ua.com.location.R

class MyDialog : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater,  container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.dialog_fragment_description, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog_fragment_bt_ok.setOnClickListener{_->
            dismiss()
        }

        dialog_fragment_bt_cancel.setOnClickListener{
            dismiss()
        }


    }
}