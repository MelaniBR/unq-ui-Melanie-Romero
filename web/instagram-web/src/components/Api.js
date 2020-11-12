import axios from 'axios';

const API_URL = 'http://localhost:7000';


export const login = (data) => {
  const authenticate = false;
  return axios.post(`${API_URL}/login`, data);
}

export const post = (id, token) => {
  return axios.get(`${API_URL}/post/${id}`,  {headers : {Authorization : token}});
}

export const like = (id, token) => {
  return axios.put(`${API_URL}/post/${id}/like`, {headers : {Authorization : token}});
}

export default login;