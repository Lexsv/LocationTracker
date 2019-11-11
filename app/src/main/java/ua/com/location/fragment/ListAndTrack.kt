package ua.com.location.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_list_and_track.*
import ua.com.location.R
import ua.com.location.data.LocatoinTrak

class ListAndTrack : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_and_track, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = listOf(
            LocatoinTrak("titl_1","mnmnmmmvsda","2334.777"),
            LocatoinTrak("titl_2","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_3","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_4","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_5","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_6","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_7","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_8","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_29","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_2","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_2","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_2","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"),
            LocatoinTrak("titl_3","hgjhsdjfasjdjhsadvvhjsdsv","2334.777"))

        val recyclerAdapter = RecyclerAdapter(items = items,callback = object :
            RecyclerAdapter.Callback {
            override fun onItemClicked(item: LocatoinTrak) {

            }
        })
        myRecycler.adapter = recyclerAdapter
        myRecycler.layoutManager = LinearLayoutManager(context)

        list_float_button.setOnClickListener{_ ->
            NavHostFragment.findNavController(this).navigate(R.id.map)
        }
    }


}
