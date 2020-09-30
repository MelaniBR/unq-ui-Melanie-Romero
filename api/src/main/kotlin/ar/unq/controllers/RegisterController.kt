package ar.unq.controllers

import ar.unq.utils.requests.RegisterRequest
import ar.unq.utils.responses.ErrorResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.UsedEmail


class RegisterController(val instagramSystem : InstagramSystem) {
    fun post(ctx: Context){
        val newUserData = ctx.body<RegisterRequest>()
        try{
            instagramSystem.register(
                newUserData.name,
                newUserData.email,
                newUserData.password,
                newUserData.image)

        }catch(e : UsedEmail){
            ctx.status(400).json(ErrorResponse("Email already in use"))
        }

    }
}