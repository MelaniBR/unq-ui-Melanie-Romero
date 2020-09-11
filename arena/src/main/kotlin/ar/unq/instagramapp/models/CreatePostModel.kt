package ar.unq.instagramapp.models;

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable

@Observable
class CreatePostModel(

    val instagramSystem: InstagramSystem,
    var postLandscape: String = "",
    var postPortrait: String = "",
    var postDescription: String = ""
) {

    fun editPost(landscape : String, portrait : String, description : String) {
        postLandscape = landscape
        postPortrait = portrait
        postDescription = description
    }
}


