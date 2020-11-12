
import React, {useState} from "react";
import {
  BrowserRouter as Router,
  Switch,
  NavLink
} from "react-router-dom";
import LoginRoute from './components/LoginRoute';
import Timeline from './components/Timeline';
import PrivateRoute from './components/PrivateRoute';
import PublicRoute from './components/PublicRoute';
import Register from './components/Register';
import Home from "./components/Home";
import Search from "./components/Search";
import Profile from "./components/Profile";
import Post from "./components/Post";
import User from "./components/User";
import './App.css';

const App = () => {

  const [auth, setAuth] = useState({
    isAuthenticated: !!localStorage.getItem("token"),
    email: localStorage.getItem("email"),
    token: localStorage.getItem("token")
  });

  //const isAuthenticated = !!localStorage.getItem("token");
  const unlogin = () => { 
    localStorage.setItem("token", "");
    setAuth({
      isAuthenticated: false,
      email: '',
      token: ''
    })
  }
  
  const handleLoginOk = (token, email) => {
    if(!!token){
      localStorage.setItem("token", token);
      localStorage.setItem("email", email);
      setAuth({
        isAuthenticated: true,
        email: email,
        token: token
      });
    }
    
  }

  return (
    <Router>
      <div>
        <nav>
          <ul>
            {!auth.isAuthenticated ?
              <>
                <li>
                  <NavLink to="/" >SingIn</NavLink>
                </li> 
                <li>
                  <NavLink to="/register">Register</NavLink>
                </li>
              </>
            : null }

            {auth.isAuthenticated ?
              <>
                <li>
                  <NavLink to="/home">Home</NavLink>
                </li>
                <li>
                  <NavLink to="/search">Search</NavLink>
                </li>
                <li>
                  <NavLink to="/profile">Profile</NavLink>
                </li>
                <li>
                  <a href="#" onClick={unlogin}>SingOut</a>
                </li>             
              </>
            : null }

          </ul>
        </nav>
        {/* A <Switch> looks through its children <Route>s and renders the first one that matches the current URL. */}
        <Switch>
          <LoginRoute path="/login" auth={auth} onLoginOk={handleLoginOk}/>
          <PublicRoute path="/register" component={Register} auth={auth}/>
          <PrivateRoute path="/home" component={Timeline} auth={auth}/>
          <PrivateRoute path="/search" component={Search} auth={auth}/>
          <PrivateRoute path="/profile" component={Profile} auth={auth}/>
          <PrivateRoute path="/post/:id" component={Post} auth={auth}/>
          <PrivateRoute path="/user/:id" component={User} auth={auth}/>
          <PrivateRoute path="*" component={Timeline} auth={auth}/>
        </Switch>
      </div>
    </Router>
  );
}

export default App;