package com.room.ps.googleadsmob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

//Rewarded Video AdListener implementation
class MainActivity : AppCompatActivity(), RewardedVideoAdListener {
    private lateinit var adView: AdView
    private lateinit var adButton : Button
    private lateinit var adButtonVideo : Button
    private lateinit var interestital : InterstitialAd
    private lateinit var videoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for interstitial ads
        interestital = InterstitialAd(this)
        interestital.apply {
            adUnitId = "ca-app-pub-3940256099942544/1033173712"
            loadAd(AdRequest.Builder().build())
            object : AdListener(){
                override fun onAdClosed() {
                    interestital.loadAd(AdRequest.Builder().build())
                }

                override fun onAdLeftApplication() {
                    interestital.loadAd(AdRequest.Builder().build())
                }
            }
        }

        //Google mobileAds SDK
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
        videoAd = MobileAds.getRewardedVideoAdInstance(this@MainActivity)
        videoAd.rewardedVideoAdListener = this@MainActivity

        adView = findViewById(R.id.AdView)
        adButton = findViewById(R.id.button_ads)
        adButtonVideo = findViewById(R.id.button_video_ads)
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.apply {
            loadAd(adRequest)
            //for the ads listener
            object : AdListener() {
                override fun onAdLoaded() {
                    TODO("Not yet implemented")
                }

                override fun onAdFailedToLoad(p0: Int) {
                    TODO("Not yet implemented")
                }

                override fun onAdOpened() {
                    TODO("Not yet implemented")
                }

                override fun onAdClicked() {
                    TODO("Not yet implemented")
                }

                override fun onAdLeftApplication() {
                    TODO("Not yet implemented")
                }

                override fun onAdClosed() {
                    adView.loadAd(AdRequest.Builder().build())
                }
            }
        }

        adButton.setOnClickListener {
            if (interestital.isLoaded){
                interestital.show()
            }
            else Toast.makeText(this, "No ADS Available", Toast.LENGTH_LONG).show()
        }

        adButtonVideo.setOnClickListener {
            if (videoAd.isLoaded){
                videoAd.show()
            }
            else Toast.makeText(this, "No ADS Available", Toast.LENGTH_LONG).show()
        }

        loadRewardedVideoAd()
    }

    private fun loadRewardedVideoAd():Unit{
        videoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
            AdRequest.Builder().build())
    }

    //overridden function of Reward ADS
    override fun onRewardedVideoAdClosed() {
        loadRewardedVideoAd()
    }

    override fun onRewardedVideoAdLeftApplication() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoAdLoaded() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoAdOpened() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoCompleted() {
        TODO("Not yet implemented")
    }

    override fun onRewarded(p0: RewardItem?) {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoStarted() {
        TODO("Not yet implemented")
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
        videoAd.pause(this)
    }

    override fun onResume() {
        super.onResume()
        videoAd.resume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        videoAd.destroy(this)
    }
}