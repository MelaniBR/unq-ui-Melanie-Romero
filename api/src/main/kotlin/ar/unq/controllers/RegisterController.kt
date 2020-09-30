package ar.unq.controllers

import ar.unq.utils.requests.RegisterRequest
import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
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
            ctx.status(200).json(OkResponse())

        }catch(e : UsedEmail){
            ctx.status(400).json(ErrorResponse("El email ya esta en uso"))
        }

    }
}