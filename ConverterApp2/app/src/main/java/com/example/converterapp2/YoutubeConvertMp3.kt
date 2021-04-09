package com.example.converterapp2

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class YoutubeConvertMp3 : JobService() {
    val appId = "lF-jPBnZ098"
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.w("Job","Mulai")
        getYoutubeConvertMp3(params)
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.w("Job","Berhenti")
        return true
    }

    private fun getYoutubeConvertMp3(jobParameters: JobParameters?) {
        var client = AsyncHttpClient()
        var url = "https://coolguruji-youtube-to-mp3-download-v1.p.rapidapi.com/?id=lF-jPBnZ098"
        val charSet = Charsets.UTF_8
        var handler = object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                var result = responseBody?.toString(charSet) ?: "Kosong"
                Log.w("Hasil", result)
                jobFinished(jobParameters,false)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                jobFinished(jobParameters,true)
            }
        }
        client.get(url,handler)
    }
}