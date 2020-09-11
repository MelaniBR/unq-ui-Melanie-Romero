package ar.unq.instagramapp.models;

import org.unq.ui.model.DraftPost
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable

@Observable
class CreatePostModel(

    val instagramSystem: InstagramSystem,
    var userId : String,
    var postLandscape: String = "",
    var postPortrait: String = "",
    var postDescription: String = "",
    var draft : DraftPost = DraftPost(postLandscape, postPortrait, postDescription)
) {

    fun createPost() {
        draft = DraftPost(postLandscape, postPortrait, postDescription)
        instagramSystem.addPost(userId, draft)
    }
}


