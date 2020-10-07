package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Post

class PostResponse(val post: Post){
    val posts = TimeLineResponse(post)
    val comments :List<CommentResponse> = post.comments.map{ CommentResponse(it) }

}
