package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.InstagramApp
import ar.unq.instagramapp.models.LoginModel
import ar.unq.instagramapp.transformers.ErrorBackgroundLoginTransformer
import org.unq.ui.model.NotFound
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action


class LoginWindow(owner: WindowOwner, model: LoginModel) : Window<LoginModel>(owner, model) {


    override fun createContents(mainPanel: Panel) {

        mainPanel.layout = VerticalLayout()

        ErrorsPanel(mainPanel, "Ingrese email y password")
        Label(mainPanel).text = "Email"
        TextBox(mainPanel) with{
            bindTo("email")
        }
        Label(mainPanel).text = "Password"
        PasswordField(mainPanel) with {
            bindTo("password")
        }

        Label(mainPanel) with {
            bindBackgroundTo("error").setTransformer(ErrorBackgroundLoginTransformer())
            bindTo("mensaje")
            bindVisibleTo("error")
        }

        Button(mainPanel) with {
            text = "Iniciar Sesión"
            onClick(Action {validateLogin()})
        }
    }

    private fun validateLogin(){
        // @Throw NotFound si `email` o `password` son incorrectos
        print(modelObject.email)
        print(modelObject.password)

        try {
            //InstagramApp.logInOk(modelObject.instagramSystem.login(modelObject.email, modelObject.password));
            modelObject.user = modelObject.instagramSystem.login(modelObject.email, modelObject.password)
            this.close()
        } catch (e: NotFound) {
            modelObject.error = true
            modelObject.mensaje = "Login incorrecto."
        }
    }
}