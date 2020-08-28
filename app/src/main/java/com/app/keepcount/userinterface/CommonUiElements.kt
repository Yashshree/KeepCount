package com.app.keepcount.userinterface

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.app.keepcount.R
import com.app.keepcount.listeners.OnDialogItemClickListener
import kotlinx.android.synthetic.main.layout_role.view.*

fun Context.showRoleSelectionDialog(layout:Int, dialogClickListener:OnDialogItemClickListener)
{
   val builder= AlertDialog.Builder(this)
    builder.setView(layout)
    builder.setCancelable(true)

val array = Array<Int>(3){R.id.imgHomeMaker}


builder.setSingleChoiceItems(,-1,object :DialogInterface.OnClickListener{
    override fun onClick(dialog: DialogInterface?, which: Int) {

    }

})
}