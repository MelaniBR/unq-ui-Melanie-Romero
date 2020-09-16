package ar.unq.instagramapp.viewModels.account

import ar.unq.instagramapp.models.LoginModel
import ar.unq.instagramapp.transformers.ErrorBackgroundLoginTransformer
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action


class LoginWindow(owner: WindowOwner, model: LoginModel) : Dialog<LoginModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Instagram - Iniciar Sesión"
        setMinWidth(300)

        ErrorsPanel(mainPanel, "Ingrese email y password")
        Label(mainPanel) with{
            text = "Email"
            alignLeft()
            setWidth(300)
        }
        TextBox(mainPanel) with{
            bindTo("email")
        }
        Label(mainPanel) with{
            text = "Contraseña"
            alignLeft()
            setWidth(300)
        }

        PasswordField(mainPanel) with {
            bindTo("password")
        }

        Label(mainPanel) with {
            bindBackgroundTo("error").setTransformer(ErrorBackgroundLoginTransformer())
            bindTo("mensaje")
            bindVisibleTo("error")
            alignLeft()
            setWidth(300)
        }

        Button(mainPanel) with {
            text = "Iniciar Sesión"
            onClick(Action {
                this@LoginWindow.modelObject.validateLogin()
                if(!this@LoginWindow.modelObject.error){
                    accept()
                }
            })
        }
        Button(mainPanel) with {
            text = "Cancelar"
            onClick(Action {
                cancel()
            })
        }
    }
}