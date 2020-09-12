package ar.unq.instagramapp.models;

import org.unq.ui.model.DraftPost
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class CreatePostModel(

    val instagramSystem: InstagramSystem,
    val postUser : String,
    var draftModel : DraftPostModel
) {

    fun createPost() {
        if (draftModel.postId == "") {
            crear()
        }
        else {
            editar()
        }
    }

    private fun crear() {
        var draft = DraftPost(draftModel.postPortrait, draftModel.postLandscape, draftModel.postDescription)
        instagramSystem.addPost(postUser, draft)
    }

    private fun editar() {
        var draft = DraftPost(draftModel.postPortrait, draftModel.postLandscape, draftModel.postDescription)
        instagramSystem.editPost(draftModel.postId, draft)
    }
}


