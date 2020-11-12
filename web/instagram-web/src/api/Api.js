import axios from 'axios';

const API_URL = 'http://localhost:7000'

export const login = (data) => {
    return axios.post(`${API_URL}/login`, data)
}

export default login;