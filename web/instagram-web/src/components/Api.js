import axios from 'axios';

const API_URL = 'http://localhost:7000';


export const login = (data) => {
  const authenticate = false;
  return axios.post(`${API_URL}/login`, data);
}

export const post = (id, token) => {
  return axios.get(`${API_URL}/post/${id}`, {headers : {Authorization : token}});
}

export const like = (id, token) => {
  return axios.put(`${API_URL}/post/${id}/like`, {headers : {Authorization : token}});
}

export const comment = (newComment, id, token) => {
  return axios.put(`${API_URL}/post/${id}/comment`, {body : newComment}, {headers :  {Authorization : token}});
}

export const user = (token) => {
  return axios.get(`${API_URL}/get/User`, {headers : {Authorization : token}});
}

export const register = (data) => {
  return axios.post(`${API_URL}/register`, data);
}

export default login;