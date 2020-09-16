package ar.unq.instagramapp.viewModels.posts;

import ar.unq.instagramapp.models.DraftPostModel
import ar.unq.instagramapp.models.PostModel
import org.uqbar.arena.kotlin.extensions.with
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class OpenPostWindow(owner: WindowOwner, model : PostModel) : Dialog<PostModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {

        title = "Instagram - Post ${modelObject.postId}"
        setMinWidth(300)
        Label(mainPanel) with {
            text = "Descripcion"
            setWidth(300)
            alignLeft()
        }
        Label(mainPanel) with {
            bindTo("postDescription")
            alignLeft()
        }
        Label(mainPanel) with {
            text = "Paisaje"
            alignLeft()
        }
        Label(mainPanel) with {
            bindTo("postLandscape")
            alignLeft()
        }
        Label(mainPanel) with {
            text = "Retrato"
            alignLeft()
        }
        Label(mainPanel) with {
            bindTo("postPortrait")
            alignLeft()
        }

        Button(mainPanel) with {
            caption = "Cerrar"
            onClick { close() }
        }

    }

}
