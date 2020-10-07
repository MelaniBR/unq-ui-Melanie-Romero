package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Comment


data class CommentResponse(private val comment: Comment) {
    val id : String = comment.id
    val body : String = comment.body
    val user = SimpleUserResponse(comment.user.name,comment.user.image)
}