package ar.unq.controllers

import ar.unq.utils.responses.ErrorResponse
import ar.unq.utils.responses.UserResponses.*
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
    fun possibleResult(text:String): SearchResponse {
        var result : SearchResponse
        if (URLDecoder.decode(text, "utf-8").get(0).equals('#')) {
           result = SearchResponse(searchByTag(text))
        } else{
         result =   SearchResponse(searchByName(text))
        }
    return result
    }
    fun searchByTag(text:String): List<PostResponse> {
        return    instagramSystem.searchByTag(text).map { PostResponse(it) }
    }
    fun searchByName(text:String): List<UserResponse> {
        return    instagramSystem.searchByName(text).map{UserResponse(it)}
    }

}