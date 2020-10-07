package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User

data class PostOnlyID (val postId : String){
}

class GetUserByIdResponse (
    user : User,
    userPosts : List<Post>
) {
    val name : String = user.name
    val image : String = user.image
    val posts : List<PostOnlyID> = userPosts.map { PostOnlyID ( it.id) }

    /*
    fun setPosts(ps : List<Post>) : MutableList<Post> {
        var postsList : MutableList<Post> = mutableListOf()
        for (post in ps) {
            postsList.add(post)
        }
        return postsList
    }*/

}
