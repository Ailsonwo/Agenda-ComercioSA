import axios from 'axios';

const API_URL = 'http://localhost:8080/contato/';

export const createContato = async (contatoData) => {
  try {
    const response = await axios.post(`${API_URL}`, contatoData);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const updateContato = async (contatoData) => {
  try {
    const response = await axios.put(`${API_URL}`, contatoData);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const deleteContato = async (id) => {
  try {
    const response = await axios.delete(`${API_URL}?id=${id}`);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

export const getContatosByClienteCpf = async (clienteCpf) => {
  try {
    const response = await axios.get(`${API_URL}buscaCpf/?cliente_cpf=${clienteCpf}`);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};

// Adicione esta nova função que estava faltando
export const getContato = async (id) => {
  try {
    const response = await axios.get(`${API_URL}${id}`);
    return response.data;
  } catch (error) {
    throw error.response?.data || error.message;
  }
};