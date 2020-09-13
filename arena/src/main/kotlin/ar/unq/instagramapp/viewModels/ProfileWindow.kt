package ar.unq.instagramapp.viewModels

import ar.unq.instagramapp.model.EditUserModel
import ar.unq.instagramapp.models.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class ProfileWindow(owner: WindowOwner, model: UserModel) : Window<UserModel>(owner, model) {
    override fun createContents(mainPanel: Panel) {
        mainPanel.layout = VerticalLayout()

        Label(mainPanel) with {
            text = "Id :"
            alignLeft()

        }

        Label(mainPanel) with {
            bindTo("userId")
        }
        Label(mainPanel) with {
            text = "Name :"
            alignLeft()
        }
        Label(mainPanel) with {
           bindTo("name")
        }
        Label(mainPanel) with {
            text = "Email :"
            alignLeft()

        }

        Label(mainPanel) with {
            bindTo("email")
            alignRight()

        }
        Button(mainPanel) with{
            caption = "Editar"
            onClick(Action { showEditarWindow()

            })
        }
    }
    private fun showEditarWindow(){
        val userEditModel = EditUserModel(modelObject.userId, modelObject.password)
        val view = EditUserWindow(this, userEditModel)
        view.onAccept {
                  modelObject.editUser(userEditModel)

        }

        view.open()
        }
    }
