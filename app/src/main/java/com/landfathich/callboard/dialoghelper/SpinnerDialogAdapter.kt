package com.landfathich.callboard.dialoghelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.callboard.R

class SpinnerDialogAdapter : RecyclerView.Adapter<SpinnerDialogAdapter.SpinnerViewHolder>() {
    private val mainList = ArrayList<String>()
    class SpinnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(text: String) {
            val tvSpinnerItem: TextView = itemView.findViewById<TextView>(R.id.tv_spinner_item)
            tvSpinnerItem.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpinnerDialogAdapter.SpinnerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.spinner_list_item, parent, false)
        return SpinnerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun onBindViewHolder(holder: SpinnerDialogAdapter.SpinnerViewHolder, position: Int) {
        holder.setData(mainList[position])
    }

    fun updateAdapter(list: ArrayList<String>) {
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }
}