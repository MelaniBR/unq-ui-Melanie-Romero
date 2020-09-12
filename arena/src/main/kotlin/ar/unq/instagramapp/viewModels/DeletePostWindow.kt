package ar.unq.instagramapp.viewModels;

import ar.unq.instagramapp.models.PostModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner

class DeletePostWindow(owner: WindowOwner, model: PostModel) : Dialog<PostModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {

        title = "Eliminar Post"

        Label(mainPanel) with {
            text = "¿Esta seguro de que desea eliminar " + modelObject.postId + " ?"
        }

        var confirmationPanel : Panel = Panel(mainPanel)
        confirmationPanel.layout = HorizontalLayout()
        Button(confirmationPanel) with {
            caption = "Aceptar"
            onClick { accept() }
        }
        Button(confirmationPanel) with {
            caption = "Cancelar"
            onClick { cancel() }
        }
    }

}
