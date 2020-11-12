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

fun main(args: Array<String>) {

    val instagramSystem = getInstagramSystem()

    val registerController = RegisterController(instagramSystem)
    val loginController = LoginController(instagramSystem)
    val userController = UserController(instagramSystem)
    val postController = PostController(instagramSystem)
    val searchController = SearchController(instagramSystem)

    val app = Javalin.create{
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes", setOf(InstagramRoles.ANYONE)))
        it.accessManager(InstagramAccessManager(instagramSystem))
        it.enableCorsForAllOrigins()
    }.start(7000)

    app.before {
        it.header("Access-Control-Expose-Headers", "*")
    }

    app.routes{
        path("register"){
            post(registerController::post, setOf(InstagramRoles.ANYONE))
        }
        path("login"){
            post(loginController::post, setOf(InstagramRoles.ANYONE))
        }
        path("user"){
            get(userController::get, setOf(InstagramRoles.USER))
            path(":userId"){
                get(userController::getById, setOf(InstagramRoles.USER))
                path("follow"){
                    put(userController::toggleFollower, setOf(InstagramRoles.USER))
                }
            }
        }
        path("post"){
            path(":postId"){
                get(postController::getById,setOf(InstagramRoles.USER))
                path("like") {
                    put(postController::toggleLike,setOf(InstagramRoles.USER))
                }
                path("comment") {
                    post(postController::addComment,setOf(InstagramRoles.USER))
                }
            }

        }
        path("search"){
            get(searchController::get,setOf(InstagramRoles.ANYONE))
        }
    }
}


