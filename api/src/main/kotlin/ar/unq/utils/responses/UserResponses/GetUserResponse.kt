package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User


data class GetUserResponse(
    private val user : User ,
    private val userTimeline: List<Post>
) : UserResponse(user){
    val timeline : List<PostResponse> = userTimeline.map { PostResponse( it ) }
}
