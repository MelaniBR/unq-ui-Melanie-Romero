package ar.unq.instagramapp.viewModels;

import ar.unq.instagramapp.models.CreatePostModel
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner

class CreatePostWindow(owner: WindowOwner, model : CreatePostModel) : Window<CreatePostModel>(owner, model) {

    override fun createContents(mainPanel: Panel) {

        title = ""

        Label(mainPanel) with {
            text = "Landscape"
        }
        TextBox(mainPanel) with {
            bindTo("postLandscape")
        }
        Label(mainPanel) with {
            text = "Portrait"
        }
        TextBox(mainPanel) with {
            bindTo("postPortrait")
        }
        Label(mainPanel) with {
            text = "Description"
        }
        TextBox(mainPanel) with {
            bindTo("postDescription")
        }

        var buttonPanel : Panel = Panel(mainPanel)
        buttonPanel.layout= HorizontalLayout()

        Button(buttonPanel) with {
            caption = "Aceptar"
            onClick { aceptar() }
        }
        Button(buttonPanel) with {
            caption = "Cancelar"
            onClick { cancelar() }
        }

    }

    private fun aceptar() {
        //TODO cierra la vista y crea un nuevo Post en el sistema con los datos introducidos
    }

    private fun cancelar() {
        //Descarta los cambios y cierra la ventana
        close()
    }

}
