package com.app.keepcount.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.app.keepcount.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import android.R.attr.data
import android.media.MediaPlayer
import android.view.View
import android.view.animation.AnimationUtils
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    val RC_SIGN_IN = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*   val androidZoomOutAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in_animation)
           txtAppName.startAnimation(androidZoomOutAnimation)*/

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
        //signIn()

        txtAppName.setOnClickListener {
            //FirebaseAuth.getInstance().signOut()
            googleSignInClient.signOut()
                .addOnCompleteListener(this, OnCompleteListener<Void> {
                    Toast.makeText(this@LoginActivity, "logout Sucessfully", Toast.LENGTH_SHORT).show()
                })
        }

        playCoinSound()

      /*  val anim = RotateAnimation(0f, 350f, 15f, 15f)
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.INFINITE
        anim.duration = 700

        imgCoin.startAnimation(anim)*/
    }

    private fun playCoinSound() {
        val afd = getAssets().openFd("coins.mp3");
        val player = MediaPlayer()
        player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength())
        player.prepare()
        player.start()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /*// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("tag", "Google sign in failed", e)
                // ...
            }
        }*/

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode === RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)



            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            firebaseAuthWithGoogle(account!!)
            // Signed in successfully, show authenticated UI.
            // updateUI(account)
            Toast.makeText(this@LoginActivity, "Login Sucessfully", Toast.LENGTH_SHORT).show()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this@LoginActivity, "Login Error " + e.message, Toast.LENGTH_SHORT).show()
            println("## " + "Login Error " + e.message)
        }

    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //   val currentUser = auth.currentUser
        val account = GoogleSignIn.getLastSignedInAccount(this)
        Toast.makeText(this, if (account != null) account.email else "new user", Toast.LENGTH_SHORT).show()
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("tag", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("tag", "signInWithCredential:success")
                    val currentUser = auth.currentUser
                    Toast.makeText(this, currentUser.toString(), Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("tag", "signInWithCredential:failure", task.exception)
                    Snackbar.make(rootLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //  Toast.makeText(this,currentUser.toString(),Toast.LENGTH_SHORT).show()
                }
            }
    }
}
