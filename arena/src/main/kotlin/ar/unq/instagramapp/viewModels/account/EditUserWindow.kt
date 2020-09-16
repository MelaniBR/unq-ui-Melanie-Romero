package ar.unq.instagramapp.viewModels.account

import ar.unq.instagramapp.models.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action



class EditUserWindow(owner: WindowOwner, userModel: UserModel) : Dialog<UserModel>(owner, userModel) {

// Arreglar lo de cancelar
    override fun createFormPanel(mainPanel: Panel?) {
        title = "Instagram - Editar Perfil"

        setMinWidth(300)
        Label(mainPanel) with {
            text = "Nombre: "
            alignLeft()
            setWidth(300)
        }
        TextBox(mainPanel) with {
            bindTo("name")
        }
        Label(mainPanel) with {
            text = "Imagen: "
            alignLeft()
        }
        TextBox(mainPanel) with {
            bindTo("image")
        }

        Button(mainPanel) with {
            caption = "Guardar Cambios"
            onClick(Action {
                accept()
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
        modelObject.validateUserName()
    }



}
