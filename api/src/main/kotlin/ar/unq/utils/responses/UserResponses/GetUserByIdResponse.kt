package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User

class GetUserByIdResponse (
    name : String,
    image : String,
    userFollowers : MutableList<User>,
    userPosts : List<Post>
) {
    val name : String = name
    val image : String = image
    val followers : MutableList<Follower> = setFollowers(userFollowers)
    val posts : MutableList<Post> = setPosts(userPosts)

    fun setFollowers(userFollowers : MutableList<User>) : MutableList<Follower> {
        var followersList : MutableList<Follower> = mutableListOf()
        for (follower in userFollowers) {
            followersList.add(Follower(follower.name, follower.image))
        }
        return followersList
    }

    fun setPosts(ps : List<Post>) : MutableList<Post> {
        var postsList : MutableList<Post> = mutableListOf()
        for (post in ps) {
            postsList.add(post)
        }
        return postsList
    }

}
