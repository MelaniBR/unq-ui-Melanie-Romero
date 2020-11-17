import React, {useEffect, useState, } from 'react';
import {post, like} from './Api.js';
import { Link } from 'react-router-dom';

const PostItem = (props) => {

    const portrait = props.post.portrait;
    const[likes, setLikes] = useState(props.post.likes.lenght);
    const[liked, setLiked] = useState(props.post.liked);
    const id = props.post.id;

    const getPostData = () => {

        post( id, props.auth.token )
            .then(response => {
                setLikes(response.data.likes.length);
                setLiked(response.data.like);
            })
            .catch(error => {
                console.log(error);
            })
    }

    const handleLikeClick = (event) =>{
        event.preventDefault();
        
        getPostData();

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
    
    return (
        <div class="card">
            <div class="card-header"> </div>
            <Link to={{pathname: `/post/${id}`}}>
                <img alt="imagen del post" src = { portrait }></img>
            </Link>
            <div class= "card-likes"> </div>
            <div class= "card-comments"> </div>
        </div>
    )

}

export default PostItem;