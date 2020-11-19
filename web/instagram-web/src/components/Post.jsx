import React, {useState, useEffect} from 'react';
import { useParams } from "react-router-dom";
import {post, like, comment} from './Api.js';
import Swal from 'sweetalert2/dist/sweetalert2';

const Post = (props) => {

    const[postData, setPostData] = useState({
        landscape: "",
        likes: 0,
        liked: false,
        comments: [],
    });
    
    const[data, setData] = useState({
        newComment: '',
    });

    let validComment = false;

    let id = useParams().id;

    useEffect(() => {
        getPostData();
    }, [])

    const getPostData = () => {

        post(id, props.auth.token )
            .then(response => {
                setPostData({...postData,
                    landscape: response.data.landscape,
                    likes: response.data.likes.length,
                    liked: response.data.liked,
                    comments: response.data.comments
                })
            })
            .catch(error => {
                console.log(error)
            })
    }

    const handleLikeClick = (event) =>{
        event.preventDefault();

        like( id, props.auth.token )
            .then(response => {
                setPostData({...postData, likes: response.data.likes.length});
            })
            .catch(error => {
                console.log(error)
            })
    }

    const handleWriteComment = (event) => {
        event.preventDefault();
        Swal.fire({
            title: 'New comment',
            html: `<input type="text" id="login" class="swal2-input" placeholder="Username">
            <input type="password" id="password" class="swal2-input" placeholder="Password">`,


        })
    }

    const handleAddComment = (event) => {
        event.preventDefault();

        if(!data.newComment.replace(/\s/g, '')) {
            console.log("comentario no valido")
        } else{

        comment(data.newComment, id, props.auth.token)
            .then( response => {
               console.log("Se agrego el comentario correctamente") 
            })
            .catch(error => {
                console.log(error);
            })
        }

    }

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setData({...data, [name]: value });
    }
    
<img alt="imagen del post" src = { postData.landscape }></img>

    return (
        <div>
        <div className="Card">
            <img alt="postImage" src = { postData.landscape } className="img-responsive" width="100%" height="100%"></img>
            <div className= "card-likes"> 
                <button onClick={handleLikeClick} >&hearts;</button>
                <h >{ postData.likes } <b>likes</b></h>
            </div>
            <div className="card-description p-2">
                <p>{}</p>
            </div>
            <div className= "card-comments">
                <ul>
                    {postData.comments.map((comment)=>
                     <il>
                         <hr></hr>
                         <img alt="userImage" src = {comment.user.image}></img>
                         <h>{comment.user.name}</h>
                        <p>{comment.body}</p>
                     </il>)}
                </ul>
                <form onSubmit={handleAddComment}>
                        <button type="submit" className="btn-comment-submit rounded">Comment</button>
                        <input name="newComment" value={data.newComment} onChange={handleInputChange} disabled={validComment}></input>
                </form>
            </div>
        </div>
        </div>
    )

}

export default Post;