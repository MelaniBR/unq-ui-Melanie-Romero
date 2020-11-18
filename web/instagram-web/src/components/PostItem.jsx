import React, {useEffect, useState, } from 'react';
import {post, like} from './Api.js';
import { Link } from 'react-router-dom';

const PostItem = (props) => {

    const portrait = props.post.portrait;
    const[likes, setLikes] = useState(props.post.likes.length);
    const[liked, setLiked] = useState(props.post.liked);
    const description = props.post.description;
    const userName = props.userName;
    const userImage = props.userImage;


    const handleLikeClick = (event) =>{
        event.preventDefault();

        like( props.post.id, props.auth.token )
            .then(response => {
                setLikes(response.data.likes.length);
                console.log(response.data.likes.length)
            })
            .catch(error => {
                console.log(error);
            })
    }
    
    return (
        <div class="card">
            <div
                className="card-header"> 
                <img src={userImage} class="rounded-circle" width="30px" height="30px"></img> {userName}
             </div>
            <div className="card-image">
            <Link to={{pathname: `/post/${props.post.id}`}}>
                <img alt="imagen del post" src = { portrait } class="img-responsive" width="100%" height="100%"></img>
            </Link>
            </div>
            <div className= "card-likes"> 
                <button onClick={handleLikeClick} >&hearts;</button>
                <h >{ likes } <b>Me gusta</b></h>
            </div>
            <div className= "card-description">
                <p>{ description }</p>
            </div>
        </div>
    )

}

export default PostItem;