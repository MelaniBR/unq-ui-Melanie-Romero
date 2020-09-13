package ar.unq.instagramapp.viewModels;

import ar.unq.instagramapp.models.DraftPostModel
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class CreatePostWindow(owner: WindowOwner, model : DraftPostModel) : Dialog<DraftPostModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {

        title = ""

        Label(mainPanel) with {
            text = "Paisaje"
        }
        TextBox(mainPanel) with {
            bindTo("postLandscape")
        }
        Label(mainPanel) with {
            text = "Retrato"
        }
        TextBox(mainPanel) with {
            bindTo("postPortrait")
        }
        Label(mainPanel) with {
            text = "Descripcion"
        }
        TextBox(mainPanel) with {
            bindTo("postDescription")
        }

        var buttonPanel : Panel = Panel(mainPanel)
        buttonPanel.layout= HorizontalLayout()

        Button(buttonPanel) with {
            caption = "Aceptar"
            onClick { accept() }
        }
        Button(buttonPanel) with {
            caption = "Cancelar"
            onClick { cancel() }
        }

    }

}
