
import React, { useState } from 'react';

const Timeline = (props) => {

  const[user, setUser] = useState({});

  const getUserData = () => {
    user( props.auth.token )
      .then(response => {
        setUser(response.data)
      })
  }

  const renderPosts = () => {

  }

  const renderFollowers = () => {

  }

  console.log(props)
  return (
    <>
    <h1>Timeline</h1>
    <span>{props.auth.token}</span>
    </>
  )
}

export default Timeline;