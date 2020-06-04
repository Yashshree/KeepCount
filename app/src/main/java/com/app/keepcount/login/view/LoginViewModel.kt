package com.app.keepcount.login.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.keepcount.models.User
import com.app.keepcount.utility.*
import com.app.keepcount.utility.SharedPreferenceUtil.Companion.SharedPreference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginViewModel : ViewModel() {

    fun isUserExists(userName: String): MutableLiveData<Resource<Boolean>> {
        val data = MutableLiveData<Resource<Boolean>>()

        data.postValue(Resource(Status.LOADING, null, null))
        val ref = SingletonInstances.databaseReference.child("users")
        val queryRef = ref.orderByChild("userName").equalTo(userName).limitToFirst(1)
        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                data.postValue(Resource(Status.ERROR, false, null))
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {

                    var res=it.getValue(User::class.java)
                    res.toString()
                    //SharedPreferenceUtil.saveUser()
                }
                if (dataSnapshot.value == null)
                    data.postValue(Resource(Status.SUCCESS, true, null))
                else
                    data.postValue(Resource(Status.SUCCESS, false, null))
            }
        })

        return data
    }

}