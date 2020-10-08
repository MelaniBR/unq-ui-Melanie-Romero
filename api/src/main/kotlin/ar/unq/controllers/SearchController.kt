package ar.unq.controllers


import ar.unq.utils.responses.UserResponses.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import java.net.URLDecoder


class SearchController(val instagramSystem : InstagramSystem) {
    fun get(ctx: Context){
        val text : String? = ctx.queryParam("search")
        if (text == null || text == "") throw BadRequestResponse()

        ctx.status(200).json(possibleResult(text))

    }
    fun possibleResult(text:String): SearchResponse {

        if (URLDecoder.decode(text, "utf-8").get(0).equals('#')) {
         return SearchResponse(searchByTag(text))
        } else{
         return SearchResponse(searchByName(text))
        }
    }
    fun searchByTag(text:String): List<PostResponse> {
        return    instagramSystem.searchByTag(text).map { PostResponse(it) }
    }
    fun searchByName(text:String): List<UserResponse> {
        return    instagramSystem.searchByName(text).map{UserResponse(it)}
    }

}