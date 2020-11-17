package ar.unq.controllers

import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
import ar.unq.utils.responses.UserResponses.PostLikesResponse
import ar.unq.utils.responses.UserResponses.PostResponseWithComments
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.DraftComment
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.Post

class PostController(val instagramSystem : InstagramSystem) {
    fun getById(ctx: Context){
        var post : Post? = null
        val id = ctx.pathParam("postId")

        try {
            post = instagramSystem.getPost(id)
        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("Not found post with id" + id))
            return
        }
        val postResponse : PostResponseWithComments = PostResponseWithComments(post!!, getUserId(ctx))
        ctx.status(200).json(postResponse)
    }

    fun toggleLike(ctx: Context){
        val userId = getUserId(ctx)
        val idPost = ctx.pathParam("postId")
        try {
            instagramSystem.updateLike(idPost,userId)
            val post = instagramSystem.getPost(idPost)
            ctx.status(200).json(PostLikesResponse(post, userId))
        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("Not found post with id" + idPost))
        }
    }

    fun addComment(ctx: Context){
        val userId = getUserId(ctx)
        val idPost = ctx.pathParam("postId")
        val comment =  ctx.body<DraftComment>()
        try {
            instagramSystem.addComment(idPost,userId,comment)
            ctx.status(200).json(OkResponse())
        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("Not found post with id" + idPost))
        }


    }
    private fun getUserId(ctx: Context): String {
        return ctx.attribute<String>("userId") ?: throw BadRequestResponse("Not found user")
    }
}



