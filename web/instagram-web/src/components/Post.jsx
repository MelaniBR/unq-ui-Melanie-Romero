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

    let id = useParams();

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
                if( liked ) {
                  console.log("Se agrego un like al post")
                    
                } else {
                    console.log("Se quito un like al post")
                }
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
    
    return (
        <div>
                <img alt="imagen del post" src = { portrait }></img>
                <form>

                </form> 
        </div>
    )

}

export default Post;