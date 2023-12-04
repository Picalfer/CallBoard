package com.landfathich.callboard.dialoghelper

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.callboard.R
import com.landfathich.callboard.utils.CityHelper

class SpinnerDialogHelper {

    fun showSpinnerDialog(context: Context, list: ArrayList<String>, selectedTextView: TextView) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder.create()
        val dialogView = LayoutInflater.from(context).inflate(R.layout.spinner_dialog, null)

        val adapter = SpinnerDialogAdapter(dialog, selectedTextView)
        val rcView = dialogView.findViewById<RecyclerView>(R.id.rv_spinner)
        val sv = dialogView.findViewById<SearchView>(R.id.sv_spinner)

        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter
        adapter.updateAdapter(list)
        setSearchViewListener(adapter, list, sv)

        dialog.setView(dialogView)
        dialog.show()
    }

    private fun setSearchViewListener(
        adapter: SpinnerDialogAdapter,
        list: ArrayList<String>,
        sv: SearchView
    ) {
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val tempList = CityHelper.filterListData(list, newText)
                adapter.updateAdapter(tempList)
                return true
            }

        })
    }
}