package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.models.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class RegisterWindow(owner: WindowOwner, model: UserModel) : Dialog<UserModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel?) {

        Label (mainPanel) with {
            text = "Register"
        }
        Label (mainPanel) with{
            text = "Name:"
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
            text = "Re write password:"
        }
        PasswordField(mainPanel) with {
            bindTo("rePassword")
        }
        Label (mainPanel) with{
            text = "Image:"
        }
        TextBox (mainPanel) with{
            bindTo("image")
        }
        Button (mainPanel) with {
            text = "Cancel"
            onClick(Action {cancel()})
        }
        Button (mainPanel) with {
            text = "Accept"
            onClick(Action{
                validateForm()
                if (!modelObject.error){
                    accept()
                }
            })
        }


    }

    private fun validateForm(){
        modelObject.validatePassword()
        modelObject.validateUserName()
    }
}