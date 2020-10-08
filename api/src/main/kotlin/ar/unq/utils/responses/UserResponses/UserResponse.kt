package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Post
import org.unq.ui.model.User

open class UserResponse( private val user : User ) {
    val name : String = user.name
    val image : String = user.image
    val followers : List<FollowerResponse> = user.followers.map { FollowerResponse( it.name, it.image) }
}