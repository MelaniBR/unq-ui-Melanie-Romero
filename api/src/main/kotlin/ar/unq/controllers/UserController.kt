package ar.unq.controllers

import ar.unq.token.TokenController
import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.UserResponses.GetUserByIdResponse
import ar.unq.utils.responses.UserResponses.GetUserResponse
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import javalinjwt.JavalinJWT
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound


class UserController(val instagramSystem : InstagramSystem) {

    val tokenController = TokenController()

    fun get(ctx: Context){

            try {
                val decodedToken = JavalinJWT.getDecodedFromContext(ctx)
                val user = instagramSystem.getUser(decodedToken.getClaim("id").asString())
                val userTimeline = instagramSystem.timeline(decodedToken.getClaim("id").asString())
                val userDTO = GetUserResponse(user.name, user.image)
                userDTO.setFollowers(user.followers)
                userDTO.setTimeline(userTimeline)

                ctx.attribute("id", decodedToken.getClaim("id").asString())
                ctx.status(200).json(userDTO)
            } catch (e: Exception) {
                ctx.status(500).json(ErrorResponse("Todavia no se implemento correctamente"))
            }
    }

    fun getById(ctx: Context){
        val id = ctx.pathParam("userId")
        try {
            val userID = instagramSystem.getUser(id)
            val userPosts = instagramSystem.searchByUserId(id)

            val response = GetUserByIdResponse(userID.name, userID.image)
            response.setFollowers(userID.followers)
            response.setPosts(userPosts)

            ctx.status(200).json(response)

        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("No se encontro usuario con "+ id))
        }

    }
    fun toggleFollower(ctx: Context){

    }
}