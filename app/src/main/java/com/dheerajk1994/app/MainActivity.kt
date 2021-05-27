package com.dheerajk1994.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dheerajk1994.pine.Pine
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Pine.setLogLevel(Pine.LogLevel.INFO)

        Pine.i("hello")
        Pine.w("test warning")
        Pine.d("test debug")
        Pine.v("test verbose")
        Pine.e("test error")

        btn_test_button.setOnClickListener {
            Pine.logStep("pressed ", btn_test_button)
        }

        testFunction()
    }

    private fun testFunction() {
        Pine.d("test")
    }
}