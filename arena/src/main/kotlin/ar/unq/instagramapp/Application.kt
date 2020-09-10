
package ar.unq.instagramapp
import ar.unq.instagramapp.models.ApplicationModel
import ar.unq.instagramapp.viewModels.ApplicationWindow
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

fun main(args: Array<String>) {
    InstagramApp.start()
}


object InstagramApp : Application(){


    private val instagramSystem: InstagramSystem = initInstagramSystem()
    var user : User? = null
    override fun createMainWindow(): Window<*> {
        return ApplicationWindow(this, ApplicationModel(instagramSystem))
    }
    private fun initInstagramSystem() : InstagramSystem{
        val instagramSystem: InstagramSystem = getInstagramSystem();
        instagramSystem.register("Jon Snow", "jon@snow.com", "ghost", "https://bit.ly/3496Vje")
        return instagramSystem;
    }

}
