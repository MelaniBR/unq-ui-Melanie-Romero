package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User


data class GetUserResponse(
    private val user : User ,
    private val userTimeline: List<Post>,
    private val userFollowers: List<User>
) : UserResponse(user){
    val timeline : List<PostResponse> = userTimeline.map { PostResponse( it, user.id ) }
    val followers : List<FollowerResponse> = userFollowers.map { FollowerResponse( it.name, it.image, it.id )}
}
