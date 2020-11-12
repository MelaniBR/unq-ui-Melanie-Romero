import React from "react";
import { Redirect, Route } from "react-router-dom";

const PrivateRoute = (props) => {
  if (!props.auth.isAuthenticated) return <Redirect to={"/login"} />;
  return (
    <Route path={props.path}>
      <props.component auth={props.auth}/>
    </Route>
  );
};

export default PrivateRoute;