package com.example.converterapp2

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_convert_link.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class ConvertLink : AppCompatActivity() {
    var jobSchedulerId = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_link)

        convertLink.setOnClickListener{
            startConvert()
        }
        cancelLink.setOnClickListener {
            stopConvert()
        }

    }


    private fun startConvert() {
        var serviceComponent = ComponentName(this,YoutubeConvertMp3::class.java)
        var mJobInfo = JobInfo.Builder(jobSchedulerId,serviceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
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