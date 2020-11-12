package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Post

data class PostResponseWithComments(private val post: Post, private val userId: String):PostResponse(post, userId){
     val comments :List<CommentResponse> = post.comments.map{ CommentResponse(it) }

}
