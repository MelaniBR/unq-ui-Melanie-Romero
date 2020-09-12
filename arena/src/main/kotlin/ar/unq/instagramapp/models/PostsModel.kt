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

@Observable
class DraftPostModel (
    var postUser : String,
    var postId : String = "",
    var postLandscape : String = "",
    var postPortrait : String = "",
    var postDescription : String = ""
) {
    fun fromPost(postM : PostModel) {
        postUser = postM.postUser
        postId = postM.postId
        postLandscape = postM.postLandscape
        postPortrait = postM.postPortrait
        postDescription = postM.postDescription
    }
}





