package ar.unq.instagramapp.viewModels


import ar.unq.instagramapp.IObserverInstagramApp
import ar.unq.instagramapp.InstagramApp
import ar.unq.instagramapp.models.ApplicationModel
import ar.unq.instagramapp.models.LoginModel
import ar.unq.instagramapp.models.PostModel
import ar.unq.instagramapp.models.SearchModel
import ar.unq.instagramapp.transformers.BooleanTransformer
import ar.unq.instagramapp.transformers.UserDataTransformer
import org.unq.ui.model.Post
import org.unq.ui.model.User
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.Layout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.lacar.ui.model.Action
import java.lang.reflect.Constructor

class ApplicationWindow(parent: WindowOwner, model: ApplicationModel): Window<ApplicationModel>(parent, model){

    override fun createContents(mainPanel: Panel) {

        title = "Example"

        Label(mainPanel) with {
            text = "Instagram"

        }
        Button(mainPanel) with{
            caption = "Ingresar"
            onClick(Action { showLoginWindow() })
            bindVisibleTo("loginOk").setTransformer(BooleanTransformer(true))
        }
        Button(mainPanel) with{
            caption = "Cerrar sesion"
            onClick(Action { logOut() })
            bindVisibleTo("loginOk")
        }
        Button(mainPanel) with{
            caption = "Ver mi perfil"
            onClick(Action { showProfileWindow() })
            bindVisibleTo("loginOk")
        }
        Button(mainPanel) with{
            caption = "Mis posteos"
            onClick(Action { showPostsWindow() })
            bindVisibleTo("loginOk")
        }


    }

    private fun showLoginWindow(){
        val loginModel =  LoginModel(modelObject.instagramSystem,"jon@snow.com", "ghost" )
        LoginWindow(this, loginModel).open() //aca se frena la ejecucion esperando que se cierre el modal
        if(loginModel.loginOk){
            logInOk(loginModel.user)
        }
    }

    private fun showProfileWindow(){
        //TODO crear ventana de edit profile y abrirla desde este metodo
    }

    private fun showPostsWindow(){
        SearchWindow(this, SearchModel(modelObject.instagramSystem)).open() //aca se frena la ejecucion esperando que se cierre el modal

    }


    fun logInOk(user : User?) {
        modelObject.loginOk = true
        modelObject.user = user
    }

    fun logOut(){
        modelObject.loginOk = false
        modelObject.user = null
    }

}
