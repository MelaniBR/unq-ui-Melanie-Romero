import React, { useEffect, useState } from 'react';
import { Link, NavLink, useParams } from 'react-router-dom';
import { userById, follow } from './Api';

export const User = (props) => {
  
  let id = useParams().id;

  const[data, setData] = useState({posts: [], followed: false});
  let followButton = "";
  
  useEffect(() => {
    userById(id, props.auth.token)
    .then(response => {
      console.log(props, response)
      setData(response.data);
      console.log(response.data.followed)

    })
    .catch(error => {
    });
  }, []);

  const handleFollow = () => {
    follow(id, props.auth.token)
    .then(response => {
      setData({...data, followed: (!data.followed)});
    })
  }

  return (
    <>
      <h1>User</h1>
      <div className="p-3 text-center">
        <img className="border border-primary rounded-circle d-inline mr-2" style={{width:"30px", height:"30px"}} src={data.image} />
        <span>{data.name} <br/>
  <button className="rounded btn-primary" onClick={handleFollow} >{data.followed ? "Unfollow" : "Follow"}</button>
        </span>
      </div>
      <div className="row">
      {data.posts.map(post => (
        <div key={post.id} className="col-6 col-md-4 col-lg-3 mb-4">
          <div className="card mx-auto text-center">
            <Link to={`/post/${post.id}`}><img className="card-img-top" src={post.landscape} alt="Sample Title"/></Link>
          </div>
        </div>
      ))}
      </div>
    </>
  )
  
}

export default User;