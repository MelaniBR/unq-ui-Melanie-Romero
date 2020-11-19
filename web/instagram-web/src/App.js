
import React, {useState} from "react";
import {
  BrowserRouter as Router,
  Switch,
  useHistory,
  NavLink,
    Redirect
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

const App = () => {

  const [auth, setAuth] = useState(readAuth);

  const handleSignOut = () => {
    signOut();
    setAuth(readAuth());
  }

  const handleOnAuth = () => {
    setAuth(readAuth());
  }

  return (
    <Router>

      {/* A <Switch> looks through its children <Route>s and renders the first one that matches the current URL. */}
      <div >
        <Switch>
          <AuthRoute path="/login" component={Login} auth={auth} onAuth={handleOnAuth}/>
          <AuthRoute path="/register" component={Register} auth={auth} onAuth={handleOnAuth}/>
          <PrivateRoute path="/home" component={Timeline} auth={auth} onSignOut={handleSignOut}/>
          <PrivateRoute path="/search/" component={Search} auth={auth} onSignOut={handleSignOut}/>
          <PrivateRoute path="/profile" component={Profile} auth={auth} onSignOut={handleSignOut}/>
          <PrivateRoute path="/post/:id" component={Post} auth={auth} onSignOut={handleSignOut}/>
          <PrivateRoute path="/user/:id" component={User} auth={auth} onSignOut={handleSignOut}/>
          <PrivateRoute path="*" component={Timeline} auth={auth} onSignOut={handleSignOut}/>
        </Switch>
      </div>      

    </Router>
  );
}

export default App;
