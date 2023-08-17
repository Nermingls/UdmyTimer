package com.example.udmytimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.udmytimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     var runnable : Runnable = Runnable{}
     var handler :Handler = Handler(Looper.getMainLooper())
     var number = 0

    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
    fun geriSay(view: View){
        object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                binding.left.text = "Left : ${p0/1000}"
            }
            override fun onFinish() {
                binding.left.text = "Left : 0"
            }
        }.start()
    }

    fun start(view: View){
        runnable = object  : Runnable{
            override fun run() {
                number +=1
                binding.time.text = "Time : ${number}"
                handler.postDelayed(runnable,1000)
            }
        }
        handler.post(runnable)
        binding.startButton.isEnabled=false
    }
    fun stop(view: View){
        handler.removeCallbacks(runnable)
        number = 0
        binding.startButton.isEnabled=true
        binding.time.text = "Time : 0"

    }
}