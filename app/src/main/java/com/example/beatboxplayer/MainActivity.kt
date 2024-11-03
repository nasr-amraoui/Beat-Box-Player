package com.example.beatboxplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mediaPlayer_One = MediaPlayer.create(this, R.raw.elgrandtoto_mghayar)
        val mediaPlayer_Two = MediaPlayer.create(this, R.raw.stormy_maradona)
        val mediaPlayer_Tree = MediaPlayer.create(this, R.raw.stormy_ft_elgrandtoto_maradona_remix)

        val seek_bar = findViewById<SeekBar>(R.id.seek_bar)
        val play_btn = findViewById<ImageButton>(R.id.play_btn)

        seek_bar.progress = 0
        seek_bar.max = mediaPlayer_One.duration

        seek_bar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer_One.seekTo(position)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            seek_bar.progress = mediaPlayer_One.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer_One.setOnCompletionListener {
            play_btn.setImageResource(R.drawable.play)
            seek_bar.progress = 0
        }

        play_btn.setOnClickListener {
            if (!mediaPlayer_One.isPlaying) {
                mediaPlayer_One.start()
                play_btn.setImageResource(R.drawable.pause)
            } else {
                mediaPlayer_One.pause()
                play_btn.setImageResource(R.drawable.play)
            }
        }
    }
}