package com.example.anoreplier

import android.Manifest

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    companion object
    {
        private const val TAG="AnoReplier"
        private val permissions= arrayOf(Manifest.permission.READ_SMS,Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
        private const val REQUEST_CODE_PERMISSIONS=1

lateinit var service:Intent
        lateinit var inst:MainActivity

        fun getInstance():MainActivity
        {
            return inst
        }
    }

    override fun onStart() {
        super.onStart()
        inst=this
        service=Intent(baseContext,SMSService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

        if(!checkifPermissionsGranted())
        {
            requestPermissions(permissions, REQUEST_CODE_PERMISSIONS)
        }


        var msgio=MessageFileIO()

        var file=File(FileIO.OUTPUT_DIRECTORY)
        var msgfile=File(msgio.generate_MessageFile_directory())
        var replyfile=File(msgio.generate_TotalRepliesFile_directory())


            if(!file.exists())
            {

                file.mkdirs()
                Log.d(TAG,"Directory made")
            }

        if(!replyfile.exists())
        {
            replyfile.createNewFile()

            if(!msgio.write_replies(FileIO.DEFAULT_TOTAL_REPLIES))
            {
                Toast.makeText(this,"Failed to fetch files",Toast.LENGTH_SHORT).show()
            }

        }
        if(!msgfile.exists())
        {
            msgfile.createNewFile()
           if(!msgio.write_message(FileIO.DEFAULT_MESSAGE))
           {
               Toast.makeText(this,"Failed to write the message",Toast.LENGTH_SHORT).show()
           }

        }


        totalRepliesdisplay.text=get_reply_message(msgio)
        messageDisplay.text=get_current_message(msgio)

        submit.setOnClickListener {
            var message=messagearea.text.toString()
            Log.d(TAG,message)
            message=message.trim()
            if(msgio.write_message(message))
            {
                messageDisplay.text=get_current_message(msgio)
            }
            else
            {
                Toast.makeText(this,"Sorry an error occured",Toast.LENGTH_SHORT).show()
            }
        }

        servicebutton.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
if(b)
{
    Log.d(TAG,"True condition of toggle button")
    startService(service)

    Log.d(TAG,"Service started")
    compoundButton.setBackgroundColor(Color.GREEN)
}
            else
{
    stopService(service)
    compoundButton.setBackgroundColor(Color.RED)
}
        }





    }



    private fun checkifPermissionsGranted()= permissions.all {
        ContextCompat.checkSelfPermission(baseContext,it)==PackageManager.PERMISSION_GRANTED
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
       if(!checkifPermissionsGranted())
       {
           Toast.makeText(this,"Please grant all the permissions.",Toast.LENGTH_SHORT).show()
           Thread.sleep(1500)
           System.exit(0)
       }
    }
 fun get_reply_message(msgio:MessageFileIO):String
 {
     return (msgio.get_total_replies().toString())
 }

    fun get_current_message(msgio: MessageFileIO):String
    {
        return (msgio.get_message().toString())
    }

    fun update_replies(msgio:MessageFileIO)
    {
        totalRepliesdisplay.text=msgio.get_total_replies().toString()
    }



}
