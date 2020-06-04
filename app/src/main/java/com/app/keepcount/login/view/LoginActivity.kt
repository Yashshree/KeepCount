package com.app.keepcount.login.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.keepcount.R
import com.app.keepcount.dashboard.DashboardActivity
import com.app.keepcount.utility.Status
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

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

     //   isUserExists(edtUserName.text.toString())


        /*val currentDate = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(Calendar.getInstance().time)
         val user = User(userName = edtUserName.text.toString(),loginStatus =  true,loggedDate =currentDate,latestAccountActivity = currentDate )
         databaseReference.child("users").child(user.id).setValue(user)
         Log.e("useId===",user.id)

         startActivity<DashboardActivity>()
         finish()*/

        loginViewModel.isUserExists(edtUserName.text.toString()).observe(this, Observer {
            if(it.status==Status.LOADING)
            {
                toast("loading")
            }else if(it.status==Status.ERROR)
            {
                toast("error")
            }else if(it.status==Status.SUCCESS)
            {
                if(it.data!!)
                    toast("please enter another user name!!")
                else
                    startActivity<DashboardActivity>()
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
