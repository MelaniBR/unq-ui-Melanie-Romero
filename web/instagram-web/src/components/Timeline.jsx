
import React, { useState, useEffect } from 'react';
import { user } from './Api.js';
import PostItem from './PostItem.jsx';
import { Link } from 'react-router-dom';


const Timeline = (props) => {

  const[userData, setUserData] = useState({
    name: "",
    image: "",
    timeline: [],
    followers : []
  });

  useEffect(() => {
    getUserData();
}, [])

  const getUserData = () => {
    user( props.auth.token )
      .then(response => {
        setUserData(response.data);
      })
  }


  function renderPostItemList() {
    return (
    <div>
      {userData.timeline.map((post) => 
        <PostItem  post={post} auth={props.auth.token}/>
      )}
    </div>
      )
  }

  const renderFollowers = () => {
    return (
      <div>
        {userData.followers.map((follower) =>
          <li>  </li>
        )}
      </div>
    )
  }
  return (
  <div className="container">
    <div className="row">
      <div className="col-sm-3"></div>
      <div className="col-sm-6" min-height="50vh">
        
        {userData.timeline.map((post) => 
            <PostItem  userImage={post.user.image} userName={post.user.name} post={post} auth={props.auth}/>
          )}
      </div>
      <div className="col-sm-3"> 
          <h2>Followers</h2>
          <div className="follower-column" >
            {userData.followers.map((follower)=>
            <><Link to={{pathname: `/user/${follower.id}`}}>
              <img alt="userImage" className="rounded-circle" src = {follower.image}></img>
              <>{follower.name}</>
              <br></br>
              </Link></>
            )}
          </div>
      </div>
    </div>
  </div>
  )
}

export default Timeline;