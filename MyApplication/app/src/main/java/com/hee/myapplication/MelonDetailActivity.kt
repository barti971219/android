package com.hee.myapplication

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

class MelonDetailActivity : AppCompatActivity() {

    lateinit var playPauseButton : ImageView
    lateinit var mediaPlayer : MediaPlayer

    lateinit var melonItemList: ArrayList<MelonItem>
    // 인덱스 setter 설정 => 0번부터 melonItemList의 길이까지만 이동할 수 있게
    var position = 0
        set(value){
            if(value <= 0) field = 0
            else if (value > melonItemList.size) field = melonItemList.size
            else field = value
        }

    // 변수와 뷰를 묶는 방법
    var is_playing: Boolean = true
        set(value){
            if(value == true){
                playPauseButton.setImageDrawable(
                    this.resources.getDrawable(R.drawable.pause, this.theme)
                )
            }else{
                playPauseButton.setImageDrawable(
                    this.resources.getDrawable(R.drawable.play, this.theme)
                )
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon_detail)

        // intent에서 값을 꺼내오고 상세페이지 들어오자마자 노래재생, 썸네일 보여줌
        melonItemList = intent.getSerializableExtra("melon_item_list") as ArrayList<MelonItem>
        position = intent.getIntExtra("position", 0)
        playMelonItem(melonItemList.get(position))
        changeThumbnail(melonItemList.get(position))

        // 멈춤/재생 버튼 눌렀을때
        playPauseButton = findViewById(R.id.play)
        playPauseButton.setOnClickListener{

            if(is_playing == true){
                is_playing = false
                mediaPlayer.stop()
            }
            else{
                is_playing = true
                playMelonItem(melonItemList.get(position))
            }
        }

        // 이전 버튼 눌렀을때
        findViewById<ImageView>(R.id.back).setOnClickListener{
            mediaPlayer.stop()
            position = position - 1
            playMelonItem(melonItemList.get(position))
            changeThumbnail(melonItemList.get(position))
        }

        // 다음 버튼 눌렀을때
        findViewById<ImageView>(R.id.next).setOnClickListener{
            mediaPlayer.stop()
            position = position + 1
            playMelonItem(melonItemList.get(position))
            changeThumbnail(melonItemList.get(position))
        }
    }

    // MediaPlayer 생성하고 시작하는 함수
    fun playMelonItem(melonItem : MelonItem){
        mediaPlayer = MediaPlayer.create(
            this,
            Uri.parse(melonItem.song)
        )
        mediaPlayer.start()
    }

    // 썸네일 넣어주는 함수
    fun changeThumbnail(melonItem: MelonItem){
        findViewById<ImageView>(R.id.thumbnail).apply{
            val glide = Glide.with(this@MelonDetailActivity)
            glide.load(melonItem.thumbnail).into(this)
        }
    }
}