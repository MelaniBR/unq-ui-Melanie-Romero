package ar.unq.instagramapp.viewModels


import ar.unq.instagramapp.IObserverInstagramApp
import ar.unq.instagramapp.InstagramApp
import ar.unq.instagramapp.models.ApplicationModel
import ar.unq.instagramapp.models.LoginModel
import ar.unq.instagramapp.models.PostModel
import ar.unq.instagramapp.models.SearchModel
import ar.unq.instagramapp.transformers.LoginOkIngresarTransformer
import ar.unq.instagramapp.transformers.UserDataTransformer
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class ApplicationWindow: Window<ApplicationModel>,
    IObserverInstagramApp {
    constructor(parent: WindowOwner, model: ApplicationModel): super(parent, model){
        InstagramApp.add(this)
    }
    override fun createContents(mainPanel: Panel) {

        title = "Example"
        this.setMinWidth(500)
        this.setMinHeight(300)

        Label(mainPanel) with {
            text = "Instagram"
        }
        Button(mainPanel) with{
            caption = "Ingresar"
            onClick(Action { showLoginWindow() })
            bindVisibleTo("loginOk").setTransformer(LoginOkIngresarTransformer())
        }
        Button(mainPanel) with{
            caption = "Cerrar sesion"
            onClick(Action { logOut() })
            bindVisibleTo("loginOk")
        }
        Label(mainPanel) with{
            text = "Bienvenido"
            bindVisibleTo("loginOk")
        }
        Label(mainPanel) with{
            bindTo("userId").setTransformer(UserDataTransformer("Id: "))
            bindVisibleTo("loginOk")
        }
        Label(mainPanel) with{
            bindTo("userName").setTransformer(UserDataTransformer("Name: "))
            bindVisibleTo("loginOk")
        }
        Label(mainPanel) with{
            bindTo("userEmail").setTransformer(UserDataTransformer("Email: "))
            bindVisibleTo("loginOk")
        }
        Button(mainPanel) with{
            caption = "Edit profile"
            onClick(Action { showEditProfileWindow() })
            bindVisibleTo("loginOk")
        }
        Label(mainPanel) with{
            text = "------------------------------"
            bindVisibleTo("loginOk")
        }
        var searchPanel = Panel(mainPanel)
        searchPanel.setLayout(HorizontalLayout())

        Label(searchPanel) with {
            text = "Search: "
            bindVisibleTo("loginOk")
        }
        TextBox(searchPanel) with {
            bindTo("searchInput")
            bindVisibleTo("loginOk")
        }
        Button(searchPanel) with {
            caption = "Search"
            bindVisibleTo("loginOk")
        }

        val postTable : Table<PostModel> = table<PostModel>(mainPanel) {
            bindItemsTo("searchResult")
            bindSelectionTo("selectedPost")
            bindVisibleTo("loginOk")
            visibleRows = 5
            column {
                title = "#"
                bindContentsTo("postId")
            }
            column {
                title = "Landscape"
                bindContentsTo("postLandscape")
            }
            column {
                title = "Portrait"
                bindContentsTo("postPortrait")
            }
        }

        var buttonPanel = Panel(mainPanel)
        buttonPanel.layout = HorizontalLayout()

        Button(buttonPanel) with{
            caption = "Add new post"
            onClick { showEditPostWindow() }
            bindVisibleTo("loginOk")
        }
        Button(buttonPanel) with{
            caption = "Edit Post"
            onClick { showNewPostWindow() }
            bindVisibleTo("loginOk")
        }
        Button(buttonPanel) with{
            caption = "Delete Post"
            onClick { showDeletePostWindow() }
            bindVisibleTo("loginOk")
        }
    }

    private fun showLoginWindow(){
        LoginWindow(this, LoginModel(modelObject.instagramSystem,"jon@snow.com", "ghost" ) ).open()
    }

    private fun showEditProfileWindow(){
        //TODO crear ventana de edit profile y abrirla desde este metodo
    }

    private fun showEditPostWindow(){
        //TODO crear ventana de edit post y abrirla desde este metodo
    }

    private fun showDeletePostWindow(){
        //TODO crear ventana de delete post y abrirla desde este metodo
    }

    private fun showNewPostWindow(){
        //TODO crear ventana de new post y abrirla desde este metodo
    }



    override fun logInOk(userId:String, userName:String, userEmail:String) {
        modelObject.loginOk = true
        modelObject.userName = userName
        modelObject.userId = userId
        modelObject.userEmail = userEmail
    }

    fun logOut(){
        modelObject.loginOk = false;
        modelObject.userName = ""
        modelObject.userId = ""
        modelObject.userEmail = ""
    }

}