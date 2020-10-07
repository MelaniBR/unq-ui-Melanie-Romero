package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User

data class GetUserByIdResponse(
    private val user : User,
    private val userPosts : List<Post>
) : UserResponse(user, userPosts) {
    val posts : List<PostResponse> = userPosts.map { PostResponse ( it ) }
}
