import React, { useState, useEffect } from 'react';
import { createCliente, updateCliente, getClienteByCpf } from '../../api/clienteApi';

const ClienteForm = ({ cpf, onSave, onCancel }) => {
  const [cliente, setCliente] = useState({
    cpf: '',
    nome: '',
    data_nascimento: '',
    endereco: ''
  });
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (cpf) {
      loadCliente(cpf);
    }
  }, [cpf]);

  const loadCliente = async (cpf) => {
    try {
      const data = await getClienteByCpf(cpf);
      console.log("DADOS DO CLIENTE: ",data);
      setCliente({
        ...data.data,
        data_nascimento: data.data.data_nascimento.split('T')[0] // Formata a data
      });
    } catch (error) {
      console.error('Erro ao carregar cliente:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCliente(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validação simples
    const newErrors = {};
    if (!cliente.cpf) newErrors.cpf = 'CPF é obrigatório';
    if (!cliente.nome) newErrors.nome = 'Nome é obrigatório';
    if (!cliente.data_nascimento) newErrors.data_nascimento = 'Data de nascimento é obrigatória';

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    try {
      const clienteData = {
        ...cliente,
        data_nascimento: new Date(cliente.data_nascimento).toISOString()
      };

      if (cpf) {
        await updateCliente(clienteData);
      } else {
        await createCliente(clienteData);
      }

      onSave();
    } catch (error) {
      console.error('Erro ao salvar cliente:', error);
      alert(error.message || 'Erro ao salvar cliente');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>CPF:</label>
        <input
          type="text"
          name="cpf"
          value={cliente.cpf}
          onChange={handleChange}
          disabled={!!cpf}
        />
        {errors.cpf && <span className="error">{errors.cpf}</span>}
      </div>

      <div>
        <label>Nome:</label>
        <input
          type="text"
          name="nome"
          value={cliente.nome}
          onChange={handleChange}
        />
        {errors.nome && <span className="error">{errors.nome}</span>}
      </div>

      <div>
        <label>Data de Nascimento:</label>
        <input
          type="date"
          name="data_nascimento"
          value={cliente.data_nascimento}
          onChange={handleChange}
        />
        {errors.data_nascimento && <span className="error">{errors.data_nascimento}</span>}
      </div>

      <div>
        <label>Endereço:</label>
        <input
          type="text"
          name="endereco"
          value={cliente.endereco}
          onChange={handleChange}
        />
      </div>

      <button type="submit">Salvar</button>
      <button type="button" onClick={onCancel}>Cancelar</button>
    </form>
  );
};

export default ClienteForm;