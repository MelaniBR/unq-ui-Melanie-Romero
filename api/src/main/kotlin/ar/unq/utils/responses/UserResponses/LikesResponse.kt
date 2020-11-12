package ar.unq.utils.responses.UserResponses

import org.unq.ui.model.Post

class PostLikesResponse (
        private val post: Post, private val userId: String
    ){
        val likes : List<LikeResponse> = post.likes.map{ LikeResponse(it.name, it.image, it.id) }
        val like : Boolean = post.likes.any { it.id == userId }
    }