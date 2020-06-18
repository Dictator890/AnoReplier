package com.example.anoreplier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log


class SMSBroadcastGenerator:BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        try {
            var action:String?=intent?.action
            if(action.equals("android.provider.Telephony.SMS_RECEIVED"))
            {


                var data=intent?.extras?.get("pdus") as Array<*>
                var msg=""
                for(i in 0 until data.size)
                {
                    var format=intent?.extras?.getString("format")
                    var message=SmsMessage.createFromPdu(data[i] as ByteArray ,format)
                    msg+=message.originatingAddress

                }
                /*
                * This section sends back the reply
                */
                var x=MessageFileIO()
                var validate=Validation()
                if(validate.validate(msg))
                {
                    var manager=SmsManager.getDefault()
                    manager.sendTextMessage(msg,null,x.get_message(),null,null)
                    var inc:Int=x.get_total_replies()
                    inc += 1
                    x.write_replies(inc)
                    MainActivity.getInstance().update_replies(x)
                }




            }

        }
        catch(exc:Exception)
        {
            Log.e("SMSBroadcast Receiver","An error occured.Details: ${exc}")
        }

    }


}



