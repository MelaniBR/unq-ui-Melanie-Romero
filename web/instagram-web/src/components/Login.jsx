import React, { useState } from 'react';
import login from './Api.js'

export const Login = ({ onAuthOk }) => {

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
        onAuthOk( response.headers.authorization, data.email );
      })
      .catch(error => {
        //Promise.reject(error.response.data);
        setData({
          ...data,
          error: 'Bad email or password'
        });
      });      
  };


    return (
    
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

  );

}

export default Login;