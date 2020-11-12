import React from "react";
import { Redirect, Route } from "react-router-dom";

const PrivateRoute = ({ path, component, auth }) => {

  if (!auth.isAuthenticated) return <Redirect to={"/login"} />;
  return <Route path={path} component={component} auth={auth}/>;
};

export default PrivateRoute;