package com.hee.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        val imageView = findViewById<ImageView>(R.id.image)

        Glide
            .with(this)
            .load("https://w.namu.la/s/5172a67e3702bf33b32f512c1cdf0cd6308eaf75937ff6ff2ae4ed338c8ee2fdfe2fc8ac13127fc0a70de4aa3bc83660fc9368fdf89a7d84c66625f8f76ba45a5fe878e3a67ec1de565e2b6cb970d6fedc61c1ecf3cac491aa15275b68aec249")
            .centerCrop()
            .into(imageView)
    }
}