
import React, { useState, useEffect } from 'react';
import { user } from './Api.js';
import PostItem from './PostItem.jsx';


const Timeline = (props) => {

  const[userData, setUserData] = useState({
    timeline: []
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

  console.log(props)
  return (
    <>
    <h1>Timeline</h1>
    <span>{props.auth.token}</span>
    <div className="post-column">
      {userData.timeline.map((post) => 
          <PostItem  post={post} auth={props.auth}/>
        )}
    </div>

    <div> 
        <h2>Followers</h2>
        <div className="follower-column">
          {userData.followers.map((follower)=>
          <il>
            <img alt="userImage" src = {follower.image}></img>
            <h>{follower.name}</h>
            </il>
          )}
        </div>
    </div>
    </>
  )
}

export default Timeline;