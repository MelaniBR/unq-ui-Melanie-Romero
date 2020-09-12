package ar.unq.instagramapp.viewModels;

import ar.unq.instagramapp.models.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner

class PostsWindow(owner: WindowOwner, model: PostsListModel) : Window<PostsListModel>(owner, model) {

    override fun createContents(mainPanel: Panel) {

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
            onClick { modelObject.search(modelObject.searchInput) }
        }
        Button(searchInputPanel) with {
            caption = "Clear"
            onClick { modelObject.loadMyPosts(); modelObject.searchInput = "" }
        }
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
                caption = "Add Post"
                onClick { showCreatePostWindow() }
            }
            Button(buttonBar) with{
                caption = "Edit Post"
                onClick { showEditPostWindow() }
            }
            Button(buttonBar) with{
                caption = "Delete Post"
                onClick { showDeletePostWindow() }
            }
        }
    }

    private fun showEditPostWindow() {
        CreatePostWindow(this, DraftPostModel(modelObject.userId)).open()
    }

    private fun showDeletePostWindow(){
        DeletePostWindow(this, DeletePostModel(modelObject.instagramSystem,modelObject.selected!!.postId)).open()
    }

    private fun showCreatePostWindow(){
        CreatePostWindow(this, DraftPostModel(modelObject.userId)).open()
    }


}
