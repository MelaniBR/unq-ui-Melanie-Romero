import axios from 'axios';

const API_URL = 'http://localhost:7000';


export const login = (data) => {
  return axios.post(`${API_URL}/login`, data);
}

export const post = (id) => {
  return axios.get(`${API_URL}/post/${id}`);
}

export const like = (id, token) => {
  return axios.put(`${API_URL}/post/${id}/like`, {headers : {Authorization : token}});
}

export const comment = (newComment, id, token) => {
  return axios.put(`${API_URL}/post/${id}/comment`, {body : newComment});
}

export const user = (token) => {
  return axios.get(`${API_URL}/user`, {headers : {Authorization : token}});
}

export const userById = (id, token) => {
  return axios.get(`${API_URL}/user/${id}`, {headers : {Authorization : token}});
}

export const register = (data) => {
  return axios.post(`${API_URL}/register`, data);
}

export default login;