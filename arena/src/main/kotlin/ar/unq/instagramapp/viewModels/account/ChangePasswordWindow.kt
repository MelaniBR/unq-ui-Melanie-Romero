package ar.unq.instagramapp.viewModels.account

import ar.unq.instagramapp.models.UserModel
import ar.unq.instagramapp.transformers.ErrorBackgroundLoginTransformer
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.PasswordField
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.lang.Exception

class ChangePasswordWindow(owner: WindowOwner, userModel: UserModel) : Dialog<UserModel>(owner, userModel) {

    override fun createFormPanel(mainPanel: Panel) {


        this.setMinWidth(300)
        title = "Instagram - Modificar Contraseña"

        Label(mainPanel) with {
            text = "Contraseña actual"
            alignLeft()
            setWidth(300)
        }

        PasswordField(mainPanel) with {
            bindTo("rePassword")
        }

        Label(mainPanel) with {
            text = "Nueva contraseña"
            alignLeft()
        }

        PasswordField(mainPanel) with {
            bindTo("newPassword")
        }

        Label(mainPanel) with {
            bindBackgroundTo("error").setTransformer(ErrorBackgroundLoginTransformer())
            bindTo("errorMessage")
            bindVisibleTo("error")
        }

        Button(mainPanel) with {
            caption = "Aceptar"
            onClick(Action {
                validateForm()
                if (!modelObject.error){
                    accept()
                }
            })
        }
        Button(mainPanel) with {
            caption = "Cancelar"
            onClick(Action {
                cancel()
            })
        }
    }
    private fun validateForm() {
        modelObject.resetValidation()
        modelObject.validatePassword()
        if (modelObject.error) return
        modelObject.validateNewPassword()
    }
}