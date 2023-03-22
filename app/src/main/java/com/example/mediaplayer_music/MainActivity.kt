package com.example.mediaplayer_music

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    var totalTime : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            mediaPlayer = MediaPlayer.create(this,R.raw.sm)
        mediaPlayer.setVolume(1f,1f)
        totalTime = mediaPlayer.duration
        val playButton = findViewById<ImageView>(R.id.Play)
        val pauseButton = findViewById<ImageView>(R.id.Pause)
        val stopButton = findViewById<ImageView>(R.id.stop)
        val seekBarButton = findViewById<SeekBar>(R.id.Seekbar)

        playButton.setOnClickListener {
            mediaPlayer.start()
        }
        pauseButton.setOnClickListener {
            mediaPlayer.pause()
        }
        stopButton.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }
        // when user changes the time stamp of music ,reflect music
        seekBarButton.max = totalTime
        seekBarButton.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser){
                mediaPlayer.seekTo(progress)
            }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        //change the seekbar position based on the music
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                try {
                    seekBarButton.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (exception:java.lang.Exception){
                    seekBarButton.progress = 0
                }
            }
        },0)

    }
}
