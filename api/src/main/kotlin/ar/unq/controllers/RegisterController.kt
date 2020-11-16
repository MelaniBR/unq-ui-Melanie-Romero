package ar.unq.controllers

import ar.unq.token.TokenController
import ar.unq.utils.requests.RegisterRequest
import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
import ar.unq.utils.responses.UserResponses.UserResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.UsedEmail


class RegisterController(val instagramSystem : InstagramSystem) {
    fun post(ctx: Context){
        val newUserData = ctx.body<RegisterRequest>()
        try{
            val user = instagramSystem.register(
                newUserData.name,
                newUserData.email,
                newUserData.password,
                newUserData.image)
            ctx.header("Authorization", TokenController().generateToken(user))
            ctx.status(201).json(UserResponse(user))

        }catch(e : UsedEmail){
            //409 Conflict Response
            ctx.status(409).json(ErrorResponse("The email is already in use"))
        }

    }
}