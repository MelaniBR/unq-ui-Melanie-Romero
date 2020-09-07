
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


object InstagramApp : Application(), IObservableInstagramApp{

    override val observers: ArrayList<IObserverInstagramApp> = ArrayList<IObserverInstagramApp>()

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

    public fun logInOk(_user : User){
        user = _user;
        sendUpdateLoginEvent(_user.id, _user.name, _user.email);
    }

}

interface IObserverInstagramApp {
    fun logInOk(userId:String, userName:String, userEmail:String)
}

interface IObservableInstagramApp {
    val observers: ArrayList<IObserverInstagramApp>

    fun add(observer: IObserverInstagramApp) {
        observers.add(observer)
    }

    fun remove(observer: IObserverInstagramApp) {
        observers.remove(observer)
    }

    fun sendUpdateLoginEvent(userId:String, userName:String, userEmail:String) {
        observers.forEach { it.logInOk(userId, userName, userEmail) }
    }
}
