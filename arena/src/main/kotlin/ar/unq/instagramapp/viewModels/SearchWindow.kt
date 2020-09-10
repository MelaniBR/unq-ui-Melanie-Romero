package ar.unq.instagramapp.viewModels;

import ar.unq.instagramapp.models.PostModel
import ar.unq.instagramapp.models.SearchModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner

class SearchWindow(owner: WindowOwner, model: SearchModel) : Window<SearchModel>(owner, model) {

    override fun createContents(mainPanel: Panel) {
        modelObject.loadMyPosts()

        title = "Mis Posts"
        this.setMinWidth(500)
        this.setMinHeight(300)

        Label(mainPanel) with{
            text = "Posts"
        }

        var searchInputPanel = Panel(mainPanel)
        searchInputPanel.layout = HorizontalLayout()

        Label(searchInputPanel) with {
            text = "Search: "
        }
        TextBox(searchInputPanel) with {
            bindTo("searchInput")
        }
        Button(searchInputPanel) with {
            caption = "Search"
            //Probablemente haya que cambiar esto
            onClick { modelObject.instagramSystem.searchByTag(modelObject.searchInput) }
        }
        //Tabla
        table<PostModel>(mainPanel) {
            bindItemsTo("searchResults")
            bindSelectionTo("selected")
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

            var buttonBar = Panel(mainPanel)
            buttonBar.layout = HorizontalLayout()
            Button(buttonBar) with{
                caption = "Add new post"
                onClick { showEditPostWindow() }
            }
            Button(buttonBar) with{
                caption = "Edit Post"
                onClick { showNewPostWindow() }
            }
            Button(buttonBar) with{
                caption = "Delete Post"
                onClick { showDeletePostWindow() }
            }
        }
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


}
