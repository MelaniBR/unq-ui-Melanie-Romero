import React from 'react';

const User = ({match}) => {
  return (<h1>User {match.params.id}</h1>)
}

export default User;