package ua.com.location.presentor.listandtrack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.com.location.R
import ua.com.location.data.LocatoinTrak

class RecyclerAdapter(var items: List<LocatoinTrak>, val callback: Callback) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.exampl_view_recycl, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) =  holder.bind(items[position])

    inner class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.recycler_title)
        private val descript = itemView.findViewById<TextView>(R.id.recycler_Text)


        fun bind(item: LocatoinTrak) {
            title.text = item.title
            descript.text = item.descript
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }


    }

    interface Callback {
        fun onItemClicked(item: LocatoinTrak)
    }
}