import React, {useState, useEffect} from 'react';
import { useParams } from "react-router-dom";
import {post, like, comment} from './Api.js';

const Post = (props) => {

    const[portrait, setPortrait] = useState("");
    const[likes, setLikes] = useState(0);
    const[liked, setLiked] = useState(false);
    const[comments, setComments] = useState([]);
    const[data, setData] = useState({
        newComment: '',
    });

    let id = useParams().id;

    useEffect(() => {
        getPostData();
    }, [])

    const getPostData = () => {

        post(id, props.auth.token )
            .then(response => {
                setPortrait(response.data.portrait);
                setLikes(response.data.likes.length);
                setLiked(response.data.like);
                setComments(response.data.comments)
            })
            .catch(error => {
                console.log(error)
            })
    }

    const handleLikeClick = (event) =>{
        event.preventDefault();

        like( id, props.auth.token )
            .then(response => {
                setLikes(response.data.likes.length);
            })
            .catch(error => {
                console.log(error)
            })
    }

    const handleAddComment = (event) => {
        event.preventDefault();

        comment(data.newComment, id, props.auth.token)
            .then( response => {
               console.log("Se agrego el comentario correctamente") 
            })
            .catch(error => {
                console.log(error);
            })

    }

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setData({...data, [name]: value });
    }
    
<img alt="imagen del post" src = { portrait }></img>

    return (
        <card>
            <div className="card-header"> </div>
            <img alt="postImage" src = { portrait }></img>
            <div className= "card-likes"> 
                <button onClick={handleLikeClick} >&hearts;</button>
                <h >{ likes } <b>Me gusta</b></h>
              </div>
            <div className= "card-comments">
                <ul>
                    {comments.map((comment)=>
                     <il>
                         <img alt="userImage" src = {comment.user.image}></img>
                         <h>{comment.user.name}</h>
                        <p>{comment.body}</p>
                     </il>)}
                </ul>
                <form onSubmit={handleAddComment}>
                        <button type="submit" className="btn-comment-submit">Comment</button>
                        <input name="newComment" value={data.newComment} onChange={handleInputChange}></input>
                </form>
            </div>
        </card>
    )

}

export default Post;