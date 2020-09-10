package ar.unq.instagramapp.models

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable


@Observable
class ApplicationModel(
    val instagramSystem: InstagramSystem,
    var loginOk : Boolean = false,
    var user: User? = null
)