package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.models.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class RegisterWindow(owner: WindowOwner, model: UserModel) : Dialog<UserModel>(owner, model) {

    private fun registerUser(){
        val user = modelObject.instagramSystem.register(modelObject.name, modelObject.email, modelObject.password, modelObject.imagen)
    }

    override fun createFormPanel(mainPanel: Panel?) {

        Label (mainPanel) with {
            text = "Registro"
        }
        Label (mainPanel) with{
            text = "Nombre:"
        }
        TextBox (mainPanel) with{
            bindTo("name")
        }
        Label (mainPanel) with{
            text = "Email:"
        }
        TextBox (mainPanel) with{
            bindTo("email")
        }
        Label (mainPanel) with{
            text = "Password:"
        }
        PasswordField(mainPanel) with {
            bindTo("password")
        }
        Label (mainPanel) with{
            text = "Reingresa el password:"
        }
        PasswordField(mainPanel) with {
            bindTo("rePassword")
        }
        Label (mainPanel) with{
            text = "Imagen:"
        }
        TextBox (mainPanel) with{
            bindTo("imagen")
        }
        Button (mainPanel) with {
            text = "Cancelar"
            onClick(Action {this@RegisterWindow.close()})
        }
        Button (mainPanel) with {
            text = "Enviar"
            onClick(Action{registerUser()})
        }
    }
}