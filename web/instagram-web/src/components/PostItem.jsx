import React, {useEffect, useState, } from 'react';
import { like } from './Api.js';
import { Link } from 'react-router-dom';

const PostItem = (props) => {

    const portrait = props.post.portrait;
    const[likes, setLikes] = useState(props.post.likes.length);
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
        <div className="card mb-5">
            <div
                className="card-header"> 
                <img src={userImage} className="rounded-circle" width="30px" height="30px"></img> {userName}
             </div>
            <div className="card-image">
            <Link to={{pathname: `/post/${props.post.id}`}}>
                <img alt="imagen del post" src = { portrait } className="img-responsive" width="100%" height="100%"></img>
            </Link>
            </div>
            <div className= "card-body">
                <div className= "card-text">
                    <div className= "card-likes mb-2"> 
                        <button onClick={handleLikeClick} >&hearts;</button>
                        <>{ likes } <b>likes</b></>
                    </div>
                    <div className= "card-description p-2">
                        <p>{ description }</p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PostItem;