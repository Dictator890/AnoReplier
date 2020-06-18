package com.example.anoreplier

import android.app.Notification
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat

class SMSService: Service() {
lateinit var broadcastReceiver: BroadcastReceiver
private val TAG="SMS Service"
    private val NOTIFICATION_ID=8975
    override fun onCreate() {
        Toast.makeText(MainActivity.getInstance(),"Background Service has started",Toast.LENGTH_SHORT).show()
        Log.d(TAG,"Service created")
         var filter=IntentFilter()
        filter.addAction("android.provider.Telephony.SMS_RECEIVED")
        Log.d(TAG,"Intent Filter addition done")
        var generator=SMSBroadcastGenerator()
        broadcastReceiver=SMSBroadcastGenerator()

        registerReceiver(broadcastReceiver,filter)
        Log.d(TAG,"Receiver Registered")
    }



    override fun onBind(p0: Intent?): IBinder? {
       return null
    }

    override fun onDestroy() {

        Toast.makeText(MainActivity.getInstance(),"Background Service has started",Toast.LENGTH_SHORT).show()
        unregisterReceiver(broadcastReceiver)
        Log.d(TAG,"Receiver Unregistered")
        super.onDestroy()


    }

}