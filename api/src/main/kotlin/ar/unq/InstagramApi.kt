package ar.unq
import ar.unq.controllers.*
import org.unq.ui.bootstrap.getInstagramSystem
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin

fun main(args: Array<String>) {
    val app = Javalin.create{
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
    }.start(7000)

    val instagramSystem = getInstagramSystem()

    app.routes{
        path("register"){
            post(RegisterController(instagramSystem)::post)
        }
        path("login"){
            post(LoginController(instagramSystem)::post)
        }
        path("user"){
            get(UserController(instagramSystem)::get)
            path(":userId"){
                get(UserController(instagramSystem)::getById)
                path("follow"){
                    put(UserController(instagramSystem)::toggleFollower)
                }
            }
        }
        path("post"){
            path(":postId") {
                get(PostController(instagramSystem)::getById)
                path("like") {
                    put(PostController(instagramSystem)::toggleLike)
                }
                path("comment") {
                    put(PostController(instagramSystem)::addComment)
                }
            }
        }
        path("search"){
            get(SearchController(instagramSystem)::get)
        }
    }
}


