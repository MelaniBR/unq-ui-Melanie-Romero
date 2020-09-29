package ar.unq.instagramapp.viewModels.posts;

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
import java.awt.Color

class CreatePostWindow(owner: WindowOwner, model : DraftPostModel, val subTitle: String = "") : Dialog<DraftPostModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {

        title = "Instagram - ${subTitle}"
        setMinWidth(300)
        Label(mainPanel) with {
            text = "Descripcion"
            setWidth(300)
            alignLeft()
        }
        TextBox(mainPanel) with {
            bindTo("postDescription")
        }
        Label(mainPanel) with {
            text = "Paisaje"
            alignLeft()
        }
        TextBox(mainPanel) with {
            bindTo("postLandscape")
        }
        Label (mainPanel) with {
            bindToModel(modelObject.validationPostLandscape,"errorMessage")
            bindVisibleToModel(modelObject.validationPostLandscape,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }
        Label(mainPanel) with {
            text = "Retrato"
            alignLeft()
        }
        TextBox(mainPanel) with {
            bindTo("postPortrait")
        }
        Label (mainPanel) with {
            bindToModel(modelObject.validationPostPortrait,"errorMessage")
            bindVisibleToModel(modelObject.validationPostPortrait,"hasError")
            setWidth(300)
            background = Color.RED
            alignLeft()
        }
        var buttonPanel : Panel = Panel(mainPanel)
        buttonPanel.layout= HorizontalLayout()

        Button(buttonPanel) with {
            caption = "Aceptar"
            onClick {
                modelObject.validateCreatePostForm()
                if(modelObject.isValidCreatePostForm){
                    accept()
                }
            }
        }
        Button(buttonPanel) with {
            caption = "Cancelar"
            onClick { cancel() }
        }

        Label(mainPanel) with {
            bindVisibleTo("error")
            bindTo("errorMessage")
        }

    }

    fun displayError(message : String) {
        modelObject.error(message)
    }

}
