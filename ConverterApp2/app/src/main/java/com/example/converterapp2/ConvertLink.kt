package com.example.converterapp2

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_convert_link.*

private var sp : SoundPool? = null
private var soundID = 0

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class ConvertLink : AppCompatActivity(), View.OnClickListener {
    var jobSchedulerId = 10
    private val PrefFileName = "MYFILEPREF01"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_link)

        convertLink.setOnClickListener{
            startConvert()
        }
        cancelLink.setOnClickListener {
            stopConvert()
        }

        button4.setOnClickListener {
            if(soundID != 0){
                sp?.play(soundID,0.99f,.99f,1,0, .99f)
            }
        }

        save.setOnClickListener(this)
        delete.setOnClickListener(this)
        read.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var mySharedPrefHelper = SharedPrefHelper (this, PrefFileName)
        when(v?.id) {
            R.id.save -> {
                mySharedPrefHelper.link = linkEdit.text.toString()
                Toast.makeText(this, "Link Tersimpan", Toast.LENGTH_SHORT).show()
                clearEditText()
            }
            R.id.delete -> {
                mySharedPrefHelper.clearValue()
                clearEditText()
            }
            R.id.read -> linkEdit.setText(mySharedPrefHelper.link)
        }
    }
    fun clearEditText() {
        linkEdit.text.clear()
    }

    override fun onStart() {
        super.onStart()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            CreateNewSoundPool()
        }
        else{
            CreateOldSoundPool()
        }
        sp?.setOnLoadCompleteListener{soundPool, id, status ->
            if(status != 0){
                Toast.makeText(this,"Gagal Load",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Berhasil Load",Toast.LENGTH_SHORT).show()
            }
        }
        soundID = sp?.load(this, R.raw.nggyu, 1) ?: 0
    }

    private fun CreateOldSoundPool() {
        sp = SoundPool(15,AudioManager.STREAM_MUSIC,0)
    }

    private fun CreateNewSoundPool() {
        sp = SoundPool.Builder()
                .setMaxStreams(15)
                .build()
    }

    override fun onStop() {
        super.onStop()
        sp?.release()
        sp = null
        upper.setOnClickListener {
            var text = linkEdit.text.toString()
            linkEdit.setText(text.toUpperCase())
        }
    }


    private fun startConvert() {
        var serviceComponent = ComponentName(this,YoutubeConvertMp3::class.java)
        var mJobInfo = JobInfo.Builder(jobSchedulerId,serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
        var jobLinkConvert = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobLinkConvert.schedule(mJobInfo.build())
        Toast.makeText(this, "Job Services Running",Toast.LENGTH_SHORT).show()
    }

    private fun stopConvert() {
        var jobLinkConvert = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobLinkConvert.cancel(jobSchedulerId)
        Toast.makeText(this, "Job Services Stopped",Toast.LENGTH_SHORT).show()
    }
}