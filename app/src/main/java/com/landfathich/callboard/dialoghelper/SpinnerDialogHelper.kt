package com.landfathich.callboard.dialoghelper

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.landfathich.callboard.R

class SpinnerDialogHelper {

    fun showSpinnerDialog(context: Context, list: ArrayList<String>) {
        val builder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.spinner_dialog, null)

        val adapter = SpinnerDialogAdapter()
        val rcView = dialogView.findViewById<RecyclerView>(R.id.rv_spinner)
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter
        adapter.updateAdapter(list)

        builder.setView(dialogView)
        builder.show()
    }
}