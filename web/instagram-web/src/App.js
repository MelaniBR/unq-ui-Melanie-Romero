
import React, {useState} from "react";
import {
  BrowserRouter as Router,
  Switch,
  NavLink, Redirect
} from "react-router-dom";
import Timeline from './components/Timeline';
import PrivateRoute from './components/PrivateRoute';
import Register from './components/Register';
import Search from "./components/Search";
import Profile from "./components/Profile";
import Post from "./components/Post";
import User from "./components/User";
import Login from "./components/Login"
import './App.css';
import { AuthRoute, readAuth, signOut } from "./components/Auth";
import ResultOfSearch from "./components/ResultOfSearch";
  


const App = () => {

  const [auth, setAuth] = useState(readAuth);

  const [search, setSearch] = useState("");


  
  const handleSearchSubmit = (event) => {




  }

  const handleSearchChange = (event) => {
    setSearch(event.target.value);
  }

  const handleOnAuth = () => {
    setAuth(readAuth());
  }

  const handleSignOut = () => {
    signOut();
    setAuth(readAuth());
  }

  return (
    <Router>

      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul className="navbar-nav mr-auto">
          {!auth.isAuthenticated ?
            <>
              <li className="nav-item">
                <NavLink className="nav-link" to="/" >SignIn</NavLink>
              </li> 
              <li className="nav-item">
                <NavLink className="nav-link" to="/register">Register</NavLink>
              </li>
            </>
          : null }

          {auth.isAuthenticated ?
            <>
              <li className="nav-item">
                <NavLink className="nav-link" to="/home">Home</NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/search">Search</NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/profile">Profile</NavLink>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#" onClick={handleSignOut}>SignOut</a>
              </li>
            </>
          : null }

        </ul>
        <form className="form-inline my-2 my-lg-0" onSubmit={handleSearchSubmit}>
          <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" value={search} onChange={handleSearchChange}/>
          <button className="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
        </form>
      </nav>
      {/* A <Switch> looks through its children <Route>s and renders the first one that matches the current URL. */}
      <div className="container pt-5">
        <Switch>
          <AuthRoute path="/login" component={Login} auth={auth} onAuth={handleOnAuth}/>
          <AuthRoute path="/register" component={Register} auth={auth} onAuth={handleOnAuth}/>
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