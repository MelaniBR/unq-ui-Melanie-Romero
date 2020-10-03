package ar.unq.controllers

import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
import ar.unq.utils.responses.UserResponses.GetUserByIdResponse
import ar.unq.utils.responses.UserResponses.GetUserResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.Post
import org.unq.ui.model.User


class UserController(val instagramSystem : InstagramSystem) {
    fun get(ctx: Context){
        val id = "u_31"
        try {
            val us = instagramSystem.getUser(id)
            val ti = instagramSystem.timeline(id)

            val response = GetUserResponse(us.name, us.image)
            response.setFollowers(us.followers)
            response.setTimeline(ti)

            ctx.status(200).json(response)
        }catch (e : Exception) {
            ctx.status(500).json(ErrorResponse("Todavia no se implemento correctamente"))
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