import React, { useState } from 'react';

export const Login = (props) => {

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

  const login = (event) => {
    event && event.preventDefault();
    if(data.email === 'pablo' && data.password === 'pablo') {
      this.props.history.push('/home', { user: { name: 'Pablo' } })
    } else {
      setData({
        ...data,
        error: 'Bad email or password (See README.md)'
      });
    }
  };


  return (
    <div className="container">
      <form onSubmit={(ev) => login(ev)}>
        <div className="form-group">
          <label htmlFor="email">Email address</label>
          <input name="email" className={`form-control ${data.error && 'is-invalid'}`} value={data.email} onChange={handleInputChange} />
          {data.error && <small className="form-text text-danger">{data.error}</small>}
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input name="password" className={`form-control ${data.error && 'is-invalid'}`} value={data.password} onChange={handleInputChange} />
        </div>
        <button type="submit" className="btn btn-primary" onClick={(ev) => login(ev)}>Login</button>
      </form>
    </div>
  );

}
