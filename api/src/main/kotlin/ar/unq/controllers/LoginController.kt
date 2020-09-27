package ar.unq.controllers

import ar.unq.utils.requests.LoginRequest
import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
import io.javalin.http.Context
import org.omg.CORBA.UserException
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound


class LoginController(val instagramSystem : InstagramSystem) {
    fun post(ctx: Context){
        val login = ctx.body<LoginRequest>()
        try{
            instagramSystem.login(login.email, login.password)
            ctx.status(200).json(OkResponse())
        }catch(e : NotFound){
            ctx.status(404).json(ErrorResponse("El usuario o el password son invalidos"))
        }

    }
}