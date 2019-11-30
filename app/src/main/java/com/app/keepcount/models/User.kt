package com.app.keepcount.models

import java.util.*

class User(var id:String=UUID.randomUUID().toString(), var userName:String,  var loggedDate:String,
           var loginStatus:Boolean,var latestAccountActivity:String)