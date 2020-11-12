import React, { useState } from 'react';
import { Redirect, Route } from 'react-router-dom';
import login from './Api.js'
import PublicRoute from './PublicRoute.jsx';

export const LoginRoute = ({ path, component, auth, onLoginOk }) => {

  const [data, setData] = useState({
    email: '',
    password: '',
    error: '',
    success: '',
  });

  const handleInputChange = (event) =>{
    setData({
      ...data,
      [event.target.name]: event.target.value
    })
  };

  const handleLoginClick = (event) => {
    event.preventDefault();
    login({email: data.email, password: data.password})
      .then(response => {
        onLoginOk( response.headers.authorization, data.email );
      })
      .catch(error => {
        //Promise.reject(error.response.data);
        setData({
          ...data,
          error: 'Bad email or password'
        });
      });      
  };

    if (auth.isAuthenticated) return <Redirect to={"/home"} />;
    return (
    <Route path="/login">
      <div className="container">
        <form onSubmit={handleLoginClick}>
          <div className="form-group">
            <label htmlFor="email">Email address</label>
            <input name="email" className={`form-control ${data.error && 'is-invalid'}`} value={data.email} onChange={handleInputChange} />
            {data.error && <small className="form-text text-danger">{data.error}</small>}
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input name="password" type="password" className={`form-control ${data.error && 'is-invalid'}`} value={data.password} onChange={handleInputChange} />
          </div>
          <button type="submit" className="btn btn-primary">Login</button>
        </form>
      </div>      
    </Route>

  );

}

export default LoginRoute;