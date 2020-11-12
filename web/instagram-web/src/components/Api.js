import axios from 'axios';

const API_URL = 'http://localhost:7000';


export const login = (data) => {
  const authenticate = false;
  return axios.post(`${API_URL}/login`, data);
}

export default login;