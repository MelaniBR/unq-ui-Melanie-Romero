
package ar.unq.instagramapp
import ar.unq.instagramapp.models.ApplicationModel
import ar.unq.instagramapp.viewModels.ApplicationWindow
import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

fun main(args: Array<String>) {
    InstagramApp().start()
}


class InstagramApp() : Application(){
    override fun createMainWindow(): Window<*> {
        return ApplicationWindow(this, ApplicationModel(getInstagramSystem()))
    }
}
