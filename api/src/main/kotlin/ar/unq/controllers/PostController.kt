package ar.unq.controllers

import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.UserResponses.TimeLineResponseWithComments
import io.javalin.http.Context
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

            ctx.status(404).json(ErrorResponse("No se encontro post con "+ id))

        }
        val postResponse : TimeLineResponseWithComments = TimeLineResponseWithComments(post!!)
        ctx.status(200).json(postResponse)
    }
    fun toggleLike(ctx: Context){

    }
    fun addComment(ctx: Context){

    }

}



