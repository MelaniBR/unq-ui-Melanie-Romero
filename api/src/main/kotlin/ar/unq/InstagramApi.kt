package ar.unq
import ar.unq.controllers.*
import ar.unq.utils.InstagramAccessManager
import org.unq.ui.bootstrap.getInstagramSystem
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin

    enum class InstagramRoles : Role {
        ANYONE, USER
    }

val instagramSystem = getInstagramSystem()

fun main(args: Array<String>) {
    val app = Javalin.create{
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes", setOf(InstagramRoles.ANYONE)))
        it.accessManager(InstagramAccessManager(instagramSystem))
    }.start(7000)

    val instagramSystem = getInstagramSystem()

    app.routes{
        path("register"){
            post(RegisterController(instagramSystem)::post, setOf(InstagramRoles.ANYONE))
        }
        path("login"){
            post(LoginController(instagramSystem)::post, setOf(InstagramRoles.ANYONE))
        }
        path("user"){
            get(UserController(instagramSystem)::get, setOf(InstagramRoles.USER))
            path(":userId"){
                get(UserController(instagramSystem)::getById, setOf(InstagramRoles.USER))
                path("follow"){
                    put(UserController(instagramSystem)::toggleFollower, setOf(InstagramRoles.USER))
                }
            }
        }
        path("post"){
            path(":postId") {
                get(PostController(instagramSystem)::getById,setOf(InstagramRoles.USER))
                path("like") {
                    put(PostController(instagramSystem)::toggleLike,setOf(InstagramRoles.USER))
                }
                path("comment") {
                    put(PostController(instagramSystem)::addComment,setOf(InstagramRoles.USER))
                }
            }
        }
        path("search"){
            get(SearchController(instagramSystem)::get,setOf(InstagramRoles.ANYONE))
        }
    }
}


