import React, {useState, } from 'react';
import {post, like} from './Api.js';

const PostItem = (props, postData) => {

    const[portrait, setPortrait] = useState(postData.portrait);
    const[likes, setLikes] = useState(postData.likes.lenght);
    const[liked, setLiked] = useState(postData.liked);
    const id = postData.id;

    const getPostData = () => {

        post({ id })
            .then(response => {
                setPortrait(response.data.portrait);
                setLikes(response.data.likes.length);
                setLiked(response.data.like);
            })
            .catch(error => {
                console.log(error);
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
                console.log(error);
            })
    }

    const handleAddComment = (event) => {
        
    }
    
    return (
        <div class="card">
            <div class="card-header"> </div>
            <img alt="imagen del post" src = { portrait } > </img>
            <div class= "card-likes"> </div>
            <div class= "card-comments"> </div>
        </div>
    )

}
/*
<NavLink to={`/post/${id}`}>
<img alt="imagen del post" src = { portrait } > </img>
</NavLink>
*/
export default PostItem;