import React, {useState} from 'react';
import {post, like} from './Api.js';

const Post = (props, post) => {

    const[portrait, setPortrait] = useState(post.portrait);
    const[likes, setLikes] = useState(post.likes.lenght);
    const[liked, setLiked] = useState(post.liked);
    const[comments, setComments] = useState(post.comments);
    const id = post.id;

    const getPostData = () => {

        post({ id })
            .then(response => {
                setPortrait(response.data.portrait);
                setLikes(response.data.likes.length);
                setLiked(response.data.like);
                setComments(response.data.comments)
            })
            .catch(error => {
                error: 'No se pudo acceder a los datos del post'
            })
    }

    const handleLikeClick = (event) =>{
        event.preventDefault();
        

        like( id, props.auth.token )
            .then(response => {
                if( !liked ) {
                  console.log("Se agrego un like al post")
                    
                } else {
                    console.log("Se quito un like al post")
                }
                setLikes(response.data.likes.length);
            })
            .catch(error => {
                error: 'Aca iria un error al apretar like';
            })
    }

    const handleAddComment = (event) => {
        
    }
    
    return (
        <div class="card">
            <div class="card-header"> </div>
            <NavLink to={`/post/${id}`}>
                <img alt="imagen del post" src = { portrait } > </img>
            </NavLink>
            <div class= "card-likes"> </div>
            <div class= "card-comments"> </div>
        </div>
    )

}

export default Post;