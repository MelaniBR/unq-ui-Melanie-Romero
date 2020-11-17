import React, {useEffect, useState, } from 'react';
import {post, like} from './Api.js';
import { Link } from 'react-router-dom';

const PostItem = (props) => {

    const portrait = props.post.portrait;
    const[likes, setLikes] = useState(props.post.likes.length);
    const[liked, setLiked] = useState(props.post.liked);

    const handleLikeClick = (event) =>{
        event.preventDefault();

        like( props.post.id, props.auth.token )
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
        <card>
            <div className="card-header"> </div>
            <Link to={{pathname: `/post/${props.post.id}`}}>
                <img alt="imagen del post" src = { portrait }></img>
            </Link>
            <div className= "card-likes"> 
            <button onClick={handleLikeClick} >&hearts;</button>
    <h >{ props.post.likes.length }</h>
              </div>
            <div className= "card-comments"> </div>
        </card>
    )

}

export default PostItem;