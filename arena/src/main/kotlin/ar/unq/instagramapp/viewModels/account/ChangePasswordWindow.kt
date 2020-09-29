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
import java.awt.Color
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
            bindTo("currentPassword")
        }
        Label (mainPanel) with {
            bindToModel(modelObject.validationCurrentPassword,"errorMessage")
            bindVisibleToModel(modelObject.validationCurrentPassword,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }

        Label(mainPanel) with {
            text = "Nueva contraseña"
            alignLeft()
        }
        PasswordField(mainPanel) with {
            bindTo("newPassword")
        }
        Label (mainPanel) with {
            bindToModel(modelObject.validationNewPassword,"errorMessage")
            bindVisibleToModel(modelObject.validationNewPassword,"hasError")
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
            bindToModel(modelObject.validationReNewPassword,"errorMessage")
            bindVisibleToModel(modelObject.validationReNewPassword,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }

         Button(mainPanel) with {
            caption = "Aceptar"
            onClick(Action {
                modelObject.validateChangePasswordForm()
                if (modelObject.isValidChangePasswordForm){
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

}