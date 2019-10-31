package com.app.keepcount.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.media.MediaPlayer
import android.view.View
import com.app.keepcount.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtAppName.setOnClickListener {
            //FirebaseAuth.getInstance().signOut()

        }

        playCoinSound()
    }

    private fun playCoinSound() {
        val afd = assets.openFd("coins.mp3")
        val player = MediaPlayer()
        player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        player.prepare()
        player.start()
    }

    private fun signIn() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }
}
