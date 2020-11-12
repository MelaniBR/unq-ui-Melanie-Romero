import React, {useState} from 'react';

export const Post = () => {

    const[portrait, setPortrait] = useState("");
    const[likes, setLikes] = useState([]);
    const[comments, setComments] = useState([]);

    const getPostData = () => {
        post({ Data: {id} })
            .then(response => {
                setPortrait(response.data.portrait);
                setLikes(response.data.likes);
                setComments(response.data.comments)
            })
            .catch(error => {
                error: 'No se pudo acceder a los datos del post'
            })
    }

    const handlePostClick = (event) =>{
       post({  })
    }

    const handleLikeClick = (event) =>{
        event.preventDefault();

        like( { Id: {id : 1} })
            .then(response => {
                const newLike = { User: {name : 'Juan'}}
                const index = likes.indexOf(newLike)
                if( index == -1 ) {
                    console.log("Se agrego un like al post")
                    setLikes(likes => [...likes, newLike])
                } else {
                    console.log("Se quito un like al post")
                    var array = [...likes];
                    array.splice(index,1);
                    setLikes({likes : array});
                }
            })
            .catch(error => {
                error: 'Aca iria un error al apretar like'
            })
    }

    const handleAddComment = (event) => {
        
    }
    
    return (
        <div class="card">
            <div class="card-header"> </div>
            <img alt="imagen del post" src = "" onClick={handlePostClick} > </img>
            <div class= "card-likes"> </div>
            <div class= "card-comments"> </div>
        </div>
    )

}