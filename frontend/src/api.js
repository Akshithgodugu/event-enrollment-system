import axios from 'axios';
const baseURL=import.meta.env.VITE_API_URL||'http://localhost:8081/api';
const api=axios.create({baseURL,withCredentials:true});
api.interceptors.response.use(
  response=>response,
  error=>{
    const message=error.response?.data?.message||error.response?.data?.error||
      (error.code==='ERR_NETWORK'?`Cannot connect to the backend at ${baseURL}. Please try again later.`:'Request failed. Please try again.');
    throw new Error(message);
  }
);
export default api;
