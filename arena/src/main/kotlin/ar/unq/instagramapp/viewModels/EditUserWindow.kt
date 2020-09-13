package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.model.EditUserModel
import ar.unq.instagramapp.transformers.ErrorBackgroundLoginTransformer
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action
import java.lang.Exception

// consultar el nombre
class EditUserWindow(owner: WindowOwner, userEditModel: EditUserModel) : Dialog<EditUserModel>(owner, userEditModel) {

// Agregar otros tipos de errores
// que se pueda editar otras propiedas sin convertir
    override fun createFormPanel(mainPanel: Panel?) {
    title = "Edit Profile"

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

        Button(mainPanel) with {
        caption = "Change Password "
        onClick(Action {
            showChangePasswordWindow()
        })
    }

        Button(mainPanel) with {
            caption = "Save Changes"
            onClick(Action {
                accept()
                close()
            })
        }


        Button(mainPanel) with {
            caption = "Cancel"
            onClick(Action {
                cancel()
            })
        }

    }


    private fun showChangePasswordWindow(){
        ChangePasswordWindow(this,modelObject ).open()

    }

}
