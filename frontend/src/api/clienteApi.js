import axios from 'axios';

const API_URL = 'http://localhost:8080/cliente/';

export const createCliente = async (clienteData) => {
  try {
    clienteData.data_nascimento = clienteData.data_nascimento.split('T')[0];
    const response = await axios.post(`${API_URL}`, clienteData);
    return response.data;
  } catch (error) {
    throw error.response.data;
  }
};

export const updateCliente = async (clienteData) => {
  try {
    clienteData.data_nascimento = clienteData.data_nascimento.split('T')[0];
    const response = await axios.put(`${API_URL}`, clienteData);
    return response.data;
  } catch (error) {
    throw error.response.data;
  }
};

export const deleteCliente = async (cpf) => {
  try {
    const response = await axios.delete(`${API_URL}?cpf=${cpf}`);
    return response.data;
  } catch (error) {
    throw error.response.data;
  }
};

export const getClientes = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data.data;
  } catch (error) {
    throw error;
  }
};

export const searchClientesByNome = async (nome) => {
  try {
    const response = await axios.get(`${API_URL}buscaNome/?nome=${nome}`);
    return response.data.data;
  } catch (error) {
    throw error.response.data;
  }
};

export const getClienteByCpf = async (cpf) => {
  try {
    const response = await axios.get(`${API_URL}buscaCpf/?cpf=${cpf}`);
    return response.data;
  } catch (error) {
    throw error.response.data;
  }
};