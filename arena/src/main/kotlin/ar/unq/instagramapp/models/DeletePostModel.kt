package ar.unq.instagramapp.models;

import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class DeletePostModel(
    val instagramSystem : InstagramSystem,
    val postId: String) {

    fun deletePost() {
        try {
            instagramSystem.deletePost(postId)
        }
        catch (e: Exception){
        }
    }
}