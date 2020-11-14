import { useEffect, useState, useCallback, useRef} from "react";
import {validateControl, validateGroup, isValidateGroup, required, minLength, maxLength, email, url} from "./Validations.js";

export function Register() {
  
  const [, updateState] = useState();
  const forceUpdate = useCallback(() => updateState({}), []);

  const[dataOk, setDataOk] = useState(false);

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
        validations: [ (x) => {console.log(data ); return x.value !== data.password.value ? "Invalid password confirmation" : ""} ]
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
    setDataOk(isValidateGroup(data));
    setData(validateGroup(data));
    forceUpdate()
    console.log(data)
  }

  return (
  <>
    <h2>Register</h2>
    <form onSubmit={handleFormSubmit}>

      <div className="form-group">
        <label htmlFor="name">{data.name.label}</label>
        <input name="name" className={`form-control ${data.name.error && 'is-invalid'}`} value={data.name.value} onChange={handleInputChange} />
        {data.name.error && <span>{data.name.error}</span>}
      </div>
      
      <div className="form-group">
        <label htmlFor="email">{data.email.label}</label>
        <input name="email" className={`form-control ${data.email.error && 'is-invalid'}`} value={data.email.value} onChange={handleInputChange} />
        {data.email.error && <span>{data.email.error}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="password">{data.password.label}</label>
        <input name="password" type="password" className={`form-control ${data.password.error && 'is-invalid'}`} value={data.password.value} onChange={handleInputChange} />
        {data.password.error && <span>{data.password.error}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="repassword">{data.repassword.label}</label>
        <input name="repassword" type="password" className={`form-control ${data.repassword.error && 'is-invalid'}`} value={data.repassword.value} onChange={handleInputChange} />
        {data.repassword.error && <span>{data.repassword.error}</span>}
      </div>

      <div className="form-group">
        <label htmlFor="image">{data.image.label}</label>
        <input name="image" className={`form-control ${data.image.error && 'is-invalid'}`} value={data.image.value} onChange={handleInputChange} />
        {data.image.error && <span>{data.image.error}</span>}
      </div>


      <button type="submit" className="btn btn-default" disabled={!dataOk}>Enviar</button>

    </form>  
  </>
  );
}
export default Register;