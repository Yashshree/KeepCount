package com.app.keepcount

import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       /* alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
            yesButton { toast("Oh…") }
            noButton {startActivity<secondActivity>()}
        }.show()*/


        val builder=android.app.AlertDialog.Builder(this)

        builder.setMessage("yes")

        builder.setPositiveButton("yes",DialogInterface.OnClickListener { dialog, which ->
            startActivity<secondActivity>()
        }).show()

    }
}
