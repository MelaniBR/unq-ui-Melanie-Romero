package ar.unq.instagramapp.viewModels

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
        title ="Change Password"

        Label(mainPanel).text = "Actual Password"
        PasswordField(mainPanel) with {
            bindTo("rePassword")
        }
        Label(mainPanel).text = "New Password"
        PasswordField(mainPanel) with {
            bindTo("newPassword")
        }
        Label(mainPanel) with {
            bindBackgroundTo("error").setTransformer(ErrorBackgroundLoginTransformer())
            bindTo("errorMessage")
            bindVisibleTo("error")
        }

        Button(mainPanel) with {
            caption = "Accept"
            onClick(Action {
                validateForm()
                if (!modelObject.error ){
                    accept()
                }
            })
        }
        Button(mainPanel) with {
            caption = "Cancel"
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