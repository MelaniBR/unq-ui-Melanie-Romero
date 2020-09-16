package ar.unq.instagramapp.viewModels.posts;

import ar.unq.instagramapp.custom.FilterBox
import ar.unq.instagramapp.models.*
import ar.unq.instagramapp.viewModels.posts.CreatePostWindow
import ar.unq.instagramapp.viewModels.posts.DeletePostWindow
import org.unq.ui.model.DraftPost
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class PostsWindow(owner: WindowOwner, model: PostsListModel) : Window<PostsListModel>(owner, model) {

    override fun createContents(mainPanel: Panel) {

        title = "Instagram - Mis Posts"
        this.setMinWidth(300)
        this.setMinHeight(300)

        Label(mainPanel) with{
            text = "Mis Posts"
        }

        var searchInputPanel = Panel(mainPanel)
        searchInputPanel.layout = HorizontalLayout()

        Label(searchInputPanel) with {
            text = "Buscar: "
        }

        FilterBox(searchInputPanel, modelObject) bindTo("searchInput")

        //TextBox(searchInputPanel) with {
        //    bindTo("searchInput")
        //}
        //Button(searchInputPanel) with {
        //    caption = "Buscar"
        //    onClick { modelObject.search() }
        //}
        Button(searchInputPanel) with {
            caption = "Limpiar"
            onClick { modelObject.loadMyPosts(); modelObject.searchInput = ""; modelObject.selectedCheck = false }
        }
        table<PostModel>(mainPanel) {
            bindItemsTo("searchResults")
            bindSelectionTo("selected")
            visibleRows = 10
            column {
                title = "#"
                bindContentsTo("postId")
            }
            column {
                title = "Descripcion"
                bindContentsTo("postDescription")
            }
            var buttonBar = Panel(mainPanel)
            buttonBar.layout = HorizontalLayout()
            Button(buttonBar) with{
                caption = "Nuevo Post"
                onClick { showCreatePostWindow() }
            }
            Button(buttonBar) with{
                caption = "Abrir Post"
                bindEnabledTo("selectedCheck")
                onClick { showOpenPostWindow() }
            }
            Button(buttonBar) with{
                caption = "Editar Post"
                bindEnabledTo("selectedCheck")
                onClick { showEditPostWindow() }
            }
            Button(buttonBar) with{
                caption = "Eliminar Post"
                bindEnabledTo("selectedCheck")
                onClick { showDeletePostWindow() }
            }

        }
    }

    private fun createDraftPost(draft : DraftPostModel) : DraftPost {
        return DraftPost(draft.postPortrait, draft.postLandscape, draft.postDescription)
    }

    private fun showEditPostWindow() {
        val draftPost = DraftPostModel(modelObject.userId)
        draftPost.fromPost(modelObject.selected!!)
        val view = CreatePostWindow(this@PostsWindow, draftPost, "Editar Post")
        view.onAccept {
            try {
                val post = createDraftPost(draftPost)
                modelObject.instagramSystem.editPost(modelObject.selected!!.postId, post)
            }
            catch (e : Exception) {
                throw UserException(e.message)
            }
            modelObject.loadMyPosts()
        }
        view.open()
    }

    private fun showOpenPostWindow() {
        OpenPostWindow(this@PostsWindow, modelObject.selected!!).open()
    }

    private fun showDeletePostWindow(){
        var view =
            DeletePostWindow(this, modelObject.selected!!)
        view.onAccept {
            try {
                modelObject.instagramSystem.deletePost(modelObject.selected!!.postId)
            }
            catch (e : Exception) {
                throw UserException(e.message)
            }
            modelObject.loadMyPosts()
        }
        view.open()
    }

    private fun showCreatePostWindow() {
        val draftPost = DraftPostModel(modelObject.userId)
        val view = CreatePostWindow(this@PostsWindow, draftPost, "Crear Post")
        view.onAccept {
            try {
                val post = createDraftPost(draftPost)
                modelObject.instagramSystem.addPost(modelObject.userId, post)
            } catch (e: Exception) {
                throw UserException(e.message)
            }
            modelObject.loadMyPosts()
        }
        view.open()
    }


}
