import React from 'react';
import { Redirect, Route } from 'react-router-dom';

//const isAuthenticated = !!localStorage.getItem("token");
export const signOut = () => { 
  saveAuth("", "");
}

export const readAuth = () => {
  return {
    isAuthenticated: !!localStorage.getItem("token"),
    email: localStorage.getItem("email"),
    token: localStorage.getItem("token")
  }
}

export const saveAuth = (token, email) => {
  localStorage.setItem("token", token);
  localStorage.setItem("email", email);
}

  
export const AuthRoute = (props) => {

  const handleAuthOk = (token, email) => {
    console.log("handleAuthOk", token, email)
    if(!!token){
      
      saveAuth(token, email);
      props.onAuth();
    }
  }

  if (props.auth.isAuthenticated) return <Redirect to={"/home"} />;

  return (
    <Route path={props.path} >
     <props.component onAuthOk={handleAuthOk} ></props.component>
    </Route>
  );

}