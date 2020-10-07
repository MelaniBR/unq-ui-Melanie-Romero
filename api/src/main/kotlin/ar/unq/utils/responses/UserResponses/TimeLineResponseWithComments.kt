package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Post

data class TimeLineResponseWithComments(private val post: Post):TimeLineResponse(post){
     val comments :List<CommentResponse> = post.comments.map{ CommentResponse(it) }

}
