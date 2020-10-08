package ar.unq.controllers

import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.UserResponses.GetUserByIdResponse
import ar.unq.utils.responses.UserResponses.GetUserResponse
import ar.unq.utils.responses.UserResponses.PostResponse
import ar.unq.utils.responses.UserResponses.UserResponse
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.Post
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.URIParameter
import java.util.*

class SearchController(val instagramSystem : InstagramSystem) {
    fun get(ctx: Context){
        val text : String? = ctx.queryParam("search")
        if (text == null || text == "") throw BadRequestResponse()
        ctx.status(200).json(possibleResult(text))

    }
    fun possibleResult(text:String): List<Any> {
        if (URLDecoder.decode(text, "utf-8").get(0).equals('#')) {
        return    instagramSystem.searchByTag(text).map { PostResponse(it) }
        } else{
        return    instagramSystem.searchByName(text).map{UserResponse(it)}
        }

    }
}