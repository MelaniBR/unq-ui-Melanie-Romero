package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.models.RegisterModel
import ar.unq.instagramapp.transformers.ErrorBackgroundLoginTransformer
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.PasswordField
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner

class RegisterWindow(owner: WindowOwner, model: RegisterModel) : Window<RegisterModel>(owner, model) {
    override fun createContents(mainPanel: Panel?) {
        TODO("Not yet implemented")

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

    }

    private fun registerUser(){
        val user = modelObject.instagramSystem.register(modelObject.name, modelObject.email, modelObject.password, modelObject.imagen)
    }
}