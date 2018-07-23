package com.kotlin.amritakumari.activityanimation

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageview.setOnClickListener( {
            val intent = Intent(this, DetailActivity :: class.java)
            val options = ActivityOptions
                    .makeSceneTransitionAnimation(this, imageview, "background")
            startActivity(intent, options.toBundle())
        })
    }
}
