package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User

data class GetUserByIdResponse(
    private val user : User,
    private val userPosts : List<Post>,
    val userFollowed : Boolean
) : UserResponse(user) {
    val posts : List<PostResponse> = userPosts.map { PostResponse ( it, user.id ) }
    val followed : Boolean = userFollowed;
}
