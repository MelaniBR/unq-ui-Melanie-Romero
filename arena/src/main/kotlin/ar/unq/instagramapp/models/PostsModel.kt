package ar.unq.instagramapp.models;

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable
import scala.collection.mutable.`MutableList$`

@Observable
 class PostsListModel(
    val instagramSystem: InstagramSystem,
    var userId : String,
    var searchInput: String = "",
    var searchResults : List<PostModel> = listOf(),
    var selected : PostModel? = null
) {

    init {
        loadMyPosts()
    }

    private fun updatePosts(posts : List<Post>) : List<PostModel> {
        return posts.map { PostModel(it.id, it.user.name, it.landscape, it.portrait, it.description) }
    }

    fun loadMyPosts() {
        searchResults = updatePosts(instagramSystem.searchByUserId(userId))
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
class PostModel (
    var postId : String,
    var postUser : String,
    var postLandscape : String,
    var postPortrait : String,
    var postDescription : String
)

/*
@Observable
class PostModel( private val post: Post) {

    var postId : String = post.id
    var postUser : String = post.user.name
    var postLandscape : String = post.landscape
    var postPortrait : String = post.portrait
    var postDescription : String = post.description
}
 */


