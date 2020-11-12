import React from "react";
import { Redirect, Route } from "react-router-dom";

const PublicRoute = (props) => {
  if (props.auth.isAuthenticated) return <Redirect to={"/home"} />;
  return (
    <Route path={props.path}>
      <props.component auth={props.auth}/>
    </Route>
  );
};

export default PublicRoute;