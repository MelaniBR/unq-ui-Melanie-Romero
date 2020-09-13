package ar.unq.instagramapp.viewModels
import ar.unq.instagramapp.models.ApplicationModel
import ar.unq.instagramapp.models.LoginModel
import ar.unq.instagramapp.models.PostsListModel
import ar.unq.instagramapp.models.UserModel
import ar.unq.instagramapp.transformers.BooleanTransformer
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
            bindVisibleTo("loginOk").setTransformer(BooleanTransformer(true))
        }
        Button(mainPanel) with{
            caption = "Crear una cuenta"
            onClick(Action {showRegisterWindow()})
            bindVisibleTo("loginOk").setTransformer(BooleanTransformer(true))
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
        val loginModel =  LoginModel(modelObject.instagramSystem,"jania@gmail.com", "jania" )
        LoginWindow(this, loginModel).open() //aca se frena la ejecucion esperando que se cierre el modal
        logInOk(loginModel.user)
    }

    private fun showRegisterWindow(){
        val userModel = UserModel(modelObject.instagramSystem)
        RegisterWindow(this, userModel).open()
        try {
            modelObject.instagramSystem.register(
                userModel.name,
                userModel.email,
                userModel.password,
                userModel.image
            )
        }catch (e : UserException){

        }
    }

    private fun showProfileWindow(){
        ProfileWindow(this, UserModel(modelObject.instagramSystem, modelObject.user!!.id, modelObject.user!!.name,modelObject.user!!.email,modelObject.user!!.password, modelObject.user!!.image) ).open()
    }

    private fun showPostsWindow(){

        PostsWindow(this, PostsListModel(modelObject.instagramSystem, modelObject.user!!.id )).open()
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
