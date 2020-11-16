import React, { useEffect, useState } from 'react';
import { userById } from './Api';

export const Profile = (props) => {

  const[data, setData] = useState({});

  // In a function component:
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
      {data.posts.map(post => (
        <li key={post.id}>{post.description}</li>
      ))}
    </>
    
  )
}

export default Profile;