package com.example.converterapp2

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlinx.android.synthetic.main.activity_file_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mInterAds : InterstitialAd? = null
    private var mRewardVid : RewardedAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load ad
        MobileAds.initialize(this) {}
        adView.loadAd(AdRequest.Builder().build())

        //menambahkan listener
        adView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                Toast.makeText(applicationContext, "Load Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        //menutup ad
        closeAd.setOnClickListener {
            adView.destroy()
            adView.visibility = View.GONE
            closeAd.visibility = View.GONE
            Toast.makeText(applicationContext, "Ad Closed!", Toast.LENGTH_SHORT).show()
        }

        InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        Toast.makeText(applicationContext, "Load Failed!", Toast.LENGTH_SHORT).show()
                        mInterAds = null
                    }
                    override fun onAdLoaded(p0: InterstitialAd) {
                        super.onAdLoaded(p0)
                        mInterAds = p0
                    }
                })

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917", AdRequest.Builder().build(),
                object : RewardedAdLoadCallback() {
                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        Toast.makeText(applicationContext, "Load Failed!", Toast.LENGTH_SHORT).show()
                        mRewardVid = null
                    }
                    override fun onAdLoaded(p0: RewardedAd) {
                        super.onAdLoaded(p0)
                        mRewardVid = p0
                    }
                })

        var amount = 0
        chooseFile.setOnClickListener {
            if (mInterAds!=null)
                mInterAds?.show(this)
            val intentChooseFile = Intent(this, FileType::class.java)
            startActivity(intentChooseFile)
        }
        fileList.setOnClickListener {
            if (mRewardVid!=null)
                mRewardVid?.show(this, OnUserEarnedRewardListener {
                    amount += 1
                    Toast.makeText(applicationContext, "File Limit +${amount.toString()}", Toast.LENGTH_SHORT).show()
                })
            val intentFileList = Intent(this, FileList::class.java)
            startActivity(intentFileList)
        }
        resizePic.setOnClickListener {
            val intentResizePic = Intent(this, ResizePic::class.java)
            startActivity(intentResizePic)
        }
        convertion.setOnClickListener {
            val intentConvert = Intent(this, ConvertFile::class.java)
            startActivity(intentConvert)
        }
        link_convertion.setOnClickListener {
            val intentlinkConvert = Intent(this, ConvertLink::class.java)
            startActivity(intentlinkConvert)
        }
        internalSave.setOnClickListener {
            val intentInternalSave = Intent(this, InternalSave::class.java)
            startActivity(intentInternalSave)
        }

/** Untuk memastikan alarm manager berjalan */
        var alarmIntent = Intent(this, MyMessage::class.java).let {
            it.action = MyMessage.ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(this, 101, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, 1)

        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC, cal.timeInMillis, 60000L, alarmIntent)
    }

    fun showInterstitial(view: View) {

    }
}