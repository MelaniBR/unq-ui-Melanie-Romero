package ar.unq.utils.responses.UserResponses;

import org.unq.ui.model.Post
import org.unq.ui.model.User


data class GetUserResponse(
    private val user : User ,
    private val userTimeline: List<Post>
){
    val name : String = user.name
    val image : String = user.image
    val followers : List<Follower> = user.followers.map { Follower( it.name, it.image) }
    val timeline : List<TimeLineResponse> = userTimeline.map { TimeLineResponse( it ) }

}