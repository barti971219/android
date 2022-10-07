package com.hee.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.w3c.dom.Text

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        findViewById<TextView>(R.id.text).setOnClickListener{
            (it as TextView).text = resources.getText(R.string.app_name)
//            it.background = resources.getDrawable(R.drawable.exercise)

//            it.background = resources.getDrawable(R.drawable.exercise, null)
 //           it.background = ContextCompat.getDrawable(this, R.drawable.exercise)
  //          it.background = ResourcesCompat.getDrawable(resources, R.drawable.exercise, null)
        }
    }
}