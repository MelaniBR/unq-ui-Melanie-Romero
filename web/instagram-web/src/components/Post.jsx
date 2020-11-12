import React, {useState} from 'react';
import { useParams } from "react-router-dom";
import {post, like} from './Api.js';

const Post = (props) => {

    const[portrait, setPortrait] = useState("");
    const[likes, setLikes] = useState(0);
    const[liked, setLiked] = useState(false);
    const[comments, setComments] = useState([]);

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

    const handlePostClick = (event) =>{
      
      let { id } = useParams();

      post(id, props.auth.token)
      .then(response => {
        
      })
      .catch(error => {
        error: 'No se pudo acceder al post';
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
            <img alt="imagen del post" src = "" onClick={handlePostClick} > </img>
            <div class= "card-likes"> </div>
            <div class= "card-comments"> </div>
        </div>
    )

}

export default Post;