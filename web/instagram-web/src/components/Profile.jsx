import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { userById } from './Api';

export const Profile = (props) => {

  const[data, setData] = useState({posts: []});

  useEffect(() => {
    userById(props.auth.id, props.auth.token)
    .then(response => {
      console.log(props, response)
      setData(response.data);
    })
    .catch(error => {
      console.log(error)
    });
  }, []);

  return (
    <>
      <h1>{props.auth.name}</h1>
      <img src={data.image} />
      <div class="row">
      {data.posts.map(post => (
        <div class="col-6 col-md-4 col-lg-3 mb-4">
          <div class="card mx-auto text-center">
            <NavLink to={`/post/${post.id}`}><img class="card-img-top" src={post.landscape} alt="Sample Title"/></NavLink>
          </div>
        </div>
      ))}
      </div>
    </>
    
  )
}

export default Profile;