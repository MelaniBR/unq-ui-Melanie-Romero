package ar.unq.instagramapp.models;

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable
import scala.collection.mutable.`MutableList$`

@Observable
 class SearchModel(
    val instagramSystem: InstagramSystem,
    var userId : String,
    var searchInput: String = "",
    var searchResults : MutableList<PostModel> = mutableListOf(),
    var selected : PostModel? = null
) {


    //Se encarga de transformar los posts en post observables
    fun postList(posts : List<Post>) : MutableList<PostModel> {
       var result : MutableList<PostModel> = mutableListOf()
       for (post in posts) {
           result.add(PostModel(post))
       }
       return result
   }

    fun loadMyPosts() {
        searchResults = postList(instagramSystem.searchByUserId(userId))
    }

    fun search(input : String) {
        var result : MutableList<PostModel> = mutableListOf()
        for (post in searchResults) {
            if (post.postDescription.contains(input)) {
                result.add(post)
            }
        }
        searchResults = result
    }

}


@Observable
class PostModel( private val post: Post) {

    var postId : String = post.id
    var postUser : String = post.user.name
    var postLandscape : String = post.landscape
    var postPortrait : String = post.portrait
    var postDescription : String = post.description
}


