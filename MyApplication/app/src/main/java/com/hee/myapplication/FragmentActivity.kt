package com.hee.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragmentManager = supportFragmentManager
        val fragmentFirst = FragmentFirst()

        // Transaction
        // - 작업의 단위 -> 시작과 끝이 있다
        // A,B,C,D
        // Commit
        // 1> commit
        // 2> commitAllowingStateLoss
        // 3> commitNow
        // 4> commitNowAllowingStateLoss
        // commit - commitAllowingStateLoss
        // - 상태손실을 허락한다
        // - onStop, lifecycle 또는 os에 의해서 앱이 상태를 저장해야 할 수 있다
        // - 상태저장 : onSaveInstanceState
        // - commit : 저장을 한 경우 실행 할 수 없다 (IllegalStateException)
        // - commitAllowingStateLoss : 저장을 한 경우 예외가 발생하지 않고 그냥 손실
        // commit - commitNow(Now)
        // - commit -> 작업을 예약한다 (메인 쓰레드에 예약이 된다)
        // - commitNow -> 바로 실행해!
        // --------------
        //            123456789

        (findViewById<TextView>(R.id.add)).setOnClickListener{
            val transaction = fragmentManager.beginTransaction() // 시작
            // 프래그먼트에 데이터를 전달 하는 방법
            val bundle = Bundle()
            bundle.putString("key", "hello")
            fragmentFirst.arguments = bundle

            transaction.replace(R.id.root, fragmentFirst, "fragment_first_tag")
            transaction.commit() // 끝

        }

        (findViewById<TextView>(R.id.remove)).setOnClickListener{
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(fragmentFirst)
            transaction.commit()
        }

        (findViewById<TextView>(R.id.access_fragment)).setOnClickListener{
            // XML에 있는 fragment를 찾는 방법
            //val fragmentFirst =
            //    supportFragmentManager.findFragmentById(R.id.fragment_first) as FragmentFirst

            //fragmentFirst.printTestLog()
            // XML에 없는 fragment를 찾는 방법
            val fragmentFirst = supportFragmentManager.findFragmentByTag("fragment_first_tag") as FragmentFirst
            fragmentFirst.printTestLog()
        }


    }

    fun printTestLog(){
        Log.d("testt", "print test log")
    }
}