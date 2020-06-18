package com.example.anoreplier

import android.util.Log

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

class MessageFileIO {
    companion object{
        private val TAG="MessageFileIO"
                    }

    fun write_message(msg:String):Boolean
    {
       var br= BufferedWriter( FileWriter(generate_MessageFile_directory(),false))
        try {
            br.write(msg)

        }
        catch(exc:Exception) {
            Log.e(TAG,"Error occured: ${exc}")
            return false
        }
        finally {
            br.flush()
            br.close()
        }
        return true

    }




    fun get_message():String
    {
        var  br=BufferedReader(FileReader(generate_MessageFile_directory()))
        var  msg:String=""
        var temp:String?=""
        try {
            temp=br.readLine()
            while(temp!=null)
            {
                msg+=temp+"\n"
                temp=br.readLine()

            }
            Log.d(TAG,"Message read ${msg}")
        }
        catch(exc:Exception)
        {
            Log.e(TAG,"Error occured while reading the message.Complete details are:-")
            Log.e(TAG,"${exc}")
            return "Undefined"

        }
        return msg
    }





    fun get_total_replies():Int
    {
        var  br=BufferedReader(FileReader(generate_TotalRepliesFile_directory()))
        var  replies=0
        try {
           replies=Integer.parseInt(br.readLine())
        }
        catch(exc:Exception)
        {
            Log.e(TAG,"Error occured while reading the total replies.Complete details are:-")
            Log.e(TAG,"${exc}")
            return 0

        }
        return replies
    }





    fun write_replies(count:Int):Boolean
    {
        var br=BufferedWriter( FileWriter(generate_TotalRepliesFile_directory(),false))
        try {
            Log.d(TAG,"Value of count : ${count}")
            br.write(count.toString())

        }
        catch(exc:Exception) {
            Log.e(TAG,"Error occured: ${exc}")
            return false
        }
        finally {
            br.flush()
            br.close()
        }
        return true

    }

    fun generate_MessageFile_directory():String{

        return FileIO.OUTPUT_DIRECTORY+"/"+FileIO.MESSAGE_FILE_NAME
    }

    fun generate_TotalRepliesFile_directory():String{

        return FileIO.OUTPUT_DIRECTORY+"/"+FileIO.REPLY_FILE_NAME
    }

}