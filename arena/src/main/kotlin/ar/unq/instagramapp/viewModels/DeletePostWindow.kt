package ar.unq.instagramapp.viewModels;

import ar.unq.instagramapp.models.DeletePostModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner

class DeletePostWindow(owner: WindowOwner, model: DeletePostModel) : Window<DeletePostModel>(owner, model){

    override fun createContents(mainPanel: Panel) {

        var idPost : String = modelObject.postId

        title = "Eliminar Post"

        Label(mainPanel) with {
            text = "¿Esta seguro de que desea eliminar " + idPost + " ?"
        }

        var confirmationPanel : Panel = Panel(mainPanel)
        confirmationPanel.layout = HorizontalLayout()
        Button(confirmationPanel) with {
            caption = "Aceptar"
            onClick { aceptar() }
        }
        Button(confirmationPanel) with {
            caption = "Cancelar"
            onClick { cancelar() }
        }
    }

    private fun aceptar() {
        modelObject.deletePost()
        close()
    }

    private fun cancelar() {
        close()
    }

}
