package ar.unq.instagramapp.models;

import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.Post
import org.uqbar.commons.model.annotations.Observable

@Observable
 class SearchModel(
    val instagramSystem: InstagramSystem,
    var searchInput: String = "",
    var selectedPost : String = "",
    var searchResults : List<Post> = listOf()

)

@Observable
class PostModel(
    var postId : String = "",
    var postUser : String = "",
    var postLandscape : String = "",
    var postPortrait : String = "",
    var postDescription : String = ""
)
