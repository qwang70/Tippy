package com.stanford.edu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.tip_info_list.view.*

class TipInfoAdapter(internal val tipInfoList: ArrayList<TipInfo>) :
    RecyclerView.Adapter<TipInfoAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class MyViewHolder(val view: RelativeLayout) :
        RecyclerView.ViewHolder(view)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TipInfoAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tip_info_list, parent, false) as RelativeLayout
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val tipInfo = tipInfoList.get(position)
        holder.view.tvDate.text = tipInfo.date.time.toString()
        holder.view.tvDescription.text = "Paid ${tipInfo.total} ${tipInfo.currency} for a ${tipInfo.base} ${tipInfo.currency} meal with a ${tipInfo.tipPercent}% tip."
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = tipInfoList.size
}