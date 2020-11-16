
import React, { useState } from 'react';
import { user } from './Api.js';
import PostItem from './PostItem.jsx';


const Timeline = (props) => {

  const[userData, setUserData] = useState({});

  const getUserData = () => {
    user( props.auth.token )
      .then(response => {
        setUserData(response.data);
      })
  }


  function renderPostItemList() {
    return (
    <ul>
      {userData.timeline.map((post) => 
        <li> <PostItem  post={post} auth={props.auth.token}/> </li>
      )}
    </ul>
      )
  }

  const renderFollowers = () => {
    return (
      <ul>
        {userData.followers.map((follower) =>
          <li>  </li>
        )}
      </ul>
    )
  }

  console.log(props)
  return (
    <>
    <h1>Timeline</h1>
    <span>{props.auth.token}</span>
    <div>
      { renderPostItemList() }
    </div>

    </>
  )
}

export default Timeline;