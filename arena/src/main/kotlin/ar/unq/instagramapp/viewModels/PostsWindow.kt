package ar.unq.instagramapp.viewModels;

import ar.unq.instagramapp.models.*
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
            onClick { modelObject.loadMyPosts(); modelObject.searchInput = ""; modelObject.selectedCheck = false }
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
            column {
                title = "Description"
                bindContentsTo("postDescription")
            }

            var buttonBar = Panel(mainPanel)
            buttonBar.layout = HorizontalLayout()
            Button(buttonBar) with{
                caption = "Add Post"
                onClick { showCreatePostWindow() }
            }
            Button(buttonBar) with{
                caption = "Edit Post"
                bindEnabledTo("selectedCheck")
                onClick { showEditPostWindow() }
            }
            Button(buttonBar) with{
                caption = "Delete Post"
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
        val view = CreatePostWindow(this@PostsWindow, draftPost)
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



    private fun showDeletePostWindow(){
        var view = DeletePostWindow(this, modelObject.selected!!)
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
        val view = CreatePostWindow(this@PostsWindow, draftPost)
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
