package ar.unq.controllers

import ar.unq.token.TokenController
import ar.unq.utils.requests.LoginRequest
import ar.unq.utils.requests.RegisterRequest
import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.OkResponse
import ar.unq.utils.responses.UserResponses.UserResponse
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound


class LoginController(val instagramSystem : InstagramSystem) {

    fun post(ctx: Context){
        val login = ctx.body<LoginRequest>()
        if(isNullOrEmptyData(login)){
            throw BadRequestResponse("empty field")
        }
        userIsNotNull(ctx,login)
    }
    fun isNullOrEmptyData(login: LoginRequest): Boolean {
        return   login.email.isNullOrEmpty() &&
                login.password.isNullOrEmpty();
    }

    fun userIsNotNull(ctx: Context,login: LoginRequest){
        try{
            val user = instagramSystem.login(login.email, login.password)
            ctx.header("Authorization", TokenController().generateToken(user))
            ctx.status(200).json(UserResponse(user))
        }catch(e : NotFound){
            ctx.status(404).json(ErrorResponse("User not found"))
        }
    }
}