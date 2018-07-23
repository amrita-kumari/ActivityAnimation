package com.kotlin.amritakumari.activityanimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.kotlin.amritakumari.activityanimation.R.id.toolbar

class DetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        toolbar.inflateMenu(R.menu.detail_menu)
        return super.onCreateOptionsMenu(menu)
    }
}