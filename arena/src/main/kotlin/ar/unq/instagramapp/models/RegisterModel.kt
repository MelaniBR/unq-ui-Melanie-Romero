package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem

class RegisterModel (
    val instagramSystem: InstagramSystem,
    var name: String = "",
    var email: String = "",
    var password:String = "",
    var rePassword: String = "",
    var imagen: String = ""
)