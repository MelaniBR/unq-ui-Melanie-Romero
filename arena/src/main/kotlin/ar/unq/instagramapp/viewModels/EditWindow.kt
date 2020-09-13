package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.models.EditModel
import ar.unq.instagramapp.models.UserModel
import ar.unq.instagramapp.transformers.ErrorBackgroundLoginTransformer
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.lang.Exception

// consultar el nombre
class EditWindow(owner: WindowOwner, model: EditModel) : Dialog<EditModel>(owner, model) {

// Agregar otros tipos de errores
    override fun createFormPanel(mainPanel: Panel?) {

        Label(mainPanel) with {
            text = "Name: "
        }
        TextBox(mainPanel) with {
            width = 200
            height = 75
            bindTo("name")
        }
        Label(mainPanel) with {
            text = "Image: "
        }
        TextBox(mainPanel) with {

            bindTo("imagen")
        }

        Label(mainPanel).text = "Actual Password"
        PasswordField(mainPanel) with {
            bindTo("actualPassword")
        }
        Label(mainPanel).text = "New Password"

        PasswordField(mainPanel) with {
            bindTo("nuevoPassword")
        }
        Label(mainPanel) with {
            bindBackgroundTo("error").setTransformer(ErrorBackgroundLoginTransformer())
            bindTo("mensaje")
            bindVisibleTo("error")
        }



        Button(mainPanel) with {
            caption = "Accept"
            onClick(Action {
                validatePassword()
                if (!modelObject.error ){
                accept()
                close()}
            })
        }


        Button(mainPanel) with {
            caption = "Cancel"
            onClick(Action {
                cancel()
            })
        }

    }

    private fun validatePassword() {
        try {
            //InstagramApp.logInOk(modelObject.instagramSystem.login(modelObject.email, modelObject.password));
            modelObject.cambiarContraseña()
            this.close()
        } catch (e: Exception) {
            modelObject.error = true
            modelObject.mensaje = "Contrseña Incorrecta."
        }


    }
}