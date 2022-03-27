package com.example.moviestreaming.model.`object`

object UserInformation {
    var email:String? = null
    private set
    var phone:String? = null
    private set
    var password:String? = null
    private set

    fun updateData(email:String? , phone:String? , password:String?){
        UserInformation.email = email
        UserInformation.phone = phone
        UserInformation.password = password
    }
}