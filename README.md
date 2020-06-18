# AnoReplier
 An Android auto SMS replier app

This project is aimed at making an Android app which will Auto reply messages with a default message so that you will not get disturbed.


This project is an Android app developed in Kotlin

## Project Structure and File Details:-

1.  When you go to the src you will see 6 files 

2.  The FileIO file will hold all the constants i.e constant values for the Project

3.  The MainActivity is the class which is the main running application of the class

4.  MessageFileIO is the class which will contain all the IO operations between files and application

5.  SMSBroadcastGenerator is the class which acts as the broadcast receiver for incoming SMS

6.  SMSService is the service that will look at registering and unregistering the Broadcast receiver and look at its lifetime

7.  Validation is the class that will validate the data before acting on it
