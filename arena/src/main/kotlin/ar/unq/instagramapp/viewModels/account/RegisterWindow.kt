package ar.unq.instagramapp.viewModels.account

import ar.unq.instagramapp.models.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.awt.Color

class RegisterWindow(owner: WindowOwner, model: UserModel) : Dialog<UserModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Instagram - Registro"
        setMinWidth(300)
        Label (mainPanel) with {
            text = "Completa los datos para crear tu usuario"
            setWidth(300)
            alignLeft()
        }
        Label (mainPanel) with{
            text = "Nombre"
            alignLeft()
        }

        TextBox (mainPanel) with{
            bindTo("name")
        }
        Label (mainPanel) with {
            bindToModel(modelObject.validationUserName,"errorMessage")
            bindVisibleToModel(modelObject.validationUserName,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }
        Label (mainPanel) with{
            text = "Email"
            alignLeft()
        }
        TextBox (mainPanel) with{
            bindTo("email")
        }
        Label (mainPanel) with {
            bindToModel(modelObject.validationEmail,"errorMessage")
            bindVisibleToModel(modelObject.validationEmail,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }
        Label (mainPanel) with{
            text = "Contraseña:"
            alignLeft()
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
        Label (mainPanel) with{
            text = "Reingrese la contraseña"
            alignLeft()
        }
       PasswordField(mainPanel) with {
            bindTo("rePassword")
        }
        Label (mainPanel) with {
            bindToModel(modelObject.validationRePassword,"errorMessage")
            bindVisibleToModel(modelObject.validationRePassword,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }
        Label (mainPanel) with{
            text = "Imagen"
            alignLeft()
        }
        TextBox (mainPanel) with{
            bindTo("image")
        }

        Button (mainPanel) with {
            text = "Cancelar"
            onClick(Action {cancel()})
        }
        Button (mainPanel) with {
            text = "Aceptar"
            onClick(Action{
                modelObject.validateRegisterForm()
                if (modelObject.isValidRegisterForm){
                    accept()
                }
            })
        }


    }

}