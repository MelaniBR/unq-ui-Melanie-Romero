import axios from 'axios';
const API_URL = 'http://localhost:7000';
let f = 0


export const login = (data) => {
  return axios.post(`${API_URL}/login`, data);
}

export const post = (id, token) => {
  return axios.get(`${API_URL}/post/${id}`, {headers : {Authorization : token}});
}

export const like = (id, token) => {
  return axios.put(`${API_URL}/post/${id}/like`, {}, {headers : {Authorization : token}});
}

export const comment = (newComment, id, token) => {
  return axios.post(`${API_URL}/post/${id}/comment`, {body : newComment}, {headers :  {Authorization : token}});
}

export const user = (token) => {
  return axios.get(`${API_URL}/user`, {headers : {Authorization : token}});
}

export const userById = (id, token) => {
  return axios.get(`${API_URL}/user/${id}`, {headers : {Authorization : token}});
}

export const follow = (id, token) => {
  return axios.put(`${API_URL}/user/${id}/follow`, {}, {headers : {Authorization : token}})
}

export const register = (data) => {
  return axios.post(`${API_URL}/register`, data);
}
export const search = (data,token) => {

  return axios.get(`${API_URL}/search`, {
      headers: {Authorization: token},
      params: {
        q: data
      }
    }
  );
}
export default login;