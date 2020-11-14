import { useState } from "react";

export function Register() {

  const [data, setData] = useState({
    name: '',
    email: '',
    password: '',
    repassword: '',
    imagen: '',
    error: '',
  });

  const handleInputChange = (event) =>{
    setData({
      ...data,
      [event.target.name]: event.target.value
    })
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

  }

  return (
  <>
    <h2>Register</h2>
    <form onSubmit={handleFormSubmit}>

      <div className="form-group">
        <label htmlFor="name">Name</label>
        <input name="name" className={`form-control ${data.error && 'is-invalid'}`} value={data.name} onChange={handleInputChange} />
      </div>
      
      <div className="form-group">
        <label htmlFor="email">Email</label>
        <input name="email" className={`form-control ${data.error && 'is-invalid'}`} value={data.email} onChange={handleInputChange} />
      </div>

      <div className="form-group">
        <label htmlFor="image">Image</label>
        <input name="image" className={`form-control ${data.error && 'is-invalid'}`} value={data.image} onChange={handleInputChange} />
      </div>

      <div className="form-group">
        <label htmlFor="password">Password</label>
        <input name="password" type="password" className={`form-control ${data.error && 'is-invalid'}`} value={data.password} onChange={handleInputChange} />
      </div>

      <div className="form-group">
        <label htmlFor="repassword">Password confirm</label>
        <input name="repassword" type="password" className={`form-control ${data.error && 'is-invalid'}`} value={data.repassword} onChange={handleInputChange} />
      </div>
      
      {data.error && <small className="form-text text-danger">{data.error}</small>}

      <button type="submit" className="btn btn-default">Enviar</button>
    </form>  
  </>
  );
}
export default Register;