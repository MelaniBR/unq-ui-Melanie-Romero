
import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  NavLink
} from "react-router-dom";
import {Login} from './components/Login/Login'
import {Timeline} from './components/Timeline/Timeline';
import './App.css';

function App() {
  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <NavLink to="/">Login</NavLink>
            </li>
            <li>
              <NavLink to="/home">Home</NavLink>
            </li>
          </ul>
        </nav>
        {/* A <Switch> looks through its children <Route>s and renders the first one that matches the current URL. */}
        <Switch>
          <Route exact path="/" component={Login} />
          <Route exact path="/home" component={Timeline} />
          <Route path="*" render={()=><h1>Not Found</h1>} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;