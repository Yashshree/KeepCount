package com.app.keepcount.login.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.keepcount.models.User
import com.app.keepcount.utility.*
import com.app.keepcount.utility.SharedPreferenceUtil.Companion.SharedPreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class LoginViewModel : ViewModel() {
    val databaseReference = SingletonInstances.databaseReference.child("users")
    fun isUserExists(userName: String): MutableLiveData<Resource<Boolean>> {
        val data = MutableLiveData<Resource<Boolean>>()

        data.postValue(Resource(Status.LOADING, null, null))

        val queryRef = databaseReference.orderByChild("userName").equalTo(userName).limitToFirst(1)
        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                data.postValue(Resource(Status.ERROR, false, null))
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val user=it.getValue(User::class.java)
                    user.toString()
                    SharedPreferenceUtil.saveUser(Gson().toJson(user,User::class.java))
                }

                if (dataSnapshot.value == null)
                    data.postValue(Resource(Status.SUCCESS, true, null))
                else
                    data.postValue(Resource(Status.SUCCESS, false, null))
            }
        })

        return data
    }

    fun logout(userId: String)
    {

    }

    fun login(user:User):MutableLiveData<Resource<Boolean>>
    {
        val data = MutableLiveData<Resource<Boolean>>()

        val currentDate = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(Calendar.getInstance().time)
      //  val user = User(userName = user.userName,loginStatus =  user.loginStatus,loggedDate =currentDate,latestAccountActivity = currentDate )
        databaseReference.child("users").child(user.id).setValue(user)

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.value == null)
                    data.postValue(Resource(Status.SUCCESS, true, null))
                else
                    data.postValue(Resource(Status.SUCCESS, false, null))
            }
        })

        return data
    }

}