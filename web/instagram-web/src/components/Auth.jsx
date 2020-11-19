import React from 'react';
import { Redirect, Route } from 'react-router-dom';

//const isAuthenticated = !!localStorage.getItem("token");
export const signOut = () => { 
  removeAuth();
}

export const readAuth = () => {
  return {
    isAuthenticated: !!localStorage.getItem("token"),
    email: localStorage.getItem("email"),
    token: localStorage.getItem("token"),
    name: localStorage.getItem("name"),
    id: localStorage.getItem("id")
  }
}

export const saveAuth = (auth) => {
  localStorage.setItem("token", auth.token);
  localStorage.setItem("email", auth.email);
  localStorage.setItem("name", auth.name);
  localStorage.setItem("id", auth.id);
}

export const removeAuth = (auth) => {
  localStorage.removeItem("token");
  localStorage.removeItem("email");
  localStorage.removeItem("name");
  localStorage.removeItem("id");
}
  
export const AuthRoute = (props) => {

  const handleAuthOk = (token, email) => {
    if(!!token){
      saveAuth(token, email);
      props.onAuth();
    }
  }

  if (props.auth.isAuthenticated) return <Redirect to={"/home"} />;

  return (
    <Route path={props.path} >
      <div className="container pt-5">
        <div  className="row">
        <div className="col col-2"></div>
        <div className="col col-4 d-none d-md-block ">
          <img src="assets/images/authimage.png" style={{width:"100%"}}></img>
          </div>
        <div className="col sm-col-8 md-col-8 lg-col-4">
          <img src="assets/images/logo.png" style={{width:"100%"}}></img>
          <props.component onAuthOk={handleAuthOk} ></props.component>
        </div>
        <div className="col col-2"></div>
          
        </div>
        
      </div>
    </Route>
  );

}