package ar.unq.controllers

import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound


class UserController(val instagramSystem : InstagramSystem) {
    fun get(ctx: Context){

    }
    fun getById(ctx: Context){
        val id = ctx.pathParam("userId")
        try {
            ctx.status(200).json(instagramSystem.getUser(id)).
            //aca quiero devolver en un json lo que devuelve getUser(id) y searchByUserId(id)
        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("No se encontro usuario con "+ id))
        }

    }
    fun toggleFollower(ctx: Context){

    }
}