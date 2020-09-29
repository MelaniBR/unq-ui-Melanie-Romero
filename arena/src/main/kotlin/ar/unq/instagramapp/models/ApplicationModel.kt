package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable


@Observable
class ApplicationModel(
    val instagramSystem: InstagramSystem,
    var user: User? = null
){

    var notLogin: Boolean = true
    var loginOk : Boolean = false
        set(value) {
            notLogin = !value
            field = value
        }




}

