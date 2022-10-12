package com.hee.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkAsyncTask().execute()

    }
}

// main 액티비티에서는 뷰만 그려줄 수 있기 때문에 따로 만들어줘야함
class NetworkAsyncTask() : AsyncTask<Any?, Any?, Any?>(){
    override fun doInBackground(vararg params: Any?): Any? {
        // 어디에 요청을 할건지
        val urlString : String = "http://mellowcode.org/json/students/"
        val url : URL = URL(urlString)
        val connection : HttpURLConnection = url.openConnection() as HttpURLConnection

        // 요청셋팅
        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json") // header

        var buffer = ""
        if(connection.responseCode == HttpURLConnection.HTTP_OK){
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream, // 컴퓨터만 알아들을 수 있는 응답코드
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("testt", buffer)
        }
        return null
    }
}