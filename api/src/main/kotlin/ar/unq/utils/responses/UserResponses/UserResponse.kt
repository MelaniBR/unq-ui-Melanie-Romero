package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Post
import org.unq.ui.model.User

open class UserResponse( private val user : User) {
    val name : String = user.name
    val image : String = user.image
    val id : String = user.id
}