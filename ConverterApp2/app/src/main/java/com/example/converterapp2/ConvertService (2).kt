package com.example.converterapp2

import android.content.Context
import android.content.Intent
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.JobIntentService

class ConvertService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        var convertProgress = 0

        //perulangan untuk menjalankan progress bar
        do {
            Thread.sleep(1000)
            convertProgress+=20
            var intentFileConvert = Intent(ACTION_CONVERT)
            intentFileConvert.putExtra(EXTRA_PERSEN, convertProgress)
            intentFileConvert.putExtra(EXTRA_FINISH, false)
            if (convertProgress>=100)
                intentFileConvert.putExtra(EXTRA_FINISH, true)
            sendBroadcast(intentFileConvert)
        }while (convertProgress<100)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show()
    }
    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, ConvertService::class.java, JOB_ID_CONVERT, intent)
        }
    }
}