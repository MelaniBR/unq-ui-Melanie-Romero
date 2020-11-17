package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Post

open class PostResponse(
    private val post: Post, private val userId: String
){
    val id : String = post.id
    val description: String = post.description
    val portrait: String = post.portrait
    val landscape: String = post.landscape
    val likes : List<LikeResponse> = post.likes.map{ LikeResponse(it.name, it.image, it.id) }
    val like : Boolean = post.likes.any{ it.id === userId }
    val date: String = post.date.format( java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm") )
    val user : SimpleUserResponse = SimpleUserResponse(post.user.name, post.user.image)
}