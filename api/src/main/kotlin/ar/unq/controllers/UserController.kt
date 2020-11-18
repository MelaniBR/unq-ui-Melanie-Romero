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

        val userId : String = ctx.attribute<String>("userId").toString()
        var user: User? = null;

        try {
            user = instagramSystem.getUser(userId)
        } catch (e: NotFound) {
            ctx.status( 404 ).json(ErrorResponse("User not found"))
            return
        }

        val userTimeline = instagramSystem.timeline(userId)
        val userFollowers = user.followers
        val userDTO = GetUserResponse(user!!, userTimeline, userFollowers)
        ctx.status(200).json(userDTO!!)

    }

    fun getById(ctx: Context){

        val currentuserId : String = ctx.attribute<String>("userId").toString()
        var currentUser : User? = null

        val id = ctx.pathParam("userId")
        var user : User? = null
        var userPosts : List<Post> = listOf()

        try {
            currentUser = instagramSystem.getUser(currentuserId)
            user = instagramSystem.getUser(id)
            userPosts = instagramSystem.searchByUserId(id)
        }catch(e : NotFound) {
            ctx.status(404).json(ErrorResponse("Not found user with id  " + id))
            return
        }

        val userDTO = GetUserByIdResponse(user!!, userPosts, currentUser!!.followers.any{it.id === id})
        ctx.status(200).json(userDTO)

    }

    fun toggleFollower(ctx: Context){

        val followedUserId = ctx.pathParam("userId")
        val loggedUserId : String = ctx.attribute<String>("userId").toString()
        try {
            instagramSystem.updateFollower(loggedUserId, followedUserId)
            ctx.status(200).json(OkResponse())
        }catch (e: NotFound) {
            ctx.status(404).json(ErrorResponse("Not found user with id " + followedUserId))
        }


    }

}