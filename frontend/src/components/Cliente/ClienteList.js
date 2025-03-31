import React, { useState, useEffect } from 'react';
import { getClientes, searchClientesByNome, deleteCliente } from '../../api/clienteApi';

const ClienteList = ({ onEdit, onViewContatos }) => {
  const [clientes, setClientes] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadClientes();
  }, []);

  const loadClientes = async () => {
    setLoading(true);
    try {
      const data = await getClientes();
      setClientes(data);
    } catch (error) {
      console.error('Erro ao carregar clientes:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async () => {
    if (!searchTerm.trim()) {
      loadClientes();
      return;
    } else {
      try {
        const data = await searchClientesByNome(searchTerm);
        setClientes(data);
      } catch (error) {
        console.error('Erro ao carregar clientes:', error);
      } finally {
        setLoading(false);
      }
    }

    setLoading(true);
    try {
      const data = await searchClientesByNome(searchTerm);
      setClientes(data);
    } catch (error) {
      console.error('Erro na busca:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (cpf) => {
    if (window.confirm('Tem certeza que deseja excluir este cliente e todos seus contatos?')) {
      try {
        await deleteCliente(cpf);
        loadClientes();
      } catch (error) {
        console.error('Erro ao excluir cliente:', error);
        alert(error.message || 'Erro ao excluir cliente');
      }
    }
  };

  return (
    <div>
      <div className="search-bar">
        <input
          type="text"
          placeholder="Buscar por nome..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button onClick={handleSearch}>Buscar</button>
      </div>

      {loading ? (
        <p>Carregando...</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>CPF</th>
              <th>Nome</th>
              <th>Data Nasc.</th>
              <th>Endereço</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {clientes.map(cliente => (
              <tr key={cliente.cpf}>
                <td>{cliente.cpf}</td>
                <td>{cliente.nome}</td>
                <td>{new Date(cliente.data_nascimento + "T03:00:00Z").toLocaleDateString()}</td>
                <td>{cliente.endereco}</td>
                <td>
                  <button onClick={() => onEdit(cliente.cpf)}>Editar</button>
                  <button onClick={() => onViewContatos(cliente.cpf)}>Contatos</button>
                  <button onClick={() => handleDelete(cliente.cpf)}>Excluir</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ClienteList;