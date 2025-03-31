import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: false,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
});

api.interceptors.response.use(
  response => response,
  error => {
    if (error.response?.status === 403 || error.response?.status === 401) {
    }
    return Promise.reject(error);
  }
);

export default api;