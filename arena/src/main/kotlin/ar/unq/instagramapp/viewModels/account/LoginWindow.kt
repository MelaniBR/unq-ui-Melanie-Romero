package ar.unq.instagramapp.viewModels.account

import ar.unq.instagramapp.models.LoginModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color


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
        Label (mainPanel) with {
            bindToModel(modelObject.validationEmail,"errorMessage")
            bindVisibleToModel(modelObject.validationEmail,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }
        Label(mainPanel) with{
            text = "Contraseña"
            alignLeft()
            setWidth(300)
        }

        PasswordField(mainPanel) with {
            bindTo("password")
        }

        Label (mainPanel) with {
            bindToModel(modelObject.validationPassword,"errorMessage")
            bindVisibleToModel(modelObject.validationPassword,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }

        Label(mainPanel) with {
            bindToModel(modelObject.validationLogin, "errorMessage")
            bindVisibleToModel(modelObject.validationLogin, "hasError")
            background = Color.RED
            alignLeft()
            setWidth(300)
        }

        Button(mainPanel) with {
            text = "Iniciar Sesión"
            onClick(Action {
                modelObject.validateLoginForm()
                if(modelObject.isValidLoginForm){
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