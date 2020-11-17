
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
    <div className="row">
      {userData.timeline.map((post) => 
          <PostItem  post={post} auth={props.auth}/>
        )}
    </div>

    </>
  )
}

export default Timeline;