package com.example.juchi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val taskLengthSeconds =5
        val progressBar: ProgressBar = findViewById(R.id.progressBar) as ProgressBar
        val increment = 100/ taskLengthSeconds
        object: CountDownTimer(taskLengthSeconds * 1000L, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val secondsREmaining = millisUntilFinished/1000

                val progress = (taskLengthSeconds - secondsREmaining) * increment
                progressBar.progress = progress.toInt()

            }

            override fun onFinish() {
                progressBar.visibility = View.INVISIBLE
                val intent = Intent(this@MainActivity, FrmAcceso::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}