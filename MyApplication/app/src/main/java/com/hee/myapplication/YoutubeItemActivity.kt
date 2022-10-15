package com.hee.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class YoutubeItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_item)

        val videoUrl = intent.getStringExtra("video_url")

        // 영상을 재생시켜주는것
        val mediaController = MediaController(this)

        findViewById<VideoView>(R.id.youtube_video_view).apply{
            this.setVideoPath(videoUrl)
            this.requestFocus()
            this.start()
            mediaController.show()
        }
    }

    // Exoplayer 외부 라이브러리
    // - 기능이 다양함
    // - 사용이 쉽다
    // - DRM
    // - Digital Right Management
    // - Streaming
}