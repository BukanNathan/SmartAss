package com.example.converterapp2

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

const val ACTION_PLAY = "PLAY"
const val ACTION_STOP = "STOP"
const val ACTION_CREATE = "CREATE"
const val ACTION_PAUSE = "PAUSE"

class MyMPService : Service(),
MediaPlayer.OnPreparedListener,
MediaPlayer.OnCompletionListener,
MediaPlayer.OnErrorListener {

    private var myMediaPlayer : MediaPlayer? = null
    private var length = 0
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            var actionIntent = intent.action
            when(actionIntent) {
                ACTION_CREATE -> {
                    myMediaPlayer = MediaPlayer()
                    myMediaPlayer?.setOnPreparedListener(this)
                    myMediaPlayer?.setOnCompletionListener(this)
                    myMediaPlayer?.setOnErrorListener(this)
                }
                ACTION_PLAY -> {
                    if(!myMediaPlayer!!.isPlaying) {
                        val assetFileDescriptor = this.resources.openRawResourceFd(R.raw.chingchenghanji)
                        myMediaPlayer?.run {
                            reset()
                            setDataSource(
                                    assetFileDescriptor.fileDescriptor,
                                    assetFileDescriptor.startOffset,
                                    assetFileDescriptor.declaredLength
                            )
                            prepareAsync()
                        }
                    }
                }
                ACTION_STOP -> {
                    myMediaPlayer?.stop()
                    length = 0
                }
                ACTION_PAUSE -> {
                    myMediaPlayer?.pause()
                    length = myMediaPlayer!!.currentPosition
                }
            }
        }
        return flags
    }

    override fun onPrepared(mp: MediaPlayer?) {
        if (length > 0) {
            myMediaPlayer?.seekTo(length)
            myMediaPlayer?.start()
        }
        else myMediaPlayer?.start()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        Toast.makeText(this, "Audio File Selesai Dibaca", Toast.LENGTH_SHORT).show()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        Toast.makeText(this, "Error Membaca Audio File", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        myMediaPlayer?.release()
    }
}