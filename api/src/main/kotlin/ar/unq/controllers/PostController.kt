package ar.unq.controllers

import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.UserResponses.GetUserByIdResponse
import ar.unq.utils.responses.UserResponses.LikeResponse
import ar.unq.utils.responses.UserResponses.PostInfoResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.User
class PostController(val instagramSystem : InstagramSystem) {
    fun getById(ctx: Context){
        val id = ctx.pathParam("postId")
        try {
            val post = instagramSystem.getPost(id)
            val likes = listLikeResponse(post.likes)

            val postResponse = PostInfoResponse(id,likes)

            ctx.status(200).json(postResponse)

        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("No se encontro post con "+ id))
        }
    }
    fun toggleLike(ctx: Context){

    }
    fun addComment(ctx: Context){

    }
    fun listLikeResponse (likes : MutableList<User>): MutableList<LikeResponse> {
        var listLikeResponse : MutableList <LikeResponse> = mutableListOf()
        for (l in likes ){
            listLikeResponse.add(LikeResponse(l.name,l.image))
        }
        return listLikeResponse
    }
}



