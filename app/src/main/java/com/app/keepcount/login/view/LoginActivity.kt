package com.app.keepcount.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import android.media.MediaPlayer
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.app.keepcount.R
import com.app.keepcount.dashboard.DashboardActivity
import com.app.keepcount.models.User
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.database.DatabaseError


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    val databaseInstance = FirebaseDatabase.getInstance()
    val databaseReference=databaseInstance.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtAppName.setOnClickListener {
            //FirebaseAuth.getInstance().signOut()
        }

        playCoinSound()

        initView()
    }



   



    private fun initView() {
        supportActionBar?.hide()

        fabLogin.setOnClickListener(this)
        val animFadein = AnimationUtils.loadAnimation(
            getApplicationContext(),
            R.anim.floating_animation
        )

        //imgCoin.startAnimation(animFadein)
    }

    private fun playCoinSound() {
        val afd = assets.openFd("coins.mp3")
        val player = MediaPlayer()
        player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        player.prepare()
        player.start()
    }

    private fun signIn() {

        isUserExists(edtUserName.text.toString())


       /*val currentDate = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(Calendar.getInstance().time)
        val user = User(userName = edtUserName.text.toString(),loginStatus =  true,loggedDate =currentDate,latestAccountActivity = currentDate )
        databaseReference.child("users").child(user.id).setValue(user)
        Log.e("useId===",user.id)

        startActivity<DashboardActivity>()
        finish()*/
    }

    private fun isUserExists(userName:String) {
        val ref = databaseReference.child("users")
        val queryRef = ref.orderByChild("userName").equalTo(userName)
        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val shot=dataSnapshot.value
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fabLogin -> {
                signIn()
            }
        }
    }
}
