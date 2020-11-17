package ar.unq.controllers


import ar.unq.utils.responses.UserResponses.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.unq.ui.model.InstagramSystem
import java.net.URLDecoder


class SearchController(val instagramSystem : InstagramSystem) {

    fun get(ctx: Context){
        val text : String? = ctx.queryParam("q")

        if (text == null || text == "") throw BadRequestResponse()

        ctx.status(200).json(possibleResult(text, getUserId(ctx)))

    }
    private fun possibleResult(text:String, userId: String): SearchResponse {

        if (text.get(0).equals('#')) {
         return SearchResponse(searchByTag(text, userId))
        } else{
         return SearchResponse(searchByName(text))
        }
    }
    private fun searchByTag(text:String, userId: String): List<PostResponse> {
        return    instagramSystem.searchByTag(text).map { PostResponse(it, userId) }
    }
    private fun searchByName(text:String): List<UserResponse> {
        return    instagramSystem.searchByName(text).map{UserResponse(it)}
    }
    private fun getUserId(ctx: Context): String {
        return ctx.attribute<String>("userId") ?: throw BadRequestResponse("Not found user")
    }
}