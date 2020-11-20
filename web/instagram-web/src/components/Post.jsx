import React, {useState, useEffect} from 'react';
import { useParams } from "react-router-dom";
import {post, like, comment} from './Api.js';
import Swal from 'sweetalert2/dist/sweetalert2';

const Post = (props) => {

    const[postData, setPostData] = useState({
        landscape: "",
        portrait: "",
        likes: 0,
        liked: false,
        comments: [],
    });

    let id = useParams().id;

    useEffect(() => {
        getPostData();
    }, [])

    const getPostData = () => {

        post(id, props.auth.token )
            .then(response => {
                setPostData({...postData,
                    landscape: response.data.landscape,
                    portrait: response.data.portrait,
                    likes: response.data.likes.length,
                    liked: response.data.liked,
                    comments: response.data.comments
                })
            })
            .catch(error => {
                
            })
    }

    const handleLikeClick = (event) =>{
        event.preventDefault();

        like( id, props.auth.token )
            .then(response => {
                setPostData({...postData, likes: response.data.likes.length});
            })
            .catch(error => {

            })
    }

    const handleAddComment = (event) => {
        event.preventDefault();
        Swal.fire({
            title: 'New comment',
            html: `<input type="text" id="commentText" class="form-control" placeholder="your comment here">`,
            confirmButtonText: 'comment',
            preConfirm: () => {
                const commentText = Swal.getPopup().querySelector('#commentText').value
                if(!commentText || !commentText.replace(/\s/g, '')) {
                    Swal.showValidationMessage("Can't post an empty comment")
                }
                return { comment: commentText }
            }
        }).then((result) => {

            comment(result.value.comment, id, props.auth.token)
            .then( response => {
                setPostData({...postData, comments:response.data}) 
            })
            .catch(error => {
                
            })
        }).catch((error) => {})
    }
    
<img alt="imagen del post" src = { postData.landscape }></img>

    return (
        <div>
        <div className="card mb-5">
            <img alt="postImage" src = { !!postData.landscape ? postData.landscape : postData.portrait } className="img-responsive" width="100%" height="100%"></img>
            <div className="card-body">
                <div className="card-text">
                    <div className= "card-likes p-2"> 
                        <button onClick={handleLikeClick} >&hearts;</button>
                        <>{ postData.likes } <b>likes</b></>
                    </div>
                    <form className="p-2" onSubmit={handleAddComment}>
                                <button type="submit" className="btn-comment-submit btn-primary rounded">Comment</button>
                        </form>
                    <div className= "card-comments">
                        <ul>
                            {postData.comments.map((comment)=>
                             <div key={comment.id}>
                                 <hr></hr>
                                 <img className="rounded-circle" alt="userImage" src = {comment.user.image}></img>
                                 <b>{comment.user.name}</b>
                                <p>{comment.body}</p>
                            </div>)}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        </div>
    )

}

export default Post;