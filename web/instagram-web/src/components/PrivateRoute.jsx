import React, {useState} from "react";
import {NavLink, Redirect, Route, useHistory} from "react-router-dom";
import {readAuth, signOut} from "./Auth";


const Navar = ({auth,onSignOut}) =>{
    const [search, setSearch] = useState("");

    const history = useHistory()


    const handleSearchSubmit = (event) => {

        event.preventDefault()
        history.push("/search/");
    }

    const handleSearchChange = (event) => {
        setSearch(event.target.value);
    }

    return <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
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
                        <a className="nav-link" href="#" onClick={onSignOut}>SignOut</a>
                    </li>
                </>
                : null }

        </ul>
        <form className="form-inline my-2 my-lg-0" onSubmit={handleSearchSubmit}>
            <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" value={search} onChange={handleSearchChange}/>
            <button className="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
        </form>
    </nav>
}
const PrivateRoute = (props) => {

  if (!props.auth.isAuthenticated) return <Redirect to={"/login"} />;
  return (
    <Route path={props.path}>
        <Navar auth={props.auth} onSignOut={props.onSignOut}> </Navar>
        <div className="container pt-5">
            <props.component auth={props.auth}/>
        </div>

    </Route>
  );
};

export default PrivateRoute;