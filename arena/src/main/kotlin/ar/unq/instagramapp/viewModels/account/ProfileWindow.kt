package ar.unq.instagramapp.viewModels.account

import ar.unq.instagramapp.models.UserModel
import ar.unq.instagramapp.viewModels.account.ChangePasswordWindow
import ar.unq.instagramapp.viewModels.account.EditUserWindow
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
        title = "Instagram - Perfil de Usuario"
        setMinWidth(300)
        Label(mainPanel) with {
            text = "Id :"
            alignLeft()
            setWidth(300)
        }
        Label(mainPanel) with {
            bindTo("userId")
            alignLeft()
            fontSize = 13
        }
        Label(mainPanel) with {
            text = "Name :"
            alignLeft()
        }
        Label(mainPanel) with {
           bindTo("name")
            alignLeft()
            fontSize = 13
        }
        Label(mainPanel) with {
            text = "Email :"
            alignLeft()
        }
        Label(mainPanel) with {
            bindTo("email")
            alignLeft()
            fontSize = 13
        }
        Button(mainPanel) with{
            caption = "Editar Perfil"
            onClick(Action {
                showEditUserWindow()
            })
        }
        Button(mainPanel) with{
            caption = "Modificar Contraseña"
            onClick(Action {
                showChangePasswordWindow()
            })
        }
        Button(mainPanel) with{
            caption = "Cerrar"
            onClick(Action {
                close()
            })
        }
    }
    private fun showEditUserWindow(){
        val model = modelObject.copy()
        val view = EditUserWindow(this, model)
        view.onAccept {
            modelObject.editUser(model)
        }
        view.open()
    }

    private fun showChangePasswordWindow(){
        val model = modelObject.copy()
        val view = ChangePasswordWindow(this, model)
        view.onAccept {
            modelObject.editPasswordUser(model)
        }
        view.open()
    }
}
