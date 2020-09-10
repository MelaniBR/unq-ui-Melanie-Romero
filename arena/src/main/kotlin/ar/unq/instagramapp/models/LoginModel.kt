package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginModel(
    val instagramSystem: InstagramSystem,
    var email: String = "",
    var password:String = "",
    var error : Boolean = false,
    var mensaje : String = "",
    var user : User? = null

)