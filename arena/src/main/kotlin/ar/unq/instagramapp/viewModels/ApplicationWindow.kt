package ar.unq.instagramapp.viewModels
import ar.unq.instagramapp.models.ApplicationModel
import ar.unq.instagramapp.models.LoginModel
import ar.unq.instagramapp.models.PostsListModel
import ar.unq.instagramapp.models.UserModel
import ar.unq.instagramapp.viewModels.account.LoginWindow
import ar.unq.instagramapp.viewModels.account.ProfileWindow
import ar.unq.instagramapp.viewModels.account.RegisterWindow
import ar.unq.instagramapp.viewModels.posts.PostsWindow
import org.unq.ui.model.User
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.Action

class ApplicationWindow(parent: WindowOwner, model: ApplicationModel): Window<ApplicationModel>(parent, model){

    override fun createContents(mainPanel: Panel) {

        title = "Instagram"

        Label(mainPanel) with {
            alignLeft()
            text = "Inicio"
        }

        setMinWidth(300)

        Button(mainPanel) with{
            setWidth(300)
            caption = "Ingresar"
            onClick(Action { showLoginWindow() })
            bindVisibleTo("notLogin")
        }

        Button(mainPanel) with{
            caption = "Crear una cuenta"
            onClick(Action {showRegisterWindow()})
            bindVisibleTo("notLogin")
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

        Button(mainPanel) with{
            caption = "Cerrar sesion"
            onClick(Action { logOut() })
            bindVisibleTo("loginOk")
        }

    }

    private fun showLoginWindow(){
        val loginModel =  LoginModel(modelObject.instagramSystem)
        LoginWindow(this, loginModel).open() //aca se frena la ejecucion esperando que se cierre el modal
        logInOk(loginModel.user)
    }

    private fun showRegisterWindow(){
        val userModel = UserModel(modelObject.instagramSystem, null)
        val view = RegisterWindow(this, userModel)
        view.onAccept(Action {
            modelObject.instagramSystem.register(
                userModel.name,
                userModel.email,
                userModel.password,
                userModel.image
            )
        })
        view.open()
    }

    private fun showProfileWindow(){
        ProfileWindow(
            this,
            UserModel(
                modelObject.instagramSystem,
                modelObject.user!!,
                modelObject.user!!.name,
                modelObject.user!!.email,
                modelObject.user!!.password,
                modelObject.user!!.image
            )
        ).open()
    }

    private fun showPostsWindow(){
        PostsWindow(
            this,
            PostsListModel(modelObject.instagramSystem, modelObject.user!!.id)
        ).open()
    }

    fun logInOk(user : User?) {
        modelObject.user = user
        modelObject.loginOk = user != null;
    }

    fun logOut(){
        modelObject.user = null
        modelObject.loginOk = false;
    }

}
