import React, {useEffect, useState, } from 'react';
import { like } from './Api.js';
import { Link } from 'react-router-dom';
import { ErrorMessage } from './Error.js';

const PostItem = (props) => {

    const portrait = props.post.portrait;
    const[likes, setLikes] = useState(props.post.likes.length);
    const[liked, setLiked] = useState(props.post.liked)
    const description = props.post.description;
    const userName = props.userName;
    const userImage = props.userImage;


    const handleLikeClick = (event) =>{
        event.preventDefault();

        like( props.post.id, props.auth.token )
            .then(response => {
                setLikes(response.data.likes.length);
                setLiked(response.data.like)
            })
            .catch(error => {
              ErrorMessage();
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
                <div className= "card-likes p-2"> 
                      
                      { liked ? 
                        <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-suit-heart-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                          <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/>
                        </svg>                    
                      : 
                        <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-heart" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                          <path fillRule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                        </svg>
                      }
                      <span className="ml-2">{likes} { likes == 1 ? "like" : "likes"} </span>
                    </div>
                    
                    <button className="btn btn-primary m-1" onClick={handleLikeClick} >{ liked ? "Unlike" : "Like"} </button>
                    
                    <div className= "card-description p-2">
                        <p>{ description }</p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PostItem;