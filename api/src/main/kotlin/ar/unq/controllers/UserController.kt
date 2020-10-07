package ar.unq.controllers

import ar.unq.token.TokenController
import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
import ar.unq.utils.responses.UserResponses.GetUserByIdResponse
import ar.unq.utils.responses.UserResponses.GetUserResponse
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse
import javalinjwt.JavalinJWT
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.Post
import org.unq.ui.model.User


class UserController(val instagramSystem : InstagramSystem) {

    //val tokenController = TokenController()

    fun get(ctx: Context){

        val userId : String = ctx.attribute<String>("userId").toString()
        var user: User? = null;

        try {
            user = instagramSystem.getUser(userId)

        } catch (e: NotFound) {
            //TODO: aca devolver un notfound cuando el usuario no existe
            ctx.status( 404 ).json(ErrorResponse("El usuario no existe"))
        } catch (e : Exception) {
            ctx.status(500).json(ErrorResponse("Error interno"))
        }

        val userTimeline = instagramSystem.timeline(userId)
        val userDTO = GetUserResponse(user!!, userTimeline)
        ctx.status(200).json(userDTO!!)

    }

    fun getById(ctx: Context){
        val id = ctx.pathParam("userId")
        var user : User? = null
        var userPosts : List<Post> = listOf()

        try {
            user = instagramSystem.getUser(id)
        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("No se encontro usuario con "+ id))
        }
        try {
            userPosts = instagramSystem.searchByUserId(id)
        }catch (e : Exception) {
            ctx.status(500).json(ErrorResponse("Error interno"))
        }

        val userDTO = GetUserByIdResponse(user!!, userPosts)
        ctx.status(200).json(userDTO)
    }

    fun toggleFollower(ctx: Context){
        val followedUserId = ctx.pathParam("userId")
        val loggedUserId : String = ctx.attribute<String>("userId").toString()
        try {
            instagramSystem.updateFollower(loggedUserId, followedUserId)
            ctx.status(200).json(OkResponse())
        }catch (e: NotFound) {
            ctx.status(404).json(ErrorResponse("No existe el usuario con id " + followedUserId))
        }

    }
}