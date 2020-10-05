package ar.unq.controllers

import ar.unq.Token.Token
import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.UserResponses.GetUserByIdResponse
import ar.unq.utils.responses.UserResponses.GetUserResponse
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound


class UserController(val instagramSystem : InstagramSystem) {

    val tokenController = Token()

    fun get(ctx: Context){

        val token = ctx.header("Authorization")

        if(token === null) {
            throw UnauthorizedResponse()
        }else {
            val id = tokenController.validateToken(token)
            try {
                val us = instagramSystem.getUser(id)
                val ti = instagramSystem.timeline(id)
                val userDTO = GetUserResponse(us.name, us.image)
                userDTO.setFollowers(us.followers)
                userDTO.setTimeline(ti)


                ctx.status(200).json(userDTO)
            } catch (e: Exception) {
                ctx.status(500).json(ErrorResponse("Todavia no se implemento correctamente"))
            }
        }

    }
    fun getById(ctx: Context){
        val id = ctx.pathParam("userId")
        try {
            val us = instagramSystem.getUser(id)
            val po = instagramSystem.searchByUserId(id)

            val response = GetUserByIdResponse(us.name, us.image)
            response.setFollowers(us.followers)
            response.setPosts(po)

            ctx.status(200).json(response)

        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("No se encontro usuario con "+ id))
        }

    }
    fun toggleFollower(ctx: Context){

    }
}