
import React from 'react';

const Timeline = (props) => {
  console.log(props)
  return (
    <>
    <h1>Timeline</h1>
    <span>{props.auth.token}</span>
    </>
  )
}

export default Timeline;