package com.example.anoreplier

class Validation {
   fun validate(no:String):Boolean
   {
      return(is_number(no) && is_valid_length(no))
   }

    fun is_number(no:String):Boolean
    {
        if(no.contains("[^0-9]".toRegex()))
        {
            return false
        }
        return true
    }

    fun is_valid_length(no:String):Boolean
    {
        if(no.length >9)
            return true
        return false
    }
}