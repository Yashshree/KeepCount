package com.app.keepcount.utility

import android.preference.PreferenceManager
import com.google.firebase.database.FirebaseDatabase

object SingletonInstances {
        val databaseReference=FirebaseDatabase.getInstance().reference

}