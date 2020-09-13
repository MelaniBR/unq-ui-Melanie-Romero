package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.models.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action



class EditUserWindow(owner: WindowOwner, userModel: UserModel) : Dialog<UserModel>(owner, userModel) {

// Arreglar lo de cancelar
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
            bindTo("image")
        }

        Button(mainPanel) with {
            caption = "Save Changes"
            onClick(Action {
                accept()
            })
        }

        Button(mainPanel) with {
            caption = "Cancel"
            onClick(Action {
                cancel()
            })
        }

    }



}
