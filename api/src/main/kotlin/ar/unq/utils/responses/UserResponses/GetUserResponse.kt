package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User

class GetUserResponse(
    name : String,
    image : String
) {
    val name : String = name
    val image : String = image
    val followers : MutableList<Follower> = mutableListOf()
    val timeline : MutableList<Post> = mutableListOf()

    fun setFollowers(userFollowers : MutableList<User>) {
        for (follower in userFollowers) {
            followers.add(Follower(follower.name, follower.image))
        }
    }

    fun setTimeline(posts : List<Post>) {
        for (post in posts) {
            timeline.add(post)
        }
    }

}

