package com.app.keepcount.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.keepcount.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)


    }
}
