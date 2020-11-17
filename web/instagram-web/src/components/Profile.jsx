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
      <h1>Profile</h1>
      <div className="p-3 text-center">
        <img className="border border-primary rounded-circle d-inline mr-2" style={{width:"30px", height:"30px"}} src={data.image} />
        <span>{props.auth.email}</span>
      </div>
      <div className="row">
      {data.posts.map(post => (
        <div key={post.id} className="col-6 col-md-4 col-lg-3 mb-4">
          <div className="card mx-auto text-center">
            <NavLink to={`/post/${post.id}`}><img className="card-img-top" src={post.landscape} alt="Sample Title"/></NavLink>
          </div>
        </div>
      ))}
      </div>
    </>
  )
}

export default Profile;