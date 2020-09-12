package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel (
    val instagramSystem: InstagramSystem,
    var userId: String = "",
    var name: String = "",
    var email: String = "",
    var password:String = "",
    var rePassword: String = "",
    var imagen: String = ""
)