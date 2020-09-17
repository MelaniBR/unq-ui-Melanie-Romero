package ar.unq.instagramapp.models;

import ar.unq.instagramapp.custom.ModelWithSearch
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable
import scala.collection.mutable.`MutableList$`

@Observable
 class PostsListModel(
    val instagramSystem: InstagramSystem,
    var userId : String,
    var searchInput: String = "",
    var searchResults : List<PostModel> = listOf()
)  : ModelWithSearch{

    var selected : PostModel? = null
        set(value) {
            selectedCheck = true
            field = value
        }

    var selectedCheck = false

    init {
        loadMyPosts()
    }

    private fun updatePosts(posts : List<Post>) : List<PostModel> {
        return posts.map { PostModel(it.id, it.user.name, it.landscape, it.portrait, it.description) }
    }

    fun loadMyPosts() {
        searchResults = updatePosts(instagramSystem.searchByUserId(userId))
    }

    override fun search(text: String) {
        loadMyPosts();
        var result : MutableList<PostModel> = mutableListOf()
        for (post in searchResults) {
            if (post.postDescription.toUpperCase().contains(text.toUpperCase().trim())) {
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
    var postDescription : String = "",
    var errorMessage : String = "",
    var error : Boolean = false
) {
    fun fromPost(postM : PostModel) {
        postUser = postM.postUser
        postId = postM.postId
        postLandscape = postM.postLandscape
        postPortrait = postM.postPortrait
        postDescription = postM.postDescription
    }

    fun validateDraft() {
        if(postLandscape == "")
            throw Exception("El campo Paisaje no puede estar vacio")
        if(postPortrait == "")
            throw Exception("El campo Retrato no puede estar vacio")
    }

    fun error(s : String) {
        errorMessage = s
        error = true
    }
}





