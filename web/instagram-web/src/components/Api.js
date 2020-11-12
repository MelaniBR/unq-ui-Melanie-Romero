import axios from 'axios';

const API_URL = 'http://localhost:7000';


export const login = (data) => {
  const authenticate = false;
  return axios.post(`${API_URL}/login`, data);
}

export const like = (data) => {
  return axios.like(`${API_URL}/post/${data.idPost}/like`)
}

export default login;