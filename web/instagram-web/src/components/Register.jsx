import { useState} from "react";
import {validateControl, isValidateGroup, required, minLength, maxLength, email, url} from "./Validations.js";
import {register} from "./Api.js"
import Swal from 'sweetalert2/dist/sweetalert2'
import { Link } from "react-router-dom";

export const Register = ({ onAuthOk }) => {
  
  const[dataOk, setDataOk] = useState(false);
  const[loading, setLoading] = useState(false);
  const[error, setError] = useState("");

  const [data, setData] = useState({
    name: {label:'Name', value: '', error: '', validations: [(x) => required(x), (x) => minLength(x, 4), (x) => maxLength(x, 40)]},
    email: {label:'Email', value: '', error: '', validations: [(x) => required(x), (x) => email(x)]},
    password: {label:'Password', value: '', error: '', validations: [(x) => required(x)]},
    repassword: {label:'Password confirm', value: '', error: '', validations: []},
    image: {label:'Image', value: '', error: '', validations: [(x) => url(x)]}
  });

  const handleInputChange = (event) =>{
    
    var newData = {
      ...data,
      repassword: {
        ...data.repassword,
        validations: [ (x) => {return x.value !== data.password.value ? "Invalid password confirmation" : ""} ]
      }
    };

    newData = {
      ...newData,
      [event.target.name]: validateControl({
        ...newData[event.target.name], 
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
      register({name: data.name.value, email: data.email.value, password: data.password.value, image: data.image.value })
        .then(response => {
          setLoading(false);
          Swal.fire({
            title: 'Success!',
            text: 'You have successfully registered',
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
          setError(error.response ? error.response.data.message : "Connection error");
          setLoading(false);
        });
    }

  }

  return (
  <>
    <h4 className="text-center">Sign up to see photos and videos of your friends.</h4>
    <form onSubmit={handleFormSubmit}>

      <div className="form-group">
        <input placeholder={data.name.label} name="name" className={`form-control ${data.name.error && 'is-invalid'}`} value={data.name.value} onChange={handleInputChange} />
        {data.name.error && <span>{data.name.error}</span>}
      </div>
      
      <div className="form-group">
        <input placeholder={data.email.label} name="email" className={`form-control ${data.email.error && 'is-invalid'}`} value={data.email.value} onChange={handleInputChange} />
        {data.email.error && <span>{data.email.error}</span>}
      </div>

      <div className="form-group">
        <input placeholder={data.password.label} name="password" type="password" className={`form-control ${data.password.error && 'is-invalid'}`} value={data.password.value} onChange={handleInputChange} />
        {data.password.error && <span>{data.password.error}</span>}
      </div>

      <div className="form-group">
        <input placeholder={data.repassword.label} name="repassword" type="password" className={`form-control ${data.repassword.error && 'is-invalid'}`} value={data.repassword.value} onChange={handleInputChange} />
        {data.repassword.error && <span>{data.repassword.error}</span>}
      </div>

      <div className="form-group">
        <input placeholder={data.image.label} name="image" className={`form-control ${data.image.error && 'is-invalid'}`} value={data.image.value} onChange={handleInputChange} />
        {data.image.error && <span>{data.image.error}</span>}
      </div>

      <button type="submit" className="btn btn-primary" disabled={!dataOk || loading}>
        Register
        {loading && <span className="spinner-border spinner-border-sm ml-1" role="status" aria-hidden="true"></span>}
      </button>

      {error && <div className="alert alert-danger mt-3">{error}</div>}

      <div className="alert alert-info mt-3">You have an account? <Link to="/login">Sign in</Link></div>
       
    </form>  
  </>
  );
}
export default Register;