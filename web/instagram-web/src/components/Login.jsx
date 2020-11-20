import React, { useState } from 'react';
import login from './Api.js'
import validateControl, { email, isValidateGroup, required } from './Validations.js';
import Swal from 'sweetalert2/dist/sweetalert2'
import { Link } from 'react-router-dom';

export const Login = ({ onAuthOk }) => {

  const[dataOk, setDataOk] = useState(false);
  const[loading, setLoading] = useState(false);
  const[error, setError] = useState("");

  const [data, setData] = useState({
    email: {label:'Email', value: '', error: '', validations: [(x) => required(x), (x) => email(x)]},
    password: {label:'Password', value: '', error: '', validations: [(x) => required(x)]},
  });


  const handleInputChange = (event) =>{
    var newData = {
      ...data,
      [event.target.name]: validateControl({
        ...data[event.target.name], 
        value: event.target.value
      })
    };
    setData(newData)
    setDataOk(isValidateGroup(newData));
  };

  const handleFormSubmit = (event) => {

    event.preventDefault();
    if(dataOk){
      setError("");
      setLoading(true)
      login({email: data.email.value, password: data.password.value})
        .then(response => {
          setLoading(false)
          Swal.fire({
            title: 'Login ok!',
            text: 'You have successfully logged in',
            icon: 'success',
            confirmButtonText: 'Go to timeline',
            willClose: () => {
              onAuthOk ({
                token: response.headers.authorization, 
                email: data.email.value, 
                name: response.data.name,
                id: response.data.id
              }) 
            }
          })
        })
        .catch(error => {
          setLoading(false)
          setError("Bad email or password");
        });
    }

  };


  return (
    <>
      <form onSubmit={handleFormSubmit}>
        <div className="form-group">
          <input placeholder={data.email.label} name="email" className={`form-control ${data.email.error && 'is-invalid'}`} value={data.email.value} onChange={handleInputChange} />
          {data.email.error && <span>{data.email.error}</span>}
        </div>
        <div className="form-group">
          <input placeholder={data.password.label} name="password" type="password" className={`form-control ${data.password.error && 'is-invalid'}`} value={data.password.value} onChange={handleInputChange} />
          {data.password.error && <span>{data.password.error}</span>}
        </div>
        <button type="submit" className="btn btn-primary" disabled={!dataOk || loading}>
          Enter
          {loading && <span className="spinner-border spinner-border-sm ml-1" role="status" aria-hidden="true"></span>}
        </button>
        {error && <div className="alert alert-danger mt-3">{error}</div>}
        <div className="alert alert-info mt-3">Not Registered? <Link to="/register">Create an account</Link></div>
      </form>
    </>
  );

}

export default Login;